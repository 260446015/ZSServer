package com.huishu.aitanalysis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

@Entity
@Table(name = "t_us_indus")
public class IndustryInfo implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "t_indus_one") 
	private String industryOne;
	
	@Column(name = "t_indus_two") 
	private String industryTwo;
	
	@Column(name = "t_indus_three") 
	private String industryThree;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public String getIndustryOne() {
		return industryOne;
	}

	public String getIndustryTwo() {
		return industryTwo;
	}

	public String getIndustryThree() {
		return industryThree;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIndustryOne(String industryOne) {
		this.industryOne = industryOne;
	}

	public void setIndustryTwo(String industryTwo) {
		this.industryTwo = industryTwo;
	}

	public void setIndustryThree(String industryThree) {
		this.industryThree = industryThree;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
	
}
