package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月3日下午3:57:01
 * @description 存储天眼查企业的基本信息
 * @version
 */
@Table(name = "t_baseinfo")
@Entity
public class BaseInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -391007822605998470L;
	/** id */
	@Id
	private Long companyId;
	/** 天眼查更新时间 */
	private Long updatetime;
	/** 营业期限开始时间 */
	private Long fromTime;
	/** 法人类型，1 人 2 公司 */
	private Integer type;
	/** 行业评分（万分制） */
	private Integer categoryScore;
	/** 公司id */
	private Long id;
	/** 注册号 */
	private String regNumber;
	/** 公司评分（万分制） */
	private Integer percentileScore;
	/** 联系方式 */
	private String phoneNumber;
	/** 注册资金 */
	private String regCapital;
	/** 登记机关 */
	private String regInstitute;
	/** 企业名称 */
	private String name;
	/** 注册地址 */
	private String regLocation;
	/** 行业 */
	private String industry;
	/** 核准时间 */
	private Long approvedTime;
	/** 核准机构 */
	private String orgApprovedInstitute;
	/** 经营范围 */
	private String businessScope;
	/** 组织机构代码 */
	private String orgNumber;
	/** 成立时间 */
	private Long estiblishTime;
	/** 经营状态 */
	private String regStatus;
	/** 法人 */
	private String legalPersonName;
	/** 营业期限结束时间 */
	private Long toTime;
	/** 法人id */
	private Long legalPersonId;
	/** 实收注册资金 */
	private String actualCapital;
	/** 网址 */
	private String websiteList;
	/** 是否显示 */
	private Integer flag;
	/** 曾用名对应现在名id */
	private String correctCompanyId;
	/** 公司类型 */
	private String companyOrgType;
	/** 省份简写 */
	private String base;
	/** 天眼查解析时间 */
	private Long updateTimes;
	/** 统一社会信用代码 */
	private String creditCode;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}

	public Long getFromTime() {
		return fromTime;
	}

	public void setFromTime(Long fromTime) {
		this.fromTime = fromTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCategoryScore() {
		return categoryScore;
	}

	public void setCategoryScore(Integer categoryScore) {
		this.categoryScore = categoryScore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public Integer getPercentileScore() {
		return percentileScore;
	}

	public void setPercentileScore(Integer percentileScore) {
		this.percentileScore = percentileScore;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}

	public String getRegInstitute() {
		return regInstitute;
	}

	public void setRegInstitute(String regInstitute) {
		this.regInstitute = regInstitute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegLocation() {
		return regLocation;
	}

	public void setRegLocation(String regLocation) {
		this.regLocation = regLocation;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Long getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(Long approvedTime) {
		this.approvedTime = approvedTime;
	}

	public String getOrgApprovedInstitute() {
		return orgApprovedInstitute;
	}

	public void setOrgApprovedInstitute(String orgApprovedInstitute) {
		this.orgApprovedInstitute = orgApprovedInstitute;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public Long getEstiblishTime() {
		return estiblishTime;
	}

	public void setEstiblishTime(Long estiblishTime) {
		this.estiblishTime = estiblishTime;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public Long getToTime() {
		return toTime;
	}

	public void setToTime(Long toTime) {
		this.toTime = toTime;
	}

	public Long getLegalPersonId() {
		return legalPersonId;
	}

	public void setLegalPersonId(Long legalPersonId) {
		this.legalPersonId = legalPersonId;
	}

	public String getActualCapital() {
		return actualCapital;
	}

	public void setActualCapital(String actualCapital) {
		this.actualCapital = actualCapital;
	}

	public String getWebsiteList() {
		return websiteList;
	}

	public void setWebsiteList(String websiteList) {
		this.websiteList = websiteList;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getCorrectCompanyId() {
		return correctCompanyId;
	}

	public void setCorrectCompanyId(String correctCompanyId) {
		this.correctCompanyId = correctCompanyId;
	}

	public String getCompanyOrgType() {
		return companyOrgType;
	}

	public void setCompanyOrgType(String companyOrgType) {
		this.companyOrgType = companyOrgType;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Long getUpdateTimes() {
		return updateTimes;
	}

	public void setUpdateTimes(Long updateTimes) {
		this.updateTimes = updateTimes;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
