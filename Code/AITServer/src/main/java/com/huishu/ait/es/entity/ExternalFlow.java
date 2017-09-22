package com.huishu.ait.es.entity;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 
 * @author yindawei
 * @date 2017年9月12日下午8:49:46
 * @description
 * @version
 */
@Document(indexName = INDEX, type = TYPE)
public class ExternalFlow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5800569296253579318L;
	// "publishDate","business", "title", "content","warnTime","park"
	private String id;
	private String publishDate;
	private String business;
	private String title;
	private String content;
	private String warnTime;
	private String park;
	private String hasWarn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
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

	public String getWarnTime() {
		return warnTime;
	}

	public void setWarnTime(String warnTime) {
		this.warnTime = warnTime;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getHasWarn() {
		return hasWarn;
	}

	public void setHasWarn(String hasWarn) {
		this.hasWarn = hasWarn;
	}

}
