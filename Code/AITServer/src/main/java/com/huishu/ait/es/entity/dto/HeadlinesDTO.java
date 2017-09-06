package com.huishu.ait.es.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年7月28日
 * @Parem
 * @return
 * 产业头条的dto
 */
public class HeadlinesDTO extends AbstractDTO {

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
	/** 词云关键词 */
	private String keyWord;
	/** 词云数目 */
	private Integer wordCloudNum;
	/** 数组，用来接收前台传过来的参数 */
	private String[] msg;
	private String dimension;
	
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

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String[] getMsg() {
		return msg;
	}

	public void setMsg(String[] msg) {
		this.msg = msg;
	}

	public Integer getWordCloudNum() {
		return wordCloudNum;
	}

	public void setWordCloudNum(Integer wordCloudNum) {
		this.wordCloudNum = wordCloudNum;
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
