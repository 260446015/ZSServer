package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月3日下午6:25:22
 * @description 分支机构
 * @version
 */
@Table(name="t_branch")
@Entity
public class Branch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7310692610961993335L;
	@Id
	private Long id;
	/** 行业code */
	private String category;
	/** 注册资本 */
	private String regCapital;
	/** 机构名称 */
	private String name;
	/** 省份code */
	private String base;
	/** 成立时间 */
	private Long estiblishTime;
	/** 经营状态 */
	private String regStatus;
	/** 法人 */
	private String legalPersonName;
	/** 1 公司 2 人 */
	private Integer type;
	/** 评分 */
	private Integer pencertileScore;
	/** 所属公司 */
	private String cname;

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Long getEstiblishTime() {
		return estiblishTime;
	}

	public void setEstiblishTime(Long estiblishTime) {
		this.estiblishTime = estiblishTime;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPencertileScore() {
		return pencertileScore;
	}

	public void setPencertileScore(Integer pencertileScore) {
		this.pencertileScore = pencertileScore;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
