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
	/** 公司名称 */
	private String business;
	/** 类型标签 */
	private String businessType;
	/** 所属单位*/
	private String danwei;
	/** 更新属性 */
	private String updateAttribute;
	/** 更新前 */
	private String beforeUpdate;
	/** 更新后 */
	private String afterUpdate;
	/** 更新时间 */
	private String dateTime;
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

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public String getUpdateAttribute() {
		return updateAttribute;
	}

	public void setUpdateAttribute(String updateAttribute) {
		this.updateAttribute = updateAttribute;
	}

	public String getBeforeUpdate() {
		return beforeUpdate;
	}

	public void setBeforeUpdate(String beforeUpdate) {
		this.beforeUpdate = beforeUpdate;
	}

	public String getAfterUpdate() {
		return afterUpdate;
	}

	public void setAfterUpdate(String afterUpdate) {
		this.afterUpdate = afterUpdate;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
