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
 * @date 2017年12月19日
 * @Parem
 * @return 
 * 关键词云实体
 */
@Entity
@Table(name="t_key_value")
public class KeyWordEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	
	private String time;
	
	@Column(name = "keyword" )
	private String key;
	
	@Column(name = "k_value" )
	private Double value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double double1) {
		this.value = double1;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
