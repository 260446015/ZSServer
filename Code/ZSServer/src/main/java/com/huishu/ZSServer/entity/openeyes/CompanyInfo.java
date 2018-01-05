package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日下午7:07:37
 * @description 企业简介（股票）
 * @version
 */
@Table(name = "t_companyinfo")
@Entity
public class CompanyInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8929691737177573808L;
	@Id
	private String id;
	/** 邮编 */
	private String postalcode;
	/** 控股股东 */
	private String controllingShareholder;
	/** 实际控股 */
	private String actualController;
	/** 最终控股 */
	private String finalController;
	/** 传真 */
	private String fax;
	/** ⽹址 */
	private String website;
	/** 员⼯数量 */
	private String employeesNum;
	/** 主营业务 */
	private String mainBusiness;
	/** 股票编号 */
	private String code;
	/** 公司名 */
	private String companyName;
	/** 注册资⾦ */
	private String registeredCapital;
	/** 曾⽤名 */
	private String usedName;
	/** 省份 */
	private String area;
	/** 地址 */
	private String address;
	/** 股票名 */
	private String name;
	/** ⾏业 */
	private String industry;
	/** 业务 */
	private String productName;
	/** 英⽂名 */
	private String engName;
	/** 介绍 */
	private String introduction;
	/** 电话 */
	private String mobile;
	/** 法人 */
	private String legal;
	/** 董事⻓ */
	private String chairman;
	/** 总经理 */
	private String generalManager;
	/** 董秘 */
	private String secretaries;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getControllingShareholder() {
		return controllingShareholder;
	}

	public void setControllingShareholder(String controllingShareholder) {
		this.controllingShareholder = controllingShareholder;
	}

	public String getActualController() {
		return actualController;
	}

	public void setActualController(String actualController) {
		this.actualController = actualController;
	}

	public String getFinalController() {
		return finalController;
	}

	public void setFinalController(String finalController) {
		this.finalController = finalController;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmployeesNum() {
		return employeesNum;
	}

	public void setEmployeesNum(String employeesNum) {
		this.employeesNum = employeesNum;
	}

	public String getMainBusiness() {
		return mainBusiness;
	}

	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getUsedName() {
		return usedName;
	}

	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public JSONObject getLegal() {
		return JSONObject.parseObject(this.legal);
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public JSONObject getChairman() {
		return JSONObject.parseObject(this.chairman);
	}

	public void setChairman(String chairman) {
		this.chairman = chairman;
	}

	public JSONObject getGeneralManager() {
		return JSONObject.parseObject(this.generalManager);
	}

	public void setGeneralManager(String generalManager) {
		this.generalManager = generalManager;
	}

	public String getSecretaries() {
		return secretaries;
	}

	public void setSecretaries(String secretaries) {
		this.secretaries = secretaries;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
