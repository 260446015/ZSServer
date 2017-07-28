package com.huishu.ait.entity.dto;

import java.util.Date;

/**
 * 产业政策的DTO类
 * @author jdz
 * @version 1.0
 * @createDate 2017-7-28
 */
public class IndustriaPolicyDTO {
    
    /**
     * id：产业政策文章id
     */
    private String id;
    
    /**
     * 产业类型：(互联网、生态科技、环保产业、物流产业...等)
     */
    private String industry;

    /**
     * 产业标签：(互联网-->不限、电子竞技、大数据、电子上午、网络视听...等)
     */
    private String industryLabel;
    
    /**
     * 地区：(不限、北京、上海、深圳...)
     */
    private String area;
    
    /**
     * 每页数据存储个数
     */
    private Integer pageSize;
    
   /**
    * 当前页码 
    */
    private Integer currentPage;
    
    /**
     * 时间段：(今日、昨天、近七天...等)
     * 用于确定查询的开始时间和结束时间
     */
    private String timeBucket;
    
    /**
     * 开始时间
     */
    private Date startDate;
    
    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * getter & setter
     * @return
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndustry() {
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getTimeBucket() {
        return timeBucket;
    }

    public void setTimeBucket(String timeBucket) {
        this.timeBucket = timeBucket;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
