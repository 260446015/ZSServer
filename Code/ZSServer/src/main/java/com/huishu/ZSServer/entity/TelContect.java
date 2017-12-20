package com.huishu.ZSServer.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.alibaba.fastjson.JSONObject;

/**
 * 想要联系人的实体类
 * 
 * @author yindawei
 * @date 2017年12月19日下午12:00:29
 * @description
 * @version
 */
@Table(name = "t_telcontect")
@Entity
public class TelContect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1085230843586508315L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** 想要联系的人物姓名 */
	private String name;
	/** 人物所属公司 */
	private String cname;
	/** 什么时候想要联系的 */
	private Long date;
	/** 是否加急联系 */
	private String urgency;
	/** 所属那个用户 */
	private Long userId;
	/** 机构ID */
	private Long organizationId;
	/** 类型 */
	private String type;
	
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String isUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrgency() {
		return urgency;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
