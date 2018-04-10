package com.huishu.ManageServer.entity.dbThird;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 * 词库管理平台实体
 */
@Entity
@Table(name ="t_word")
public class ThesaurusEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	// 关键词
	@Column(name="t_key_word")
	private String keyword;
	
	//词汇描述
	@Column(name="t_key_describe")
	private String describe;
	
	//添加时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="t_insert_time")
	private Date insertTime;
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

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}


	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
		
	}
