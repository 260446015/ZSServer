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
 * @date 2018年4月8日
 * @Parem
 * @return 
 * 编号以及词性关系属性图
 */
@Entity
@Table(name = "t_word_info")
public class KeywordInfoEntity implements Serializable{

	private static final long serialVersionUID = 1556580992838066738L;
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	//关键词编号
	@Column(name = "t_word_number")
	private String wordNumber;
	//词性id
	@Column(name = "t_type_id")
	private Long typeId;
	
	//关键词id
	@Column(name = "t_word_id")
	private Long wordId;
	
	//添加时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="t_insert_time")
	private Date insertTime;
	
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWordNumber() {
		return wordNumber;
	}
	public void setWordNumber(String wordNumber) {
		this.wordNumber = wordNumber;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public Long getWordId() {
		return wordId;
	}
	public void setWordId(Long wordId) {
		this.wordId = wordId;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
