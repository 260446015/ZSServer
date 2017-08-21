package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yindawei 
 * @date 2017年8月8日上午11:33:03
 * @description 
 * @version 
 */
@Table(name="t_skyeye_access")
@Entity
public class SkyEyeAuthEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8831887752628366901L;

	/**
	 * 存贮id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
 
	/**
	 * 天眼查token
	 */
	private String accessToken;
	/**
	 * 设置token过期时间
	 */
	private String expireTime;
	
	/**
	 * 天眼查测试账号
	 */
	private String account;
	
	/**
	 * 天眼查测试密码
	 */
	private String password;
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
