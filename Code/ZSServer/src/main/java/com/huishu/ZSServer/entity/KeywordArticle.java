package com.huishu.ZSServer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2018年1月4日
 * @Parem
 * @return 
 * 获取关键词的文章信息
 */
@Entity
@Table(name = "t_key_article")
public class KeywordArticle implements Serializable{

	private static final long serialVersionUID = 4515312728644908827L;
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	private Long kid;
	private String aid;
	private String industryLabel;
	private String title;
	private String articleLink;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getKid() {
		return kid;
	}
	public void setKid(Long kid) {
		this.kid = kid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getIndustryLabel() {
		return industryLabel;
	}
	public void setIndustryLabel(String industryLabel) {
		this.industryLabel = industryLabel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArticleLink() {
		return articleLink;
	}
	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
