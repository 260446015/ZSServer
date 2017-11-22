package com.huishu.ZSServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * 按融资产业条件查询DTO
 * 
 * @author yindq
 * @date 2017年11月22日
 */
public class FinancingSearchDTO extends AbstractDTO{

	private static final long serialVersionUID = 1L;
	private String type;
	private String[] industry;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getIndustry() {
		return industry;
	}

	public void setIndustry(String[] industry) {
		this.industry = industry;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}