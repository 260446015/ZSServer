package com.huishu.ZSServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author Administrator
 * 精准筛选实体类
 */
public class SearchDTO {
	/**产业*/
	private String industry;
	/**成立资本*/
	private String register;
	/**成立时间*/
	private String registerTime;
	/**地域*/
	private String area ;
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
