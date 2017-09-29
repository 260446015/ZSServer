package com.huishu.ait.es.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.common.SearchModel;

/**
 * 查询园区动态
 * 
 * @author yindq
 * @create 2017年9月29日
 */
public class BusinessSuperviseDTO extends SearchModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 所属园区 */
	private String park;
	private String dimension;


	public String getDimension() {
		return dimension;
	}


	public void setDimension(String dimension) {
		this.dimension = dimension;
	}


	public String getPark() {
		return park;
	}


	public void setPark(String park) {
		this.park = park;
	}


	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
