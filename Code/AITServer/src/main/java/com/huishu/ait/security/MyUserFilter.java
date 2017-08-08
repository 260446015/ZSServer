package com.huishu.ait.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

public class MyUserFilter extends UserFilter {
	
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if ("XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With"))) {
			//ajax 请求直接返回403 Access Denied
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendError(403, "Access Denied");
		} else {
			//普通请求返回登录页面
			String loginUrl = getLoginUrl();
			WebUtils.issueRedirect(request, response, loginUrl);
		}
	}
	
}
