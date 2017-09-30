package com.huishu.ait.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.common.SearchModel;

/**
 * 园区列表查询DTO
 * 
 * @author yindq
 * @create 2017年9月7日
 */
public class GardenSearchDTO extends SearchModel {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/** 地域 */
	private String area;
	/** 会员级别 */
	private String type;
	/** 时间 */
	private String day;
	/** 搜索条件 */
	private String search;
	private String[] msg = {};

	public String getArea() {
		if ("全部".equals(area)) {
			area = "%%";
		}
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getType() {
		if ("全部".equals(type)) {
			type = "%%";
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getSearch() {
		if (StringUtil.isEmpty(search)) {
			search = "%%";
		} else if(!search.equals("%%")){
			search = "%" + search + "%";
		}
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String[] getMsg() {
		return msg;
	}

	public void setMsg(String[] msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
