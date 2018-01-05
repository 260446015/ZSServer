package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 新闻
 * 
 * @author yindawei
 * @date 2017年11月8日下午5:55:31
 * @description
 * @version
 */
@Table(name = "t_news")
@Entity
public class News implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5089548224180448866L;
	@Id
	private String id;
	/** 新闻标题 */
	private String title;
	/** 链接 */
	private String url;
	/** 来源 */
	private String website;
	/** 时间 */
	private String time;
	/** 所属公司 */
	private String companyName;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
