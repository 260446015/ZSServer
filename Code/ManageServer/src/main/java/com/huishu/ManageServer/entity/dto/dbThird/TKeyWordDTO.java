package com.huishu.ManageServer.entity.dto.dbThird;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.common.util.ConcersUtils;

/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 * 
 */
public class TKeyWordDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String type;
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
