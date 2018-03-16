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
	@Column(name="key_word")
	private String keyword;
	// 关键词类型
	@Column(name="word_type")
	private String type;
	

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

	

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
		
	}
