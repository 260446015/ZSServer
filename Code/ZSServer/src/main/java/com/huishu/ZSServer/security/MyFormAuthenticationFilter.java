package com.huishu.ZSServer.security;

import java.util.Collection;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.huishu.ZSServer.common.util.ShiroUtil;

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
			// 单点登录l
			Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
			for (Session session : sessions) {
				if(token.getUsername().equals("testzkdj")){
					continue;
				}
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
		String host = getHost(request);
		return new UsernamePasswordToken(username,password.toCharArray(),rememberMe, host);
	}

	/**
	 * 未登录拦截处理
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		 if (isLoginRequest(request, response)) {  
             if (isLoginSubmission(request, response)) {  
                 if (log.isTraceEnabled()) {  
                     log.trace("Login submission detected.  Attempting to execute login.");  
                 }  
                 return executeLogin(request, response);  
             } else {  
                 if (log.isTraceEnabled()) {  
                     log.trace("Login page view.");  
                 }  
                 return true;  
             }  
         } else {  
             if (log.isTraceEnabled()) {  
                 log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +  
                         "Authentication url [" + getLoginUrl() + "]");  
             }  
             if(isAjax(request)){  
            	 ShiroUtil.writeResponse((HttpServletResponse)response, "请先登录本系统！");
             }else{  
                 this.saveRequestAndRedirectToLogin(request, response);  
             }  
             return false;  
         }  
	}
   
	private static boolean isAjax(ServletRequest request){  
		String header = ((HttpServletRequest) request).getHeader("X-Requested-With");  
		if("XMLHttpRequest".equalsIgnoreCase(header)){  
			return Boolean.TRUE;  
		}  
		return Boolean.FALSE;  
	}  
	
	
	
}
