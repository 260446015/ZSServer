package com.huishu.ait.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;

public class MyUserFilter extends UserFilter {
	
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	}
	
}
