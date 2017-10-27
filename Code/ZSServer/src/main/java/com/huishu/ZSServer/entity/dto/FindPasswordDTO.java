package com.huishu.ZSServer.entity.dto;

import java.io.Serializable;

public class FindPasswordDTO implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private String telphone;
	private String captcha;
	private String newPassword;

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
