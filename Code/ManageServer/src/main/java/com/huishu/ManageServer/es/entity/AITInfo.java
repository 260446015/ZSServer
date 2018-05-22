package com.huishu.ManageServer.es.entity;

import static com.huishu.ManageServer.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ManageServer.common.conf.DBConstant.EsConfig.TYPE;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.common.util.StringUtil;

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
	/** 存续（在营、开业、在册） */
	private String engageState;
	/** 总结 */
	private String summary;
	/** 身份 */
	private String identity;
	/** 注册资金 */
	private String registerCapital;
	/** 注册时间 */
	private String registerData;
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
	/** 原文链接 */
	private String sourceLink;
	/** 来源 */
	private String source;
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
	/** 所属园区 */
	private String park;

	/** 成立时间 */
	private String establishTime;

	/** 园区面积 */
	private String acreage;

	/** 企业名称 */
	private String business;

	/** 企业变更的属性 */
	private String updateAttribute;

	/** 企业法人 */
	private String boss;

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

	/** 企业logo */
	private String logo;
	
	private boolean istop;
	/** 维度 */
	private String dimension;

	
	/** 公司名录集合 */
	private List<String> bus;
	


	public boolean isIstop() {
		return istop;
	}

	public void setIstop(boolean istop) {
		this.istop = istop;
	}

	public List<String> getBus() {
		return bus;
	}

	public void setBus(List<String> bus) {
		this.bus = bus;
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

	public String getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	

	public String getArticleLink() {
		return articleLink;
	}

	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}


	public String getSummary() {
		if(!StringUtil.isEmpty(this.summary))
			return StringUtil.replaceHtml(summary);
		else
			return StringUtil.replaceHtml(content).substring(0, 15);
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getEngageState() {
		return engageState;
	}

	public void setEngageState(String engageState) {
		this.engageState = engageState;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}

	public String getRegisterData() {
		return registerData;
	}

	public void setRegisterData(String registerData) {
		this.registerData = registerData;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEstablishTime() {
		return establishTime;
	}

	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}

	public String getAcreage() {
		return acreage;
	}

	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}

	public String getUpdateAttribute() {
		return updateAttribute;
	}

	public void setUpdateAttribute(String updateAttribute) {
		this.updateAttribute = updateAttribute;
	}

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
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
