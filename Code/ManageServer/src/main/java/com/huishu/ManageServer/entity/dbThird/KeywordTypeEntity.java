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
 * @date 2018年4月3日
 * @Parem
 * @return 
 * 词类型内容
 */
@Entity
@Table(name="t_word_type")
public class KeywordTypeEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "t_type_word")
	private String TypeWord;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeWord() {
		return TypeWord;
	}

	public void setTypeWord(String typeWord) {
		TypeWord = typeWord;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
