package com.huishu.ZSServer.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月3日上午9:58:15
 * @description 园区对比的实体类
 * @version
 */
@Table(name = "t_garden_compare")
@Entity
public class GardenCompare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3208437527106811040L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 园区名称 */
	private String gardenName;
	/** 园区图片 */
	private String logo;
	/** 对比园区的id */
	private Long gardenId;
	/** 园区面积 */
	private Double gardenSquare;
	/** 入驻企业 */
	private String enterCompany;
	/** 园区产值 */
	private Double gdp;
	/** 用户id */
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/** 产业占比 */
	@Transient
	private Map<Object, Object> industryCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGardenName() {
		return gardenName;
	}

	public void setGardenName(String gardenName) {
		this.gardenName = gardenName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Long getGardenId() {
		return gardenId;
	}

	public void setGardenId(Long gardenId) {
		this.gardenId = gardenId;
	}

	public Double getGardenSquare() {
		return gardenSquare;
	}

	public void setGardenSquare(Double gardenSquare) {
		this.gardenSquare = gardenSquare;
	}

	public String getEnterCompany() {
		return enterCompany;
	}

	public void setEnterCompany(String enterCompany) {
		this.enterCompany = enterCompany;
	}

	public Double getGdp() {
		return gdp;
	}

	public void setGdp(Double gdp) {
		this.gdp = gdp;
	}

	public Map<Object, Object> getIndustryCount() {
		return industryCount;
	}

	public void setIndustryCount(Map<Object, Object> industryCount) {
		this.industryCount = industryCount;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
