package com.huishu.ait.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * 注册用户DTO
 * 
 * @author yindq
 * @date 2017年8月24日
 */
public class RegisterDTO implements Serializable {
	/**
	 * 可序列化
	 */
	private static final long serialVersionUID = 1L;

	/** 手机号 */
	@Pattern(regexp = "^((13[0-9])|(14[57])|(15[0-9])|(17[01678])|(18[0-9]))\\d{8}$")
	private String telphone;
	/** 验证码 */
	@Length(min = 6, max = 6)
	private String captcha;
	/** 邮箱 */
	@Email
	private String userEmail;
	/** 园区名称 */
	@Length(min = 4, max = 20)
	private String park;
	/** 公司名称 */
	private String company;
	/** 部门名称 */
	@Length(min = 2, max = 20)
	private String department;
	/** 所属职务 */
	private String job;
	/** 用户类型 */
	@Length(min = 4, max = 5)
	private String userType;
	private String imageUrl;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
