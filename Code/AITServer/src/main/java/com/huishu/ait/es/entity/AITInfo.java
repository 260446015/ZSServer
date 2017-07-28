package com.huishu.ait.es.entity;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author hhy
 * @date 2017年7月25日
 * @Parem
 * @return
 * 
 */
@Document(indexName = INDEX, type = TYPE)
public class AITInfo {
    
    private String id;
	/** 发布时间 yyyy-MM-dd HH:mm:ss */
	private String publishDateTime;

	/** 发布时间 10 */
	private String publishTime;

	/** 发布时间 yyyy-MM-dd */
	private String publishDate;

	private String ossLink;

	/** 文章地址 */
	private String articleLink;

	/** 文章标题 */
	private String title;

	

	/** 文章内容 */
	private String content;

	/** 作者 */
	private String author;

	/** 原文链接 */
	private String sourceLink;

	/** 来源 */
	private String source;

	/** 文章类型 */
	private String articleType;

	/** 地域 */
	private String area;

	/** 产业 */
	private String industry;

	/** 产业标签 网络游戏 */
	private String industryLabel;

	/** 载体 */
	private String vector;

	/** 产业类型 */
	private String industryType;

	/** 所属园区 中关村软件园 */

	private String park;

	/** 企业名称 */
	private String business;
	/**企业法人*/
	private String businessLegal;
	/** 企业类型 */
	private String businessType;

	/** 情感 */
	private String emotion;

	/** 点击量 */
	private Long hitCount;

	/** 支持数 */
	private Long supportCount;

	/** 回复量 */
	private Long replyCount;

	/** 是否已经预警 */
	private Boolean hasWarn;

	/** 维度 */
	private String dimension;
	

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSourceLink() {
		return sourceLink;
	}

	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
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

	public String getVector() {
		return vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public Long getHitCount() {
		return hitCount;
	}

	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
	}

	public Long getSupportCount() {
		return supportCount;
	}

	public void setSupportCount(Long supportCount) {
		this.supportCount = supportCount;
	}

	public Long getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Long replyCount) {
		this.replyCount = replyCount;
	}

	public Boolean getHasWarn() {
		return hasWarn;
	}

	public void setHasWarn(Boolean hasWarn) {
		this.hasWarn = hasWarn;
	}
	public String getPublishDateTime() {
		return publishDateTime;
	}

	public void setPublishDateTime(String publishDateTime) {
		this.publishDateTime = publishDateTime;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getOssLink() {
		return ossLink;
	}

	public void setOssLink(String ossLink) {
		this.ossLink = ossLink;
	}

	public String getArticleLink() {
		return articleLink;
	}

	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}

	public String getBusinessLegal() {
		return businessLegal;
	}

	public void setBusinessLegal(String businessLegal) {
		this.businessLegal = businessLegal;
	}

	@Override
	public String toString() {
		return "AITInfo [publishDateTime=" + publishDateTime + ", publishTime=" + publishTime + ", publishDate="
				+ publishDate + ", ossLink=" + ossLink + ", articleLink=" + articleLink + ", title=" + title
				+ ", content=" + content + ", author=" + author + ", sourceLink=" + sourceLink + ", source=" + source
				+ ", articleType=" + articleType + ", area=" + area + ", industry=" + industry + ", industryLabel="
				+ industryLabel + ", vector=" + vector + ", industryType=" + industryType + ", park=" + park
				+ ", business=" + business + ", businessLegal=" + businessLegal + ", businessType=" + businessType
				+ ", emotion=" + emotion + ", hitCount=" + hitCount + ", supportCount=" + supportCount + ", replyCount="
				+ replyCount + ", hasWarn=" + hasWarn + ", dimension=" + dimension + "]";
	}

	
	
}

