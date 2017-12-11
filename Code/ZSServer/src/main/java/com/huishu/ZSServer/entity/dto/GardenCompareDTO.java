package com.huishu.ZSServer.entity.dto;

import java.io.Serializable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 园区对比
 * 
 * @author yindawei
 * @date 2017年11月30日上午10:27:23
 * @description
 * @version
 */
public class GardenCompareDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1594257789528615941L;

	/** 园区名称 */
	private String gardenName;
	/** 园区主导产业年产值 */
	private Double gdp;
	/** 入驻企业数量 */
	private int enterCount;
	/** 占地 */
	private double square;
	/** 每种产业下的企业数量 */
	private JSONArray industryType;

	public String getGardenName() {
		return gardenName;
	}

	public void setGardenName(String gardenName) {
		this.gardenName = gardenName;
	}

	public Double getGdp() {
		return gdp;
	}

	public void setGdp(Double gdp) {
		this.gdp = gdp;
	}

	public int getEnterCount() {
		return enterCount;
	}

	public void setEnterCount(int enterCount) {
		this.enterCount = enterCount;
	}

	public double getSquare() {
		return square;
	}

	public void setSquare(double square) {
		this.square = square;
	}

	public JSONArray getIndustryType() {
		return industryType;
	}

	public void setIndustryType(JSONArray industryType) {
		this.industryType = industryType;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
