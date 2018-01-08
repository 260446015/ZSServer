package com.huishu.ManageServer.entity.dbSecond;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.config.TargetDataSource;

/**
 * @author hhy
 * @date 2018年1月5日
 * @Parem
 * @return 
 * 行业的关键词
 */
@Entity
@Table(name = "li_indus_word")
@TargetDataSource(name="second")
public class KeyInfoEntity implements Serializable {

	private static final long serialVersionUID = -6220647475255865478L;
	@Id  
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	//关键词
	@Column(name="keyword")
	private String keyword;
	//所占比重
	@Column(name="proportion")
	private Double proportion;
	//所属产业
	@Column(name="industry")
	private String industry;
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
	public Double getProportion() {
		return proportion;
	}
	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
