package com.huishu.ZSServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * 融资企业查询DTO
 * 
 * @author yindq
 * @date 2017年10月30日
 */
public class CompanySearchDTO extends AbstractDTO{

	private static final long serialVersionUID = 1L;
	/** 园区 */
	private String park;
	private String[] msg;
	/** 排序 */
	private String sort;
	/** 融资轮次 */
	private String invest;
	/** 地域 */
	private String area;
	/** 产业 */
	private String industry;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getInvest() {
		return invest;
	}
	public void setInvest(String invest) {
		this.invest = invest;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
