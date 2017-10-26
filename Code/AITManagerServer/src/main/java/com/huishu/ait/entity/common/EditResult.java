package com.huishu.ait.entity.common;

/**
 * @author hhy
 * @date 2017年7月26日
 * @Parem
 * @return
 * 
 */
public class EditResult {


	private ImageSrc data;

	private String message;

	private int code;

	public ImageSrc getData() {
		return data;
	}

	public void setData(ImageSrc data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}

