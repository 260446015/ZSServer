package com.huishu.ZSServer.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 身份校验核心类
 * 
 * @author yindq
 * @date 2017年8月8日
 */
public class ShiroDbRealm extends AuthorizingRealm {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LOGGER.info("===============进行权限配置================");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 获取权限
		return authorizationInfo;
	}

	/**
	 * 认证身份
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		LOGGER.info("===============进行登陆认证================");
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
		return authenticationInfo;
	}

	@Override
	public void onLogout(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
		Object object = principals.getPrimaryPrincipal();
		removeUserCache(object);
	}

	/**
	 * 清除用户缓存
	 * 
	 * @param shiroUser
	 */
	public void removeUserCache(Object shiroUser) {
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

}
