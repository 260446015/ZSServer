package com.huishu.ait.es.entity.dto;

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
	private String keyword;
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
	public String getKeyWord() {
		return keyword;
	}
	public void setKeyWord(String keyWord) {
		this.keyword = keyWord;
	}
	@Override
	public String toString() {
		return "HeadlinesDTO [industry=" + industry + ", industryLabel=" + industryLabel + ", vector=" + vector
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", keyword=" + keyword + "]";
	}
	
	
	
}
