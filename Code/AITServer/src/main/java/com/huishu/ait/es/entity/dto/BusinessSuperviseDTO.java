package com.huishu.ait.es.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * 园区监管——企业查询DTO
 * 
 * @author jdz
 * @version 1.0
 * @createDate 2017-8-3
 */
public class BusinessSuperviseDTO extends AbstractDTO {

	/** 所属园区 */
	private String park;

	/** 企业名称 */
	private String business;

	/** 查询关键字 */
	private String keyword;

	/** 情感 */
	private String emotion;

	/** 分页 */
	private Integer pageNumber;
	private Integer pageSize;

	/** 数据维度 */
	private String dimension;

	/** 数组型请求参数 */

	private String msg[];

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String[] getMsg() {
		return msg;
	}

	public void setMsg(String[] msg) {
		this.msg = msg;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
