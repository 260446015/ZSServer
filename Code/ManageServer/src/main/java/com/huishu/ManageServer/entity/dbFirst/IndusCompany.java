package com.huishu.ManageServer.entity.dbFirst;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="t_indus_company")
public class IndusCompany implements Serializable{

	private static final long serialVersionUID = 270850549795604366L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	//产业
	@Column(name = "t_industry")
	private String industry;
	
	//公司全称
	@Column(name = "t_company")
	private String company;
	
	//公司简称
	@Column(name = "t_company_name")
	private String companyName;

	//所属战略新兴产业
	@Column(name = "t_indus_zero")
	private String induszero;
	
	//行业标签
	@Column(name = "t_industry_label")
	private String industryLabel;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
	
}
