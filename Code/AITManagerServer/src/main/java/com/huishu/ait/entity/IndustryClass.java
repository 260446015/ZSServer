package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author yindawei
 * @date 2017年8月28日上午9:24:16
 * @description 映射地区产业数量的实体类
 * @version
 */
@Table(name = "t_industry_class")
@Entity
public class IndustryClass implements Serializable {

	private static final long serialVersionUID = 5200560994830588853L;
	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 地区 */
	private String area;
	/** 产业 */
	private String industry;
	/** 数量 */
	private Integer count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
