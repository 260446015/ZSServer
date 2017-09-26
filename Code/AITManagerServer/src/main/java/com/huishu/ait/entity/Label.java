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
 * 需求池企业标签实体类
 * 
 * @author yindq
 * @create 2017年9月22日
 */
@Entity
@Table(name = "t_pool_label")
public class Label implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 主键id */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** 企业标签 */
	private String label;

	/** 用户id */
	@Column(name = "user_id")
	private Long userId;
	
	/** 用户园区 */
	private String park;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getPark() {
		return park;
	}


	public void setPark(String park) {
		this.park = park;
	}


	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
