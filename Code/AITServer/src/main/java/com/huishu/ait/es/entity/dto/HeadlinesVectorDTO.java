package com.huishu.ait.es.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年7月28日
 * @Parem
 * @return 产业头条的dto
 */
public class HeadlinesVectorDTO extends AbstractDTO {

	/** 产业 */
	private String industry;
	/** 产业标签 */
	private String industryLabel;
	/** 载体 */
	private String vector;
	/** 开始时间 */
	private String startDate;
	/** 结束时间 */
	private String endDate;
	/** 时间段 */
	private String periodDate;
	
	/** 数组，用来接收前台传过来的参数 */
	private String[] msg;
	private String dimension;
	
	/** 每页数据存储个数 */
	private Integer pageSize;

	/** 当前页码 */
	private Integer pageNumber;
	
	

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getPeriodDate() {
		return periodDate;
	}

	public void setPeriodDate(String periodDate) {
		this.periodDate = periodDate;
	}

	

	public String[] getMsg() {
		return msg;
	}

	public void setMsg(String[] msg) {
		this.msg = msg;
	}

	

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIndustryLabel() {
		return industryLabel;
	}

	public void setIndustryLabel(String industryLabel) {
		this.industryLabel = industryLabel;
	}

	public String getVector() {
		return vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
