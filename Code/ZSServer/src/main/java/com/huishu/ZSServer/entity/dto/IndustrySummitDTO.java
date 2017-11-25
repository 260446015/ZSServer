package com.huishu.ZSServer.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.util.ConcersUtils;

/**
 * @author hhy
 * @date 2017年11月24日
 * @Parem
 * @return 
 * 产业峰会抽象实体
 */
public class IndustrySummitDTO  {

	private static final long serialVersionUID = 7690765158755358983L;
	private String [] msg;
	private String industry;
	private String area;
	private String sort;
	/**
	 * 分页中每页大小
	 */
	private Integer pageSize;
	/**
	 * 分页当前数
	 */
	private Integer pageNumber;

	public Integer getPageSize() {
		if (null == pageSize)
			pageSize = ConcersUtils.PAGE_SIZE;
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (null == pageSize)
			pageSize = ConcersUtils.PAGE_SIZE;
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		if (null == pageNumber)
			pageNumber = ConcersUtils.ES_MIN_PAGENUMBER;
		if (pageNumber > ConcersUtils.ES_MAX_PAGENUMBER)
			setPageNumber(ConcersUtils.ES_MAX_PAGENUMBER);
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		if (null == pageNumber)
			pageNumber = ConcersUtils.ES_MIN_PAGENUMBER;
		if (pageNumber > ConcersUtils.ES_MAX_PAGENUMBER)
			setPageNumber(ConcersUtils.ES_MAX_PAGENUMBER);
		this.pageNumber = pageNumber;
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
	public String getArea() {
		return area;
	}
	public void setAreas(String area) {
		this.area = area;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
}
