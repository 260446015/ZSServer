package com.huishu.ZSServer.entity.garden;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月27日上午9:22:22
 * @description 映射园区产业的实体
 * @version
 */
@Table(name="t_gardenindustry")
@Entity
public class GardenIndustry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1027847428692696873L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 一级产业指标 */
	private String industryOne;
	/** 二级产业指标 */
	private String industryTwo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndustryOne() {
		return industryOne;
	}

	public void setIndustryOne(String industryOne) {
		this.industryOne = industryOne;
	}

	public String getIndustryTwo() {
		return industryTwo;
	}

	public void setIndustryTwo(String industryTwo) {
		this.industryTwo = industryTwo;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
