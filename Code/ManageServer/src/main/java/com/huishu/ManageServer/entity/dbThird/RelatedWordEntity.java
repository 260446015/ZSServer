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
 * @date 2018年4月10日
 * @Parem
 * @return 
 * 关系词实体
 */
@Entity
@Table(name="t_word_relatetion")
public class RelatedWordEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "word_relatetion")
	private String relatetion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRelatetion() {
		return relatetion;
	}

	public void setRelatetion(String relatetion) {
		this.relatetion = relatetion;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
