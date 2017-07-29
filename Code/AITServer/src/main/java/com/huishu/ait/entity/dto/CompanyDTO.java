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
	 * 当前页码
	 */
	private Integer currentPage;
	/**
     * 每页数据存储个数
     */
    private Integer pageSize;

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

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
