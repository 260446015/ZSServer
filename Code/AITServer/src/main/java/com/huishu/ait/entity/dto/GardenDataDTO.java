package com.huishu.ait.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.common.SearchModel;

/**
 * 园区数据展示DTO
 * 
 * @author yindq
 * @create 2017年9月7日
 */
public class GardenDataDTO extends SearchModel {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
