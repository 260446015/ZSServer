package com.huishu.ManageServer.entity.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 用户日志视图类
 *
 * @author yindq
 * @date 2018/1/11
 */
public class UserLogoVO implements Serializable {

	/** 用户id */
	private Long userId;

	/** 账号 */
	private String userAccount;

	/** 真实姓名 */
	private String realName;

	/** 手机号 */
	private String telphone;

	/** 邮箱号 */
	private String userEmail;

	/** 用户登录时间 */
	private String loginTime;

	/** 用户操作时间 */
	private String operationTime;

	/** 查询公司 */
	private String searchCompany;

	/** 查询公司次数 */
	private Integer searchCount;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
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

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getSearchCompany() {
		return searchCompany;
	}

	public void setSearchCompany(String searchCompany) {
		this.searchCompany = searchCompany;
	}

	public Integer getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(Integer searchCount) {
		this.searchCount = searchCount;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
