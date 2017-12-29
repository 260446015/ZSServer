package com.huishu.ManageServer.entity.dto;

import java.io.Serializable;

import com.huishu.ManageServer.common.util.ConcersUtils;
import org.springframework.data.domain.PageRequest;

public class AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3470443730465187413L;
	private Integer pageNum;
	private Integer pageSize;
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

	public Integer getPageNum() {
		if (null == pageNum)
			pageNum = ConcersUtils.ES_MIN_PAGENUMBER;
		if (pageNum > ConcersUtils.ES_MAX_PAGENUMBER)
			setPageNum(ConcersUtils.ES_MAX_PAGENUMBER);
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		if (null == pageNum)
			pageNum = ConcersUtils.ES_MIN_PAGENUMBER;
		if (pageNum > ConcersUtils.ES_MAX_PAGENUMBER)
			setPageNum(ConcersUtils.ES_MAX_PAGENUMBER);
		this.pageNum = pageNum;
	}
	protected PageRequest getPageRequest(){
		return new PageRequest(this.pageNum, this.pageSize);
	}
}
