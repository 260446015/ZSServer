package com.huishu.ait.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.es.entity.dto.AbstractDTO;

/**
 * 产业政策的DTO类
 * @author jdz
 * @version 1.0
 * @createDate 2017-7-28
 */
public class IndustrialPolicyDTO extends AbstractDTO {
    
    /** 产业类型：(互联网、生态科技、环保产业、物流产业...等) */
    private String industry;

    /** 产业标签：(互联网-->不限、电子竞技、大数据、电子上午、网络视听...等) */
    private String industryLabel;
    
    /** 地区：(不限、北京、上海、深圳...)*/
    private String area;
    
    /** 每页数据存储个数 */
    private Integer pageSize;
    
   /** 当前页码  */
    private Integer pageNumber;
    
    /** 开始时间 */
    private String startDate;
    
    /** 结束时间 */
    private String endDate;
    
    /** 时间段 */
    private String periodDate;
    
    /** 文章类型  */
    private String articleType;
    
    private String dimension;
    
    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    private String[] msg;
    
    public String[] getMsg() {
        return msg;
    }

    public void setMsg(String[] msg) {
        this.msg = msg;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
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

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
