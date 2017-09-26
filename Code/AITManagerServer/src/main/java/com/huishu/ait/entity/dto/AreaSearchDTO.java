package com.huishu.ait.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.common.SearchModel;

/**
 * 辖区情报和政策的DTO
 * 
 * @author yindq
 * @date 2017年8月3日
 */
public class AreaSearchDTO extends SearchModel {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/** 园区 */
	private String park;

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
