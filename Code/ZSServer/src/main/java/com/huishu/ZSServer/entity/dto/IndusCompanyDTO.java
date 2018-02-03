package com.huishu.ZSServer.entity.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年11月9日
 * @Parem
 * @return 
 * 
 */
@Entity
@Table(name="t_enterprise")
public class IndusCompanyDTO implements Serializable{

	private static final long serialVersionUID = 270850549795604366L;
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	//产业
	@Column(name = "industry")
	private String industry;
	
	//公司全称
	@Column(name = "company")
	private String company;
	
	//公司简称
	@Column(name = "t_company_name")
	private String companyName;

	//所属战略新兴产业
	@Column(name = "t_industry_zero")
	private String induszero;
	
	//行业标签
	@Column(name = "t_industry_label")
	private String industryLabel;

	@Column(name = "t_create_time")
	private String createTime;
	
	@Column(name = "t_update_time")
	private String updateTime;
	
	public String getInduszero() {
		return induszero;
	}

	public void setInduszero(String induszero) {
		this.induszero = induszero;
	}

	public String getIndustryLabel() {
		return industryLabel;
	}

	public void setIndustryLabel(String industryLabel) {
		this.industryLabel = industryLabel;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
	
}
