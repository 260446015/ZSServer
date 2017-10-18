package com.huishu.ait.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.common.SearchModel;

/**
 * 后台需求池公司列表查询DTO
 *
 * @author yindq
 * @data 2017年9月21日
 */
public class CompanySearchDTO extends SearchModel {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/** 状态 */
	private String status;
	/** 所属园区 */
	private String park;
	/** 时间 */
	private String time;
	/** 企业标签 */
	private String label;

	private String search;

	private String[] msg = {};

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

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String[] getMsg() {
		return msg;
	}

	public void setMsg(String[] msg) {
		this.msg = msg;
	}

	public String getLabel() {
		if ("全部".equals(label)) {
			status = "%%";
		} else if ("不限".equals(label)) {
			status = "%%";
		}
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		if ("全部".equals(status)) {
			status = "%%";
		} else if ("不限".equals(status)) {
			status = "%%";
		}
		return status;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
