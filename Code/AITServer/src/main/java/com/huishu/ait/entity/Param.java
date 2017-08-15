package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年8月3日
 * @Parem
 * @return 
 * 
 */
@Entity
@Table(name="t_user_industry")
public class Param implements Serializable {

	private static final long serialVersionUID = 1L;
	/**主键id*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**产业信息*/
	@Column(name="t_industry_info",nullable=true)
	private String industryInfo;
	
	/**产业标签*/
	@Column(name="t_industry_lagel")
	private String industryLagel;
	
	/**用户id*/
	@Column(name="uid")
	private Long Uid;
	private String msg;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getIndustryInfo() {
		return industryInfo;
	}
	public void setIndustryInfo(String industryInfo) {
		this.industryInfo = industryInfo;
	}
	public String getIndustryLagel() {
		return industryLagel;
	}
	public void setIndustryLagel(String industryLagel) {
		this.industryLagel = industryLagel;
	}
	public Long getUid() {
		return Uid;
	}
	public void setUid(Long uid) {
		Uid = uid;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
}
