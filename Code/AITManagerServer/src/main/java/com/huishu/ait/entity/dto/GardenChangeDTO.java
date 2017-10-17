package com.huishu.ait.entity.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 修改园区信息的DTO
 * 
 * @author yindq
 * @create 2017年10月17日
 */
public class GardenChangeDTO implements Serializable {

	private static final long serialVersionUID = 333324299374920031L;

	private String id;
	
	private String gardenName;

	private String area;

	private String address;

	private String gardenLevel;

	private String establishDate;

	private String gardenSquare;
	/** 主导产业一级目录 */
	private String industry;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGardenName() {
		return gardenName;
	}

	public void setGardenName(String gardenName) {
		this.gardenName = gardenName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGardenLevel() {
		return gardenLevel;
	}

	public void setGardenLevel(String gardenLevel) {
		this.gardenLevel = gardenLevel;
	}

	public String getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(String establishDate) {
		this.establishDate = establishDate;
	}

	public String getGardenSquare() {
		return gardenSquare;
	}

	public void setGardenSquare(String gardenSquare) {
		this.gardenSquare = gardenSquare;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
