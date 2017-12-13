package com.huishu.ZSServer.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.util.StringUtil;

/**
 * 招商报告筛选实体类
 * 
 * @author yindq
 * @date 2017年12月13日
 */
public class ReportSearchDTO extends AbstractDTO{
	private static final long serialVersionUID = 1L;
	/** 年份 */
	private String year;
	/** 类型(season,month) */
	private String type;
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
