package com.huishu.ManageServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户dto
 * 
 * @author yindq
 * @date 2018/1/4
 */
public class UserBaseDTO implements Serializable {

	/** 用户id */
	private Long id;

	private String userAccount;

	private String password;

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

	/** 名片路径 */
	private String imageUrl;

	/** 创建时间 */
	private String createTime;

	/** 会员时间 */
	private String userTime;

	/** 用户类型 */
	private String userType;

	/** 会员等级 */
	private Integer userLevel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserTime() {
		return userTime;
	}

	public void setUserTime(String userTime) {
		this.userTime = userTime;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
