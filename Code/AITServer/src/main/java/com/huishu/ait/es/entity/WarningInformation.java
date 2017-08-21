package com.huishu.ait.es.entity;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import org.springframework.data.elasticsearch.annotations.Document;

import com.alibaba.fastjson.JSONObject;

/**
 * 信息变更预警详情
 * @author yindq
 * @date 2017年8月3日
 */
@Document(indexName = INDEX, type = TYPE)
public class WarningInformation {
	private String id;
	/** 标题 */
	private String title;
	/** 类型标签 */
	private String businessType;
	/** 公司名称*/
	private String business;
	/** 更新属性 */
	private String updateAttribute;
	/** 更新前 */
	private String contentBefore;
	/** 更新后 */
	private String contentAfter;
	/** 更新时间 */
	private String publishTime;
	/** 更新来源 */
	private String source;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getUpdateAttribute() {
		return updateAttribute;
	}

	public String getContentBefore() {
		return contentBefore;
	}

	public void setContentBefore(String contentBefore) {
		this.contentBefore = contentBefore;
	}

	public String getContentAfter() {
		return contentAfter;
	}

	public void setContentAfter(String contentAfter) {
		this.contentAfter = contentAfter;
	}

	public void setUpdateAttribute(String updateAttribute) {
		this.updateAttribute = updateAttribute;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
