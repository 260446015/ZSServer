package com.huishu.ManageServer.entity.dbThird;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 * 词库管理平台实体
 */
@Entity
@Table(name = "t_word")
public class ThesaurusEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	// 关键词
	@Column(name="t_key_word")
	private String keyword;
	// 关键词类型
	@Column(name="t_word_type")
	private String type;
	//词汇描述
	@Column(name="t_key_describe")
	private String describe;
	//产业解释性关键词
	@Column(name="t_key_explanatory")
	private String keyExplanatory;
	//产业业务性关键词
	@Column(name="t_key_business")
	private String keyBusiness;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getKeyExplanatory() {
		return keyExplanatory;
	}

	public void setKeyExplanatory(String keyExplanatory) {
		this.keyExplanatory = keyExplanatory;
	}
	
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getKeyBusiness() {
		return keyBusiness;
	}

	public void setKeyBusiness(String keyBusiness) {
		this.keyBusiness = keyBusiness;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
		
	}
