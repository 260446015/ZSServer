package com.huishu.ZSServer.security;

import java.util.Collection;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);

	@Autowired
	private DefaultWebSecurityManager securityManager;

	/**
	 * 登陆验证
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		UsernamePasswordToken token = createToken(request, response);
		try {
			Subject subject = getSubject(request, response);
			DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
			// 单点登录
			Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
			for (Session session : sessions) {
				if (token.getUsername()
						.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
					sessionManager.getSessionDAO().delete(session);
				}
			}
			subject.login(token);// 正常验证
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			log.error("登录失败." + e);
			return onLoginFailure(token, e, request, response);
		}
	}

	/**
	 * 登陆成功
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		httpServletRequest.setAttribute("success", true);
		return true;
	}

	/**
	 * 创建token
	 */
	@Override
	protected UsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		boolean rememberMe = isRememberMe(request);
		return new UsernamePasswordToken(username,password,rememberMe);
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				String account = this.getUsername(request);
				Subject subject = this.getSubject(request, response);
				/*ShiroUser user = (ShiroUser) subject.getPrincipal();
				if (account != null && user != null && !account.equals(user.getLoginName())) {
					subject.logout();
				}*/
			}
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}
	
}
