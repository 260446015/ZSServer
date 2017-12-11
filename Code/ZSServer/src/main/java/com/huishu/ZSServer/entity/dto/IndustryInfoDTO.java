package com.huishu.ZSServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年11月29日
 * @Parem
 * @return 
 * 
 */
public class IndustryInfoDTO extends AbstractDTO{

	private static final long serialVersionUID = -3800185331043312571L;
	
	private String industry;
	private String area;
	private String sort;
	
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
}
