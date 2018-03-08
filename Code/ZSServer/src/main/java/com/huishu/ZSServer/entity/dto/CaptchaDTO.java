package com.huishu.ZSServer.entity.dto;

import java.io.Serializable;

public class CaptchaDTO implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private String telphone;
	private String realName;

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	

}
