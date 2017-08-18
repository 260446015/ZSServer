package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：企业信息的实体类
 */
@Entity
@Table(name = "t_company")
public class Company implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4365522363158952716L;
	@Id
	@GeneratedValue
	@Column(name = "cid", nullable = false)
	private Long cid;
	@Column(name = "company_id")
	private String companyId;
	@Column(name = "company_name")
	private String companyName;
	//建议采用多对一
	@ManyToOne(cascade=CascadeType.REFRESH,optional=false)
	@JoinColumn(name = "group_id",nullable=false)
	private CompanyGroup companyGroup;
	@Column(name="business_legal")
	private String businessLegal;
	/**
	 * 所属园区
	 */
	private String park;
	/**
	 * 所属地域
	 */
	private String area;
	/**
	 * 注册资本
	 */
	@Column(name="register_capital")
	private Double registerCapital;
	/**
	 * 注册时间
	 */
	@Column(name="register_date")
	private String registerDate;
	private String logo;
	private String industry;
	
	
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public CompanyGroup getCompanyGroup() {
		return companyGroup;
	}
	public void setCompanyGroup(CompanyGroup companyGroup) {
		this.companyGroup = companyGroup;
	}
	public String getBusinessLegal() {
		return businessLegal;
	}
	public void setBusinessLegal(String businessLegal) {
		this.businessLegal = businessLegal;
	}
	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
