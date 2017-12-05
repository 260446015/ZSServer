package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日下午6:13:12
 * @description 法律诉讼
 * @version
 */
@Table(name = "t_lawsuit")
@Entity
public class LawSuit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7771540835016951909L;

	@Id
	private String uuid;
	/** 标题 */
	private String title;
	/** 提交时间 */
	private Long submittime;
	/** 法院 */
	private String court;
	/** ⽂书类型 */
	private String doctype;
	/** 原⽂链接地址 */
	private String url;
	/** 案件号 */
	private String caseno;
	/** 案件类型 */
	private String casetype;
	/** 所属公司 */
	private String cname;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSubmittime() {
		return submittime;
	}

	public void setSubmittime(Long submittime) {
		this.submittime = submittime;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCaseno() {
		return caseno;
	}

	public void setCaseno(String caseno) {
		this.caseno = caseno;
	}

	public String getCasetype() {
		return casetype;
	}

	public void setCasetype(String casetype) {
		this.casetype = casetype;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
