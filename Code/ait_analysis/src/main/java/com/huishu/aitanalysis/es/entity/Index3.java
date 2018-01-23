package com.huishu.aitanalysis.es.entity;

import static com.huishu.aitanalysis.common.DBConstant.EsConfig.INDEX2;
import static com.huishu.aitanalysis.common.DBConstant.EsConfig.TYPE1;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

import com.alibaba.fastjson.JSONObject;

@Document(indexName = INDEX2 , type = TYPE1)
public class Index3  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	/** 公司logo */
	private String logo;
	/** 融资轮次 */
	private String invest;
	/** 融资金额 */
	private String financingAmount;
	/** 融资时间 */
	private String financingDate;
	/** 投资方 */
	private String investor;
	/** 企业类型 */
	private String industry;
	//融资方
	private String financiers;
	//载体
	private String vector;
	//标题
	private String title;
	//内容
	private String content;
	//维度
	private String dimension;
	//原文网址
	private String articleLink;
	//详情
	private String 	articleInfo;
	//发布时间
	private String publishDate;
	//发布时间
	private String publishTime;
	//融资公司
	private String financingCompany;
	
	private String area;
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getArticleInfo() {
		return articleInfo;
	}
	public void setArticleInfo(String articleInfo) {
		this.articleInfo = articleInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getInvest() {
		return invest;
	}
	public void setInvest(String invest) {
		this.invest = invest;
	}
	public String getFinancingAmount() {
		return financingAmount;
	}
	public void setFinancingAmount(String financingAmount) {
		this.financingAmount = financingAmount;
	}
	public String getFinancingDate() {
		return financingDate;
	}
	public void setFinancingDate(String financingDate) {
		this.financingDate = financingDate;
	}
	public String getInvestor() {
		return investor;
	}
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getFinanciers() {
		return financiers;
	}
	public void setFinanciers(String financiers) {
		this.financiers = financiers;
	}
	public String getVector() {
		return vector;
	}
	public void setVector(String vector) {
		this.vector = vector;
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
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getArticleLink() {
		return articleLink;
	}
	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getFinancingCompany() {
		return financingCompany;
	}
	public void setFinancingCompany(String financingCompany) {
		this.financingCompany = financingCompany;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
}
