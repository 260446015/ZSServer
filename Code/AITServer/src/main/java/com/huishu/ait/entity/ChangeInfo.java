package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年9月14日下午9:04:09
 * @description 天眼查信息变更实体类
 * @version
 */
@Entity
@Table(name = "t_changeinfo")
public class ChangeInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7026144386657590459L;
	@Id
	private String id;
	@Column(name = "change_time")
	private String changeTime;
	@Column(name = "content_after", length = 5000)
	private String contentAfter;
	@Column(name = "create_time")
	private String createTime;
	@Column(name = "content_before", length = 5000)
	private String contentBefore;
	/**
	 * 更新属性
	 */
	@Column(name = "change_item")
	private String changeItem;
	private String company;
	/**
	 * 是否查看
	 */
	private Integer dr;
	/**
	 * 所属园区
	 */
	private String park;
	/**
	 * 类型标签
	 */
	private String tag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public String getContentAfter() {
		return contentAfter;
	}

	public void setContentAfter(String contentAfter) {
		this.contentAfter = contentAfter;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getContentBefore() {
		return contentBefore;
	}

	public void setContentBefore(String contentBefore) {
		this.contentBefore = contentBefore;
	}

	public String getChangeItem() {
		return changeItem;
	}

	public void setChangeItem(String changeItem) {
		this.changeItem = changeItem;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);

	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
