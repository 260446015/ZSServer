package com.huishu.ait.es.entity.dto;

import java.util.Set;

import com.alibaba.fastjson.JSONObject;

/**
 * 文章列表
 * 
 * @author yindq
 * @create 2017年9月13日
 */
public class EssayListDTO {

	private String id;
	private String title;
	private String summary;
	private String content;
	private String source;
	private String publishTime;
	/** 公司名录集合 */
	private Set<String> bus;


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


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
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


	public String getPublishTime() {
		return publishTime;
	}


	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}


	public Set<String> getBus() {
		return bus;
	}


	public void setBus(Set<String> bus) {
		this.bus = bus;
	}


	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
