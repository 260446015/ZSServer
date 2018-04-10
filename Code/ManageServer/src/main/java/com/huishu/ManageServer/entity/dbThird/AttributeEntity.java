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
 * @date 2018年3月27日
 * @Parem
 * @return 
 * 词库平台属性值实体
 */
@Entity
@Table(name = "t_word_attribute")
public class AttributeEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	//关联词id
	@Column(name="t_word_id",insertable=true ,updatable=true)
	private Long wordId;
	
	@Column(name="t_attribute_name")
	private String attributeName;
	//string的字段内容
	@Column(name="t_attribute_first")
	private String attributeValue;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWordId() {
		return wordId;
	}

	public void setWordId(Long wordId) {
		this.wordId = wordId;
	}

	public String getAttributeName() {
		return attributeName;
	}


	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
