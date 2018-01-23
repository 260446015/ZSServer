package com.huishu.aitanalysis.es.entity;

import static com.huishu.aitanalysis.common.DBConstant.EsConfig.INDEX1;
import static com.huishu.aitanalysis.common.DBConstant.EsConfig.TYPE1;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年8月30日
 * @Parem
 * @return
 * 
 */
@Document(indexName = INDEX1, type = TYPE1)
public class Index2 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	/** 总结 */
	private String summary;
	
	/** 详细地址 */
	private String address;

	/** 发布时间 yyyy-MM-dd HH:mm:ss */
	private String publishDate;
	
	/** 发布时间  yyyy-MM-dd */
	private String publishTime;
	
	/** 发布时间 年份 */
	private String publishYear;

	/** 文章地址 */
	private String articleLink;

	/** 文章标题 */
	private String title;
	/** 文章内容 */
	private String content;
	/** 作者 */
	private String author;
	/** 来源 */
	private String source;
	/** 地域 */
	private String area;
	
	/** 载体 */
	private String vector;
	
	
	/** 展会时间 */
	private String exhibitiontime;

	/** 涉及公司  */
	private String business;
	
	/** 涉及行业  */
	private String bus;

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

	/** 企业logo */
	private String logo;
	
	/**一级产业 */
	private String idustryZero;
	
	/**二级产业 */
	private String idustryTwice;
	
	/**三级产业 */
	private String idustryThree;
	
	 /**是否置顶 */
	private boolean istop;
	
	/** 维度 */
	private String dimension;

	
	
	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getBus() {
		return bus;
	}

	public void setExhibitiontime(String exhibitiontime) {
		this.exhibitiontime = exhibitiontime;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	
	public void setIdustryZero(String idustryZero) {
		this.idustryZero = idustryZero;
	}

	public void setIdustryTwice(String idustryTwice) {
		this.idustryTwice = idustryTwice;
	}

	public void setIdustryThree(String idustryThree) {
		this.idustryThree = idustryThree;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExhibitiontime() {
		return exhibitiontime;
	}

	public String getBusiness() {
		return business;
	}

	
	public String getIdustryZero() {
		return idustryZero;
	}

	public String getIdustryTwice() {
		return idustryTwice;
	}

	public String getIdustryThree() {
		return idustryThree;
	}

	public boolean isIstop() {
		return istop;
	}

	public void setIstop(boolean istop) {
		this.istop = istop;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVector() {
		return vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}

	public String getArticleLink() {
		return articleLink;
	}

	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	
	

	public Boolean getHasWarn() {
		return hasWarn;
	}

	public void setHasWarn(Boolean hasWarn) {
		this.hasWarn = hasWarn;
	}

	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	

	

	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
