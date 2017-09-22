package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年8月10日
 * @Parem
 * @return
 * 
 */
@Entity
@Table(name = "t_enterprise")
public class Enterprise implements Serializable {

	private static final long serialVersionUID = 8373154264210121342L;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	/** 产业类型 */
	@Column(name = "industry_type")
	private String industryType;
	/** 公司名 */
	private String company;
	/** 公司联系方式 */
	private String phone;
	/** 公司邮箱 */
	private String email;
	/** 公司网址 */
	private String url;
	/** 公司地址 */
	private String address;
	/** 法定代表 */
	private String boss;
	/** 注册资金 */
	@Column(name = "register_capital")
	private String registerCapital;
	/** 经营状态 */
	@Column(name = "engage_state")
	private String engageState;
	/** 注册时间 */
	@Column(name = "register_time")
	private String registerTime;
	/** 行业领域 */
	private String industry;
	/** 工商注册号 */
	@Column(name = "icRegister_no")
	private String icRegisterNo;
	/** 企业类型 */
	@Column(name = "company_type")
	private String companyType;
	/** 组织机构代码 */
	@Column(name = "orgMechanism_no")
	private String orgMechanismNo;
	/** 经营期限 */
	@Column(name = "business_date")
	private String businessDate;
	/** 登记机关 */
	@Column(name = "register_agency")
	private String registerAgency;
	/** 核准日期 */
	@Column(name = "examine_time")
	private String examineTime;
	/** 注册地址 */
	@Column(name = "register_address")
	private String registerAddress;
	/** 经营范围 */
	@Column(name = "operate_scope")
	private String operateScope;
	/** 所属园区 */
	private String park;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public String getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}

	public String getEngageState() {
		return engageState;
	}

	public void setEngageState(String engageState) {
		this.engageState = engageState;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIcRegisterNo() {
		return icRegisterNo;
	}

	public void setIcRegisterNo(String icRegisterNo) {
		this.icRegisterNo = icRegisterNo;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getOrgMechanismNo() {
		return orgMechanismNo;
	}

	public void setOrgMechanismNo(String orgMechanismNo) {
		this.orgMechanismNo = orgMechanismNo;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getRegisterAgency() {
		return registerAgency;
	}

	public void setRegisterAgency(String registerAgency) {
		this.registerAgency = registerAgency;
	}

	public String getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public String getOperateScope() {
		return operateScope;
	}

	public void setOperateScope(String operateScope) {
		this.operateScope = operateScope;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
