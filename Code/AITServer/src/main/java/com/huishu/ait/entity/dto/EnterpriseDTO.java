package com.huishu.ait.entity.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date
 * 
 * @Parem
 * @return 
 * 
 */
@Entity
@Table(name="t_enterprise")
public class EnterpriseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	
	/**产业类型*/
	@Column(name="industry_type")
	private String industryType;
	
	/**公司名*/
	private String company;
	
	/**法定代表*/
	private String  boss;
	
	/**注册资金*/
	@Column(name="register_capital")
	private String registerCapital;
	
	/**注册时间*/
	@Column(name="register_time")
	private String createTime;
	
	/**行业领域*/
	private String industry;
	
	/**注册地址*/
	@Column(name="register_address")
	private String registerAddress;
	
	/**天眼查超链接*/
	@Column(name="company_url" )
	private String companyUrl;

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	

}
