package com.huishu.ait.entity.common;

/**
 * @author hhy
 * @date 2017年7月26日
 * @Parem
 * @return
 * 
 */
public class AjaxResult {

	private boolean success;

	private Object data;

	private String message;

	private int status;

	public boolean isSuccess() {
		return success;
	}

	public AjaxResult setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public Object getData() {
		return data;
	}

	public AjaxResult setData(Object data) {
		this.data = data;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public AjaxResult setMessage(String message) {
		this.message = message;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public AjaxResult setStatus(int status) {
		this.status = status;
		return this;
	}
}
