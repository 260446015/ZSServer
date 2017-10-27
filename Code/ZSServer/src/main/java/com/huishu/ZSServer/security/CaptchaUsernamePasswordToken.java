package com.huishu.ZSServer.security;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

	private static final long serialVersionUID = -457397376802136466L;

	/**
	 * 用户类型：admin/user
	 */
	private String type;

	// 验证码字符串
	private String captcha;

	public CaptchaUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host,
			String captcha, String type) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.type = type;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
