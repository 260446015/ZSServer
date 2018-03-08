package com.huishu.ZSServer.entity.dto;

import java.io.Serializable;

import javax.persistence.Column;

import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2018年3月2日
 * @Parem
 * @return 
 * 
 */
public class RegisterDTO implements Serializable {

	private static final long serialVersionUID = -7027212090907819908L;
	
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
	/**所在城市*/
	private String area;
	/**主要发展单位*/
	private String userComp;
	/** 验证码 */
	@Length(min = 6, max = 6)
	private String captcha;
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getUserComp() {
		return userComp;
	}
	public void setUserComp(String userComp) {
		this.userComp = userComp;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
}
