package com.huishu.ManageServer.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 账号过期异常
 * 
 * @author yindq
 * @date 2017年12月14日
 */
public class AccountExpiredException extends AuthenticationException {

	private static final long serialVersionUID = 5024425056491608508L;

	public AccountExpiredException() {
		super();
	}

	public AccountExpiredException(String message) {
		super(message);
	}

	public AccountExpiredException(Throwable cause) {
		super(cause);
	}

	public AccountExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

}
