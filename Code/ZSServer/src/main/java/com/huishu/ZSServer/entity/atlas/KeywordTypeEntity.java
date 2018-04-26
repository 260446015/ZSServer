package com.huishu.ZSServer.entity.atlas;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.*;
import java.io.Serializable;

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
	private String typeWord;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeWord() {
		return typeWord;
	}

	public void setTypeWord(String typeWord) {
		typeWord = typeWord;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
