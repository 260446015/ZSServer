package com.huishu.ZSServer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：企业信息的实体类
 */
@Entity
@Table(name = "t_company_data")
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4365522363158952716L;
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	/** 企业 */
	@Column(name = "company_name")
	private String companyName;
	/** 企业法人 */
	private String boss;
	/** 所属园区 */
	private String park;
	/** 经营状况 */
	@Column(name = "engage_state")
	private String engageState;
	/** 注册资金 */
	@Column(name = "register_capital")
	private Double registerCapital;
	/** 注册时间 */
	@Column(name = "register_date")
	private String registerDate;
	/** 公司详细地址 */
	private String address;
	/** 公司所属地区 */
	private String area;
	/** 公司logo */
	private String logo;
	/** 企业类型 */
	private String industry;
	/** 融资轮次 */
	private String invest;
	/** 融资金额 */
	@Column(name = "financing_amount")
	private Double financingAmount;
	/** 融资时间 */
	@Column(name = "financing_date")
	private String financingDate;
	/** 投资方 */
	private String investor;
	/** 公司规模 */
	private Integer scale;
	/** 二级产业 */
	private String industryLabel;

	public String getFinancingDate() {
		return financingDate;
	}

	public void setFinancingDate(String financingDate) {
		this.financingDate = financingDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getEngageState() {
		return engageState;
	}

	public void setEngageState(String engageState) {
		this.engageState = engageState;
	}

	public Double getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(Double registerCapital) {
		this.registerCapital = registerCapital;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getInvest() {
		return invest;
	}

	public void setInvest(String invest) {
		this.invest = invest;
	}

	public Double getFinancingAmount() {
		return financingAmount;
	}

	public void setFinancingAmount(Double financingAmount) {
		this.financingAmount = financingAmount;
	}

	public String getInvestor() {
		return investor;
	}

	public void setInvestor(String investor) {
		this.investor = investor;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getIndustryLabel() {
		return industryLabel;
	}

	public void setIndustryLabel(String industryLabel) {
		this.industryLabel = industryLabel;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
