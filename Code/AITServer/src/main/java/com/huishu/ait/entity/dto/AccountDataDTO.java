package com.huishu.ait.entity.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 账号数据展示DTO
 * 
 * @author yindq
 * @create 2017年9月19日
 */
public class AccountDataDTO implements Serializable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	/** 真实姓名 */
	private String name;
	/** 账号 */
	private String account;
	/** 开通时间 */
	private String time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
