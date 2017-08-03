package com.huishu.ait.es.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年7月28日
 * @Parem
 * @return 
 * 
 */
public class HeadlinesDTO extends AbstractDTO{
	
	/**产业*/
	private String industry;
	/**产业标签*/
	private String industryLabel;
	/**载体*/
	private String vector;
	/**开始时间*/
	private String startDate ;
	/**结束时间*/
	private String endDate;
	/**词云关键词*/
	private String keyWord;
	/**词云数目*/
	private Integer wordCloudNum; 
	
	public String getKeyword() {
		return keyWord;
	}
	public void setKeyword(String keyWord) {
		this.keyWord = keyWord;
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
