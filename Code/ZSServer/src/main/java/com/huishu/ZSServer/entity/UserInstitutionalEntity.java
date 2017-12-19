package com.huishu.ZSServer.entity;

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
 * @date 2017年11月22日
 * @Parem
 * @return 
 * 
 */
@Entity
@Table(name = "t_user_institutional")
public class UserInstitutionalEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 用户id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	@Column(name="t_user_id",nullable = true)
	private Long userId;
	@Column(name="t_ist_id",nullable = true)
	private Long insId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getInsId() {
		return insId;
	}
	public void setInsId(Long insId) {
		this.insId = insId;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
}
