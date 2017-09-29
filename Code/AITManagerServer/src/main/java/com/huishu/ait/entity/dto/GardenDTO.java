package com.huishu.ait.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.common.SearchModel;

/**
 * 查询园区的DTO
 * 
 * @author yindq
 * @create 2017年9月28日
 */
public class GardenDTO extends SearchModel {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/** 产业类型 */
	private String type;
	/** 地域 */
	private String area;
	/** 搜索条件 */
	private String search;
	public String getType() {
		if (StringUtil.isEmpty(type)||type.equals("不限")) {
			type = "%%";
		} else {
			type = "%" + type + "%";
		}
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getArea() {
		if (StringUtil.isEmpty(area)||area.equals("不限")) {
			area = "%%";
		} else {
			area = "%" + area + "%";
		}
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSearch() {
		if (StringUtil.isEmpty(search)) {
			search = "%%";
		} else {
			search = "%" + search + "%";
		}
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
