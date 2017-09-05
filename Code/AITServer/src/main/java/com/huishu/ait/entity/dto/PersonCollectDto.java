package com.huishu.ait.entity.dto;

import java.io.Serializable;

public class PersonCollectDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8590230340523181256L;
	/**
	 * 分页中每页大小
	 */
	private Integer pageSize;
	/**
	 * 分页当前数
	 */
	private Integer pageNumber;
	/**
	 * 查询的内容
	 */
	private String query;
	/**
	 * 用户id
	 */
	private Long userId;

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

	public String getQuery() {
		return "%"+query+"%";
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
