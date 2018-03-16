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
@Table(name = "t_key_word")
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
	//关联词
	@Column(name="word_relate")
	private	String wordRelate;
	
	//关联关系
	@Column(name="related")
	private String related;
	
	//关联id
	@Column(name="related_id")
	private Long relateId;

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

	public String getWordRelate() {
		return wordRelate;
	}

	public void setWordRelate(String wordRelate) {
		this.wordRelate = wordRelate;
	}

	public String getRelated() {
		return related;
	}

	public void setRelated(String related) {
		this.related = related;
	}

	public Long getRelateId() {
		return relateId;
	}

	public void setRelateId(Long relateId) {
		this.relateId = relateId;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
		
	}
