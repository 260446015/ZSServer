package com.huishu.ait.entity.common;

import com.huishu.ait.common.conf.ConfConstant;

public class SearchModel {
	/** 园区名称 */
	private String park;   
	/** 当前页 */
	private Integer pageNumber;
	/** 页容量 */
	private Integer pageSize=ConfConstant.DEFAULT_PAGE_SIZE;
	/** 总页数 */
	private Integer totalPage;
	/** 总条数 */
	private Integer totalSize;
	/** 分页查询起始条数 */
	private Integer pageFrom;
	
	public String getPark() {
		return park;
	}
	public Integer getPageFrom() {
		return pageFrom;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		pageFrom=(pageNumber-1)*pageSize>=0?(pageNumber-1)*pageSize:0;
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
	public Integer getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
		totalPage = (totalSize/pageSize)+((totalSize%pageSize)>0?1:0);
		if(pageNumber>totalPage){
			pageNumber=totalPage;
			pageFrom=(pageNumber-1)*pageSize>=0?(pageNumber-1)*pageSize:0;
		}
	}
	@Override
	public String toString() {
		return "SearchModel [park=" + park + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", totalPage="
				+ totalPage + ", totalSize=" + totalSize + ", pageFrom=" + pageFrom + "]";
	}
	
}
