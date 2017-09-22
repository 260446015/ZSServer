package com.huishu.ait.entity;

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
	private Long cid;
	/** 企业 */
	@Column(name = "company_name")
	private String companyName;
	/** 企业法人 */
	private String boss;
	/** 所属园区 */
	private String park;
	/***/
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

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
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

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
