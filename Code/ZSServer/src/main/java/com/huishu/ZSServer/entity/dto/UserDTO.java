package com.huishu.ZSServer.entity.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户信息修改DTO
 * 
 * @author yindq
 * @date 2017年12月15日
 */
public class UserDTO implements Serializable {
	private static final long serialVersionUID = -3776635613444720804L;
	/** 真实姓名 */
	private String realName;
	/** 手机号 */
	private String telphone;
	/** 邮箱号 */
	private String userEmail;
	/** 所属职务 */
	private String userJob;
	/** 部门 */
	private String userDepartment;
	/** 所属园区 */
	private String userPark;
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserJob() {
		return userJob;
	}
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	public String getUserDepartment() {
		return userDepartment;
	}
	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}
	public String getUserPark() {
		return userPark;
	}
	public void setUserPark(String userPark) {
		this.userPark = userPark;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
