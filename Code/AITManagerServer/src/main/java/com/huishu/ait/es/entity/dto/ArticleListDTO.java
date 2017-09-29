package com.huishu.ait.es.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年9月27日
 * @Parem
 * @return 
 * 
 */
public class ArticleListDTO {
	
	
	private String id;
	private String title;
	private String content;
	private String source;
	private String vector;
	private String publishTime;
	
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
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
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
