package com.huishu.ait.entity.dto;

import java.io.Serializable;

public class CaptchaDTO implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private String telphone;
	private String type;

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
