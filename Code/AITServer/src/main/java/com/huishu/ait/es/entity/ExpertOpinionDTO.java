package com.huishu.ait.es.entity;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author yxq
 *	专家观点的dto
 */
@Document(indexName = INDEX, type = TYPE)
public class ExpertOpinionDTO extends AITInfo {
	
	/**开始时间*/
	private  String startDate ;
	/**结束时间*/
	private String endDate;
	/*根据热度排序标识*/
	private String SortByHotFlag;
	/*根据时间排序标识*/
	private String SortByTimeFlag;
	/*专家观点所属栏目*/
	private String lanmu;
	
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
}
