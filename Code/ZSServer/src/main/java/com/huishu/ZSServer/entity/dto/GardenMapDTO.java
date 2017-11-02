package com.huishu.ZSServer.entity.dto;

import com.huishu.ZSServer.entity.garden.GardenMap;

public class GardenMapDTO extends GardenMap{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3008676197909687580L;
	
	/**返回echarts产业数量*/
	private Integer industryCount;

	public Integer getIndustryCount() {
		return industryCount;
	}

	public void setIndustryCount(Integer industryCount) {
		this.industryCount = industryCount;
	}
	
	

}
