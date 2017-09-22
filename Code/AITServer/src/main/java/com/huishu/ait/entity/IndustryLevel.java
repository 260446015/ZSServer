package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 产业等级实体类
 * 
 * @author yindq
 * @date 2017年9月1日
 */
@Table(name = "t_industry_level")
@Entity
public class IndustryLevel implements Serializable {

	private static final long serialVersionUID = 5200560994830588853L;
	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** 产业 */
	@Column(name = "industry_name")
	private String industryName;

	/** 产业等级 */
	@Column(name = "level")
	private Integer level;

	/** 父级ID */
	@Column(name = "father_id")
	private Long fatherId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
