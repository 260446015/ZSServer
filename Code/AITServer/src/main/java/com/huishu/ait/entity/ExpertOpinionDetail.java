package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yxq
 * @date 2017年7月31日
 * @功能描述：专家观点详情类
 */
@Entity
@Table(name = "t_expert_opinion_detail")
public class ExpertOpinionDetail implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "article_id")
	private String articleId;
	private String author;
	private String title;
	@Column(name = "publish_time")
	private String publishTime;
	private String content;
	private String source;//文章来源
	@Column(name = "source_link")
	private String sourceLink;//文章来源网址
	private String industry;//所属产业
	private String lanmu;//所属栏目
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
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
	public String getSourceLink() {
		return sourceLink;
	}
	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getLanmu() {
		return lanmu;
	}
	public void setLanmu(String lanmu) {
		this.lanmu = lanmu;
	}
}