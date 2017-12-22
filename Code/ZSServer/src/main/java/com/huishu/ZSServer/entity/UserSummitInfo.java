package com.huishu.ZSServer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 * 关注产业峰会列表
 */
@Entity
@Table(name = "t_use_summit")
public class UserSummitInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
    @Column(name = "id", nullable = false)
	private Long id ;
	
	private Long uid;
	
	private String aid;
	
	private String logo;
	
	private String exhibitiontime;
	
	private String address;
	
	private String title;
	
	private String idustry;
	
	private String articleLink;
	
	public String getArticleLink() {
		return articleLink;
	}

	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}

	public String getIdustry() {
		return idustry;
	}

	public void setIdustry(String idustry) {
		this.idustry = idustry;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getExhibitiontime() {
		return exhibitiontime;
	}

	public void setExhibitiontime(String exhibitiontime) {
		this.exhibitiontime = exhibitiontime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public Long getId() {
		return id;
	}

	public Long getUid() {
		return uid;
	}

	public String getAid() {
		return aid;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
