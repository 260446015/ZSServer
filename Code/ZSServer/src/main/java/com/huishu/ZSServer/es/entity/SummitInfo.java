package com.huishu.ZSServer.es.entity;

import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.INDEX2;
import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.TYPE2;

import org.springframework.data.elasticsearch.annotations.Document;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 * 产业峰会实体
 */
@Document(indexName = INDEX2, type = TYPE2)
public class SummitInfo {
	//涉及行业
	private String bus ;
	private String idustryThree;
	private String idustryTwice;
	private String publishDate;
	private Boolean hasWarn;
	private String source;
	private String title;
	private String content;
	private String articleLink;
    private Boolean istop;
    //展会logo
	private String logo;
	private String vector;
 	private String id;
	private String dimension;
	private String area;
	private String summary;
	private String publishTime;
	//会展地址
	private String address;
	
	//涉及公司
	private String business;
	private Long supportCount;
	private String author;
	//展会时间
	private String exhibitiontime;
	private String idustryZero;
	private Long hitCount;
	private Long replyCount;
	private String emotion;
	private String publishYear;
	public String getBus() {
		return bus;
	}
	public void setBus(String bus) {
		this.bus = bus;
	}
	public String getIdustryThree() {
		return idustryThree;
	}
	public void setIdustryThree(String idustryThree) {
		this.idustryThree = idustryThree;
	}
	public String getIdustryTwice() {
		return idustryTwice;
	}
	public void setIdustryTwice(String idustryTwice) {
		this.idustryTwice = idustryTwice;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public Boolean getHasWarn() {
		return hasWarn;
	}
	public void setHasWarn(Boolean hasWarn) {
		this.hasWarn = hasWarn;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
	public String getArticleLink() {
		return articleLink;
	}
	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}
	public Boolean getIstop() {
		return istop;
	}
	public void setIstop(Boolean istop) {
		this.istop = istop;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getVector() {
		return vector;
	}
	public void setVector(String vector) {
		this.vector = vector;
	}
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public Long getSupportCount() {
		return supportCount;
	}
	public void setSupportCount(Long supportCount) {
		this.supportCount = supportCount;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getExhibitiontime() {
		return exhibitiontime;
	}
	public void setExhibitiontime(String exhibitiontime) {
		this.exhibitiontime = exhibitiontime;
	}
	public String getIdustryZero() {
		return idustryZero;
	}
	public void setIdustryZero(String idustryZero) {
		this.idustryZero = idustryZero;
	}
	public Long getHitCount() {
		return hitCount;
	}
	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
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
	public String getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
