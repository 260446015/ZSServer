package com.huishu.ait.entity.dto;

import java.io.Serializable;

/**
 * 
 * @author yindawei
 * @creationTime 2017-07-09
 * @description 企业排行榜查询DTO
 *
 */
public class CompanyDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产业
	 */
	private String industry;
	/**
	 * 产业标签
	 */
	private String industryLabel;
	/**
	 * 发布时间
	 */
	private String publishTime;
	/**
	 * 维度
	 */
	private String dimension;
	/**
	 * 关键字
	 */
	private String keyWord;
	/**
	 * 注册资金
	 */
	private String regCapital;
	/**
     * 每页数据存储个数
     */
    private Integer pageSize;
    /**
     * 当前页码数
     */
    private Integer pageNumber;
    /**
     * 所属园区
     */
    private String park;
    /**
     * 
     * 传递数组
     */
    private String[] msg;
    
    /**
     * 传递企业分组的id
     */
    private Long groupId;
    /**
     * 查询注册资本起始资金
     */
    private double start;
    /**
     * 查询注册资本结束资金
     */
    private double end;
    private String groupname;
    private Long userId;

	public String getIndustry() {
		if("全部".equals("industry")){
			industry = "%%";
		}
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIndustryLabel() {
		return industryLabel;
	}

	public void setIndustryLabel(String industryLabel) {
		this.industryLabel = industryLabel;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

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

	public String[] getMsg() {
		return msg;
	}

	public void setMsg(String[] msg) {
		this.msg = msg;
	}

	

	

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getRegCapital() {
		if(regCapital == null){
			return regCapital;
		}
		switch (regCapital) {
		case "全部":
			regCapital = "0,99999";
			break;
		case "0-100万":
			regCapital = "0,100";
			break;
		case "100-200万":
			regCapital = "100.001,200";
			break;
		case "200-500万":
			regCapital = "200.001,500";
			break;
		case "500-1000万":
			regCapital = "500.001,1000";
			break;
		case "1000万以上":
			regCapital = "1000.001,99999";
			break;
		default:
			break;
		}
		return regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public double getStart() {
		String[] split = getRegCapital().split(",");
		return Double.parseDouble(split[0]);
	}

	public void setStart(double start) {
		this.start = start;
	}

	public double getEnd() {
		String[] split = getRegCapital().split(",");
		return Double.parseDouble(split[1]);
	}

	public void setEnd(double end) {
		this.end = end;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
