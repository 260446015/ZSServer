package com.huishu.ZSServer.entity.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月24日下午4:11:16
 * @description 某个省份下所有园区产业分布dto
 * @version
 */
public class IndustryCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2316992945640468351L;
	/** 产业类型 */
	private String industryType;
	/** 产业数量 */
	private Integer industryCount;

	public IndustryCount(String industryType, Integer industryCount) {
		super();
		this.industryType = industryType;
		this.industryCount = industryCount;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public Integer getIndustryCount() {
		return industryCount;
	}

	public void setIndustryCount(Integer industryCount) {
		this.industryCount = industryCount;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
