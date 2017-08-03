package com.huishu.ait.es.entity;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import org.springframework.data.elasticsearch.annotations.Document;

import com.alibaba.fastjson.JSONObject;

/**
 * 园区政策实体
 * @author yindq
 * @date
 */
@Document(indexName = INDEX, type = TYPE)
public class GardenPolicy {
	private String id;
	/** 标题 */
	private String title;
	/** 来源 */
	private String vector;
	/** 时间 */
	private String publishDateTime;
	/** 网站 */
	
	/** 网址 */
	private String articleLink;
	/** 情报采集 */
	private String source;
	/** 情报原址 */
	private String sourceLink;
	/** 内容 */
	private String content;
	/** 编辑 */
	private String author;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVector() {
		return vector;
	}
	public void setVector(String vector) {
		this.vector = vector;
	}
	public String getPublishDateTime() {
		return publishDateTime;
	}
	public void setPublishDateTime(String publishDateTime) {
		this.publishDateTime = publishDateTime;
	}
	public String getArticleLink() {
		return articleLink;
	}
	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
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
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
