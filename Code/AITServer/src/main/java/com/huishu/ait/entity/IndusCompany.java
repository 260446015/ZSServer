package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 智能企业实体类
 * 
 * @author hhy
 * @date 2017年8月10日
 * @Parem
 * @return
 * 
 */
@Entity
@Table(name = "t_indus_company")
public class IndusCompany implements Serializable {

	private static final long serialVersionUID = -4832242390727880793L;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * 产业 -- 大数据
	 */
	@Column(name = "t_industry")
	private String industry;
	/**
	 * 公司
	 */
	@Column(name = "t_company")
	private String company;
	/**
	 * 公司别名
	 */
	@Column(name = "t_company_name")
	private String companyName;

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
