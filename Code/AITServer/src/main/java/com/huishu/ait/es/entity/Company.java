package com.huishu.ait.es.entity;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import org.springframework.data.elasticsearch.annotations.Document;

import com.alibaba.fastjson.JSONObject;
/**
 * 
 * @author yindawei
 * 企业排行榜对应的es实体类
 *
 */

@Document(indexName = INDEX, type = TYPE)
public class Company {
	
	private String id;
	/** 标题 */
	private String title;
	/** 来源 */
	private String vector;
	/** 时间 */
	private String publishDateTime;
	/** 内容 */
	private String content;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	

}
