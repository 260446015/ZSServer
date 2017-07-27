package com.huishu.ait.exception;

/**
 * 日期转换异常
 * @author yuwei
 *
 */
public class DateParseException extends RuntimeException {

	private static final long serialVersionUID = -1349427261192356043L;
	
	public DateParseException() {
        super();
    }
	
	public DateParseException(String message) {
        super(message);
    }
	
	public DateParseException(Throwable cause) {
        super(cause);
    }
	
	public DateParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
