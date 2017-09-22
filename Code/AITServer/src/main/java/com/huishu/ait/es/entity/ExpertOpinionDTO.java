package com.huishu.ait.es.entity;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import org.springframework.data.elasticsearch.annotations.Document;

import com.huishu.ait.es.entity.dto.AbstractDTO;

/**
 * @author yxq 专家观点的dto
 */
@Document(indexName = INDEX, type = TYPE)
public class ExpertOpinionDTO extends AbstractDTO {

	/** 开始时间 */
	private String startDate;
	/** 结束时间 */
	private String endDate;
	/* 根据热度排序标识 */
	private String SortByHotFlag;
	/* 根据时间排序标识 */
	private String SortByTimeFlag;
	private String timeFlag;
	private String author;
	private String industry;
	private String industryLabel;
	private String[] msg = {};

	/* 专家观点所属栏目 */
	private String lanmu;
	private Integer pageNumber;
	private Integer pageSize;

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

	public String getSortByHotFlag() {
		return SortByHotFlag;
	}

	public void setSortByHotFlag(String sortByHotFlag) {
		SortByHotFlag = sortByHotFlag;
	}

	public String getSortByTimeFlag() {
		return SortByTimeFlag;
	}

	public void setSortByTimeFlag(String sortByTimeFlag) {
		SortByTimeFlag = sortByTimeFlag;
	}

	public String getLanmu() {
		return lanmu;
	}

	public void setLanmu(String lanmu) {
		this.lanmu = lanmu;
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

	public String getTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String[] getMsg() {
		return msg;
	}

	public void setMsg(String[] msg) {
		this.msg = msg;
	}
}
