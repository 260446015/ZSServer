package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日下午5:01:55
 * @description 招投标的实体类
 * @version
 */
@Table(name="t_bids")
@Entity
public class Bids implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8614172659909678999L;

	@Id
	private String uuid;

	/** 简述 */
	private String intro;
	/** 标题 */
	private String title;
	/** 发布⽇期 */
	private Long publishTime;
	/** 采购⼈ */
	private String purchaser;
	/** 代理机构 */
	private String proxy;
	/** 来源链接 */
	private String link;
	/** 摘要信息 */
	private String abs;
	/** 正⽂信息 */
	private String content;
	/** 所属公司 */
	private String cname;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAbs() {
		return abs;
	}

	public void setAbs(String abs) {
		this.abs = abs;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
