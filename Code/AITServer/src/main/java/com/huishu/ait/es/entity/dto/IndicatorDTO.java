package com.huishu.ait.es.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年9月6日
 * @Parem
 * @return 
 * 产业智图的参数dto
 */
public class IndicatorDTO {
	
	private String firstIndicator;
	
	private String secondIndicator;
	
	private String thirdIndicator;
	
	private String  fourIndicator;

	public String getFirstIndicator() {
		return firstIndicator;
	}

	public void setFirstIndicator(String firstIndicator) {
		this.firstIndicator = firstIndicator;
	}

	public String getSecondIndicator() {
		return secondIndicator;
	}

	public void setSecondIndicator(String secondIndicator) {
		this.secondIndicator = secondIndicator;
	}

	public String getThirdIndicator() {
		return thirdIndicator;
	}

	public void setThirdIndicator(String thirdIndicator) {
		this.thirdIndicator = thirdIndicator;
	}

	public String getFourIndicator() {
		return fourIndicator;
	}

	public void setFourIndicator(String fourIndicator) {
		this.fourIndicator = fourIndicator;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
	
}
