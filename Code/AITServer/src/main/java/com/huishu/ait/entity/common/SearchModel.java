package com.huishu.ait.entity.common;

public class SearchModel {
	private String park;   //园区名称
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalPage;
	private Integer totalSize;
	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
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
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	
}
