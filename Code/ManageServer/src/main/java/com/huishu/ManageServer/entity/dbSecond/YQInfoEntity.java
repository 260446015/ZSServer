package com.huishu.ManageServer.entity.dbSecond;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年12月26日
 * @Parem
 * @return 
 * 舆情信息的实体
 */
@Entity
@Table(name = "li_keywords")
public class YQInfoEntity implements Serializable{

	private static final long serialVersionUID = -5140230870865901869L;
	//舆情主键
	@Id
	@GeneratedValue
	@Column(name = "yid", nullable = false)
	private Long id;
	//关键字id
	@Column(name = "k_id", nullable = false)
	private Long kid;
	//文章id
	@Column(name = "id", nullable = false)
	private String aid;
	//发布时间
	private Timestamp publishTime;
	//原文地址
	private String url;
	//消极
	private boolean isnegative;
	//中性
	private boolean isneutral;
	//积极
	private boolean ispositive;
	//原文标题
	private String  title;
	//将文章标题中出现的特殊的字符替换掉,并存储
	private String  cleanTitle;
	//内容
	private String content;
	//摘要
	private String summary;
	//关键词
	private String keywords;
	//实体-地域
	@Column(name = "entity_area")
	private String area;
	//实体-人名
	@Column(name = "entity_name")
	private String name;
	//实体-公司
	@Column(name = "entity_organization")
	private String organization;
	//入库时间
	private Timestamp time;
	//备注
	private String remark;
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
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isIsnegative() {
		return isnegative;
	}
	public void setIsnegative(boolean isnegative) {
		this.isnegative = isnegative;
	}
	public boolean isIsneutral() {
		return isneutral;
	}
	public void setIsneutral(boolean isneutral) {
		this.isneutral = isneutral;
	}
	public boolean isIspositive() {
		return ispositive;
	}
	public void setIspositive(boolean ispositive) {
		this.ispositive = ispositive;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCleanTitle() {
		return cleanTitle;
	}
	public void setCleanTitle(String cleanTitle) {
		this.cleanTitle = cleanTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
