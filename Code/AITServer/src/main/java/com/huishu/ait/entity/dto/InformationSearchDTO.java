package com.huishu.ait.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.common.SearchModel;

/**
 * 园区信息变更DTO
 * 
 * @author yindq
 * @create 2017年9月12日
 */
public class InformationSearchDTO extends SearchModel {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/** 园区 */
	private String park;
	
	private String search;
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
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
