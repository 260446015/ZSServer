package com.huishu.ait.common.util.send;

/**
 * 寄件人
 * 
 * @author yuwei
 */
public class AddresserDTO {

	private String username;

	private char[] password;

	private String token;

	private String message;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
