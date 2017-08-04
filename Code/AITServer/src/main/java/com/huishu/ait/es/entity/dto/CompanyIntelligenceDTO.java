package com.huishu.ait.es.entity.dto;

/**
 * @author yxq
 * @date 2017年8月4日
 * @功能描述：企业情报画像dto
 */
public class CompanyIntelligenceDTO extends AbstractDTO{
	
	/** 所属园区 */
    private String park;
    
    /** 企业名称 */
    private String business;
    
    /** 查询关键字 */
    private String keyword;
    
    /** 情感 */
    private String emotion;
    
    /**
     * 开始时间和结束时间
     */
    private String startDate;
    private String endDate;
    
    /** 分页 */
    private Integer pageNumber;
    private Integer pageSize;
    
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
    
}
