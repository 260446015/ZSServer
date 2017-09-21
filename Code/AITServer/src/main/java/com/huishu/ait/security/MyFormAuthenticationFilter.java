package com.huishu.ait.security;

import java.util.Collection;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.huishu.ait.security.ShiroDbRealm.ShiroUser;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);

	@Autowired
	private DefaultWebSecurityManager securityManager;

	/**
	 * 登陆验证
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token = createToken(request, response);
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
			log.error("登录失败."+e);
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
		// if
		// ("XMLHttpRequest".equals(httpServletRequest.getHeader("X-Requested-With")))
		// {
		httpServletRequest.setAttribute("success", true);
		return true;
		// } else {
		// this.issueSuccessRedirect(httpServletRequest, response);
		// return false;
		// }
	}

	/**
	 * 创建token
	 */
	@Override
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		String type = getType(request);
		return new CaptchaUsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, type);
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				String account = this.getUsername(request);
				Subject subject = this.getSubject(request, response);
				ShiroUser user = (ShiroUser) subject.getPrincipal();
				if (account != null && user != null && !account.equals(user.getLoginName())) {
					//本来这么写是可以解决问题的但是，因为session里存了私钥，所以对密码解不了密
					subject.logout();
				}
			}
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}

	private String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, CheckCodeUtil.DEFAULT_CAPTCHA_PARAM);
	}

	private String getType(ServletRequest request) {
		return WebUtils.getCleanParam(request, "type");
	}
}
