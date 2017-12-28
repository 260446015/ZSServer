package com.huishu.ManageServer.entity.dto;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;

public class AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3470443730465187413L;
	private Integer pageNum;
	private Integer pageSize;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	protected PageRequest getPageRequest(){
		return new PageRequest(this.pageNum, this.pageSize);
	}
}
