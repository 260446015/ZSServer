package com.huishu.ZSServer.entity.dto;

import java.io.Serializable;

import com.huishu.ZSServer.common.util.ConcersUtils;



/**
 * dto基类
 * 
 * @author yindawei
 * @date 2017年10月27日上午11:44:57
 * @description 所有的dto都应该继承此类
 * @version
 */
public class AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6486209189459205800L;
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
		this.pageNumber = pageNumber;
	}

}
