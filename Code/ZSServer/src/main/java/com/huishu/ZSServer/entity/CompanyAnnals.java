package com.huishu.ZSServer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 企业年报数据（年产值、年税收）
 * 
 * @author yindq
 * @date 2017年11月2日
 */
@Entity
@Table(name = "t_company_annals")
public class CompanyAnnals implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	/** 企业 */
	private String companyName;
	/** 所属园区 */
	private String park;
	/** 企业类型 */
	private String industry;
	/** 二级产业 */
	private String industryLabel;
	/** 年份  */
	private String year;
	/** 年产值  */
	private String outputValue;
	/** 年税收  */
	private String taxRevenue;
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getOutputValue() {
		return outputValue;
	}

	public void setOutputValue(String outputValue) {
		this.outputValue = outputValue;
	}

	public String getTaxRevenue() {
		return taxRevenue;
	}

	public void setTaxRevenue(String taxRevenue) {
		this.taxRevenue = taxRevenue;
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

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
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
