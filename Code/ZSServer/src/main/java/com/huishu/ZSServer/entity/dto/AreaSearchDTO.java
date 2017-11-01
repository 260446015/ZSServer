package com.huishu.ZSServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * 园区动态和政策的DTO
 * 
 * @author yindq
 * @date 2017年11月1日
 */
public class AreaSearchDTO extends AbstractDTO {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/** 园区 */
	private String park;
	/** 维度 */
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
