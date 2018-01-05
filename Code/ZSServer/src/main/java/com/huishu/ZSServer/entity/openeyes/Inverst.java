package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日下午3:07:33
 * @description 对外投资
 * @version
 */
@Table(name = "t_inverst")
@Entity
public class Inverst implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8401418007847165247L;

	@Id
	private Long id;
	/** 经营范围 */
	@Column(name = "business_scope")
	private String business_scope;
	/** 占股⽐例 */
	private String percent;
	/** 成⽴⽇期 */
	private Long estiblishTime;
	/** 经营状态 */
	private String regStatus;
	/** 1 公司 2 ⼈ */
	private Integer type;
	/** 法⼈ */
	private String legalPersonName;
	/** 评分 */
	private Integer pencertileScore;
	/** 法⼈id */
	private Long legalPersonId;
	/** 投资⾦额 */
	private Double amount;
	/** ⾏业 */
	private String category;
	/** 注册⾦额 */
	private String regCapital;
	/** 公司名 */
	private String name;
	/** 省份code */
	private String base;
	/** 1 ⼈ 2 公司 */
	private Integer personType;
	/** 所属公司 */
	private String cname;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusiness_scope() {
		return business_scope;
	}

	public void setBusiness_scope(String business_scope) {
		this.business_scope = business_scope;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public Integer getPencertileScore() {
		return pencertileScore;
	}

	public void setPencertileScore(Integer pencertileScore) {
		this.pencertileScore = pencertileScore;
	}

	public Long getLegalPersonId() {
		return legalPersonId;
	}

	public void setLegalPersonId(Long legalPersonId) {
		this.legalPersonId = legalPersonId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Integer getPersonType() {
		return personType;
	}

	public void setPersonType(Integer personType) {
		this.personType = personType;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
