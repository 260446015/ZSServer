package com.huishu.ait.entity.dto;

import java.io.Serializable;

/**
 * 
 * @author yindawei
 * @creationTime 2017-07-09
 * @description 企业排行榜查询DTO
 *
 */
public class CompanyDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产业
	 */
	private String industry;
	/**
	 * 产业标签
	 */
	private String industryLabel;
	/**
	 * 发布时间
	 */
	private String publishTime;
	/**
	 * 维度
	 */
	private String dimension;
	/**
	 * 关键字
	 */
	private String keyWord;
	/**
	 * 注册资金
	 */
	private Long regCapital;
	/**
     * 每页数据存储个数
     */
    private Integer pageSize;
    /**
     * 当前页码数
     */
    private Integer pageNumber;
    /**
     * 所属园区
     */
    private String park;
    /**
     * 
     * 传递数组
     */
    private String[] msg;

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

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

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

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Long getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(Long regCapital) {
		this.regCapital = regCapital;
	}
	
}
