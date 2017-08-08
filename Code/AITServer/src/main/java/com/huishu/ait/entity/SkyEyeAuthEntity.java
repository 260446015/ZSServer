package com.huishu.ait.entity;

/**
 * @author yindawei 
 * @date 2017年8月8日上午11:33:03
 * @description 
 * @version 
 */
public class SkyEyeAuthEntity {
 
	/**
	 * 天眼查token
	 */
	private String accessToken;
	/**
	 * 设置token过期时间
	 */
	private String expireTime;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	
}
