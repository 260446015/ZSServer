package com.huishu.ait.security;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.huishu.ait.entity.Permission;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.repository.user.PermissionRepository;
import com.huishu.ait.repository.user.UserBaseRepository;
import com.huishu.ait.repository.user.UserPermissionRepository;

/**
 * 身份校验核心类
 * @author yindq
 * @date 2017年8月8日
 */
public class ShiroDbRealm extends AuthorizingRealm {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);

	@Autowired
	private UserBaseRepository userBaseRepository;
	@Autowired
	private UserPermissionRepository userPermissionRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LOGGER.info("===============进行权限配置================");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		ShiroUser user  = (ShiroUser)principals.getPrimaryPrincipal();
		authorizationInfo.addRole(user.type);
		List<Long> permissionIds = userPermissionRepository.findPermissionIdByAdminId(user.getId());
		if (permissionIds != null && permissionIds.size()!=0) {
			for (Long permissionId : permissionIds) {
				Permission permission = permissionRepository.findOne(permissionId);
				authorizationInfo.addStringPermission(permission.getPermissionName());
			}
		}
		
		return authorizationInfo;
	}

	/**
	 * 认证身份
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		CaptchaUsernamePasswordToken myToken =(CaptchaUsernamePasswordToken) token;
		//获取用户的输入的账号.
		String userAccount = myToken.getUsername();
		UserBase user = userBaseRepository.findByUserAccount(userAccount);
		if(user==null){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("user {} is not exist.", myToken.getUsername());
			}
			throw new IncorrectCredentialsException();
		}
		//获取权限
		List<Long> permissions = userPermissionRepository.findPermissionIdByAdminId(user.getId());
		user.setPermissions(permissions);
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
    		   new ShiroUser(user.getId(),user.getUserAccount(),user.getRealName(),user.getUserType(),user.getUserPark()),
				user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()),
				getName());
       return authenticationInfo;
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		private Long id;
		private String loginName;
		private String name;
		private String type;
		private String park;
		
		public ShiroUser(Long id, String loginName, String name, String type, String park) {
			super();
			this.id = id;
			this.loginName = loginName;
			this.name = name;
			this.type = type;
			this.park = park;
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
		
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getPark() {
			return park;
		}


		public void setPark(String park) {
			this.park = park;
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
