package com.huishu.ManageServer.exception;

/*
* 属性不合法异常
* @author yuwei
*/
public class IllegalFieldException extends RuntimeException {

	private static final long serialVersionUID = -4538577727284541975L;

	public IllegalFieldException() {
		super();
	}

	public IllegalFieldException(String message) {
		super(message);
	}

	public IllegalFieldException(Throwable cause) {
		super(cause);
	}

	public IllegalFieldException(String message, Throwable cause) {
		super(message, cause);
	}

}
