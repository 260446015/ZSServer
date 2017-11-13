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
	private String financingAmount;
	/** 融资时间 */
	@Column(name = "financing_date")
	private String financingDate;
	/** 投资方 */
	private String investor;
	/** 公司规模 */
	private Integer scale;
	/** 二级产业 */
	private String industryLabel;
	/**天眼查产业标签*/
	private String openIndustry;
	/**天眼查实收注册资金*/
	private String openActualCapital;
	/**天眼查省份简称*/
	private String openBase;
	/**天眼查经营范围*/
	@Column(length=1000)
	private String openBusinessScope;
	/**天眼查行业评分（万分制）*/
	private Integer openCategoryScore;
	/**天眼查公司类型*/
	private String openCompanyOrgType;
	/**天眼查曾用名id*/
	private String openCorrectCompanyId;
	/**统一社会信用代码*/
	private String openCreditCode;
	/**天眼查企业成立时间*/
	private Long openEstiblishTime; 
	/**天眼查营业期限开始时间*/
	private Long openFromTime;
	/**天眼查法人id*/
	private Long openLegalPersonId;
	/**天眼查法人名称*/
	private String openLegalPersonName;
	/**天眼查核准机构*/
	private String openOrgApprovedInstitute;
	/**天眼查组织机构代码*/
	private String openOrgNumber;
	/**天眼查公司评分*/
	private Integer openPercentileScore;
	/**天眼查联系方式*/
	private String openPhoneNumber;
	/**天眼查登记机关*/
	private String openRegInstitute;
	/**天眼查注册号*/
	private String openRegNumber;
	/**天眼查营业期限结束时间*/
	private Long openToTime;
	/**天眼查法人类型，1 人 2 公司*/
	private Integer openType;
	 
	

	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	public Long getOpenToTime() {
		return openToTime;
	}

	public void setOpenToTime(Long openToTime) {
		this.openToTime = openToTime;
	}

	public String getOpenRegNumber() {
		return openRegNumber;
	}

	public void setOpenRegNumber(String openRegNumber) {
		this.openRegNumber = openRegNumber;
	}

	public String getOpenRegInstitute() {
		return openRegInstitute;
	}

	public void setOpenRegInstitute(String openRegInstitute) {
		this.openRegInstitute = openRegInstitute;
	}

	public String getOpenPhoneNumber() {
		return openPhoneNumber;
	}

	public void setOpenPhoneNumber(String openPhoneNumber) {
		this.openPhoneNumber = openPhoneNumber;
	}

	public Integer getOpenPercentileScore() {
		return openPercentileScore;
	}

	public void setOpenPercentileScore(Integer openPercentileScore) {
		this.openPercentileScore = openPercentileScore;
	}

	public String getOpenOrgNumber() {
		return openOrgNumber;
	}

	public void setOpenOrgNumber(String openOrgNumber) {
		this.openOrgNumber = openOrgNumber;
	}

	public String getOpenOrgApprovedInstitute() {
		return openOrgApprovedInstitute;
	}

	public void setOpenOrgApprovedInstitute(String openOrgApprovedInstitute) {
		this.openOrgApprovedInstitute = openOrgApprovedInstitute;
	}

	public String getOpenLegalPersonName() {
		return openLegalPersonName;
	}

	public void setOpenLegalPersonName(String openLegalPersonName) {
		this.openLegalPersonName = openLegalPersonName;
	}

	public Long getOpenLegalPersonId() {
		return openLegalPersonId;
	}

	public void setOpenLegalPersonId(Long openLegalPersonId) {
		this.openLegalPersonId = openLegalPersonId;
	}

	public Long getOpenFromTime() {
		return openFromTime;
	}

	public void setOpenFromTime(Long openFromTime) {
		this.openFromTime = openFromTime;
	}

	public Long getOpenEstiblishTime() {
		return openEstiblishTime;
	}

	public void setOpenEstiblishTime(Long openEstiblishTime) {
		this.openEstiblishTime = openEstiblishTime;
	}

	public String getOpenCreditCode() {
		return openCreditCode;
	}

	public void setOpenCreditCode(String openCreditCode) {
		this.openCreditCode = openCreditCode;
	}

	public String getOpenCorrectCompanyId() {
		return openCorrectCompanyId;
	}

	public void setOpenCorrectCompanyId(String openCorrectCompanyId) {
		this.openCorrectCompanyId = openCorrectCompanyId;
	}

	public String getOpenCompanyOrgType() {
		return openCompanyOrgType;
	}

	public void setOpenCompanyOrgType(String openCompanyOrgType) {
		this.openCompanyOrgType = openCompanyOrgType;
	}

	public Integer getOpenCategoryScore() {
		return openCategoryScore;
	}

	public void setOpenCategoryScore(Integer openCategoryScore) {
		this.openCategoryScore = openCategoryScore;
	}

	public String getOpenBusinessScope() {
		return openBusinessScope;
	}

	public void setOpenBusinessScope(String openBusinessScope) {
		this.openBusinessScope = openBusinessScope;
	}

	public String getOpenBase() {
		return openBase;
	}

	public void setOpenBase(String openBase) {
		this.openBase = openBase;
	}

	public String getOpenActualCapital() {
		return openActualCapital;
	}

	public void setOpenActualCapital(String openActualCapital) {
		this.openActualCapital = openActualCapital;
	}

	public String getOpenIndustry() {
		return openIndustry;
	}

	public void setOpenIndustry(String openIndustry) {
		this.openIndustry = openIndustry;
	}

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

	public String getFinancingAmount() {
		return financingAmount;
	}

	public void setFinancingAmount(String financingAmount) {
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
