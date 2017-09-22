package com.huishu.ait.entity.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户修改密码DTO
 * 
 * @author yindq
 * @date 2017年8月24日
 */
public class UserPasswordDTO implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String oldPassword;
	private String newPassword;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
