package com.huishu.ait.entity.dto;

import java.io.Serializable;

/**
 * 添加正式用户DTO
 * 
 * @author yindq
 * @create 2017年9月19日
 */
public class AddAccountDTO implements Serializable {
	/**
	 * 可序列化
	 */
	private static final long serialVersionUID = 1L;
	/** 手机 */
	private String telphone;
	/** 姓名 */
	private String name;
	/** 邮箱 */
	private String userEmail;
	/** 园区名称 */
	private String park;
	private String area;
	/** 公司名称 */
	private String company;
	/** 部门名称 */
	private String department;
	/** 所属职务 */
	private String job;
	/** 用户类型 */
	private String userType;
	/** 试用期限 */
	private String time;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
