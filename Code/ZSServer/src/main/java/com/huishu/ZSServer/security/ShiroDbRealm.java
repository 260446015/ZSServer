package com.huishu.ZSServer.security;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.huishu.ZSServer.entity.user.UserBase;
import com.huishu.ZSServer.exception.AccountExpiredException;
import com.huishu.ZSServer.exception.AccountStartException;
import com.huishu.ZSServer.repository.user.UserBaseRepository;

/**
 * 身份校验核心类
 * 
 * @author yindq
 * @date 2017年12月13日
 */
public class ShiroDbRealm extends AuthorizingRealm {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);

	@Autowired
	private UserBaseRepository userBaseRepository;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LOGGER.info("===============进行权限配置================");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		/*ShiroUser user = (ShiroUser) principals.getPrimaryPrincipal();
		// 获取权限
		List<Long> permissionIds = userPermissionService.getPermissionIdsByUserId(user.getId());
		if (permissionIds != null && permissionIds.size() != 0) {
			authorizationInfo.addRole(user.getUserLevel().toString());
			for (Long permissionId : permissionIds) {
				Permission permission = permissionService.getPermissionByPermissionId(permissionId);
				authorizationInfo.addStringPermission(permission.getPermissionName());
			}
		}*/
		return authorizationInfo;
	}

	/**
	 * 认证身份
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		LOGGER.info("===============进行登陆认证================");
		UsernamePasswordToken myToken = (UsernamePasswordToken) token;
		UserBase user = userBaseRepository.findByUserAccount(myToken.getUsername());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = format.format(new Date());
		if (user == null) {
			LOGGER.debug("user {} is not exist.", myToken.getUsername());
			throw new IncorrectCredentialsException();
		} 
		if (user.getIsCheck() == 0) {
			LOGGER.debug("user {} is not check.", myToken.getUsername());
			throw new AccountStartException();
		} else if (today.compareTo(user.getExpireTime()) > 0) {
			LOGGER.debug("user {} be overdue.", myToken.getUsername());
			throw new AccountExpiredException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				new ShiroUser(user.getId(), user.getUserAccount(), user.getRealName(), user.getUserLevel()),
				user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
		return authenticationInfo;
	}

	@Override
	public void onLogout(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		removeUserCache(shiroUser.getLoginName());
	}

	/**
	 * 清除用户缓存
	 * 
	 * @param loginName
	 */
	public void removeUserCache(String loginName) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection();
		principals.add(loginName, super.getName());
		super.clearCachedAuthenticationInfo(principals);
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		private Long id;
		private String loginName;
		private String name;
		private Integer userLevel;

		public ShiroUser(Long id, String loginName, String name, Integer userLevel) {
			super();
			this.id = id;
			this.loginName = loginName;
			this.name = name;
			this.userLevel = userLevel;
		}

		public Integer getUserLevel() {
			return userLevel;
		}

		public void setUserLevel(Integer userLevel) {
			this.userLevel = userLevel;
		}

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(loginName);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (loginName == null) {
				if (other.loginName != null) {
					return false;
				}
			} else if (!loginName.equals(other.loginName)) {
				return false;
			}
			return true;
		}
	}
	
}
