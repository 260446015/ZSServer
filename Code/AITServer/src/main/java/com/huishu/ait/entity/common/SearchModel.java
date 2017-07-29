package com.huishu.ait.entity.common;

import com.huishu.ait.common.conf.ConfConstant;

/**
 * 
 * @author yindq
 * @date
 */
public class SearchModel {
	/** 园区名称 */
	private String park;   
	/** 当前页 */
	private Integer pageNumber;
	/** 页容量 */
	private Integer pageSize;
	/** 总页数 */
	private Integer totalPage;
	/** 总条数 */
	private Integer totalSize;
	/** 分页查询起始条数 */
	private Integer pageFrom;
	
	public SearchModel() {
		if(null == pageSize){
			setPageSize(ConfConstant.DEFAULT_PAGE_SIZE);
		}else if (pageSize>ConfConstant.MAX_PAGE_SIZE){
			setPageSize(ConfConstant.MAX_PAGE_SIZE);
		}else if (pageSize<ConfConstant.MIN_PAGE_SIZE){
			setPageSize(ConfConstant.MIN_PAGE_SIZE);
		}
		if(null == pageNumber){
			setPageNumber(1);
		}else if (pageNumber>ConfConstant.MAX_PAGE_NUMBER){
			setPageNumber(ConfConstant.MAX_PAGE_NUMBER);
		}else if (pageNumber<1){
			setPageNumber(1);
		}
			
	}
	public String getPark() {
		return park;
	}
	public Integer getPageFrom() {
		this.pageFrom = (pageNumber-1)*pageSize>0?(pageNumber-1)*pageSize:0;
		return pageFrom;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		if(pageNumber>0){
			this.pageNumber = pageNumber;
		}
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize>0){
			this.pageSize = pageSize;
		}
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
		}
	}
	@Override
	public String toString() {
		return "SearchModel [park=" + park + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", totalPage="
				+ totalPage + ", totalSize=" + totalSize + ", pageFrom=" + pageFrom + "]";
	}
	
}
