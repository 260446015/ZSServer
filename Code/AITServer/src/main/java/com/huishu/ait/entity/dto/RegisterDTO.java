package com.huishu.ait.entity.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * 注册用户DTO
 * @author yindq
 * @date 2017年8月24日
 */
public class RegisterDTO {
	/**账号*/
	@Pattern(regexp = "^[a-zA-Z\\d]{3,20}$")
	private String userAccount;
	/**手机号*/
	@Pattern(regexp = "^((13[0-9])|(14[57])|(15[0-9])|(17[01678])|(18[0-9]))\\d{8}$")
	private String telphone;
	/**验证码*/
	@Length(min = 6, max = 6)
	private String captcha;
	/**邮箱*/
	@Email
	private String userEmail;
	/**园区名称*/
	@Length(min = 4, max = 20)
	private String park;
	/**公司名称*/
	@Length(min = 2, max = 20)
	private String company;
	/**部门名称*/
	@Length(min = 2, max = 20)
	private String department;
	/**用户类型*/
	@Length(min = 4, max = 5)
	private String userType;
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
