package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 欠税公告
 * 
 * @author yindawei
 * @date 2017年11月8日下午5:46:36
 * @description
 * @version
 */
@Table(name = "t_owntax")
@Entity
public class OwnTax implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3735876163661816652L;
	@Id
	private String id;
	/** 证件号码 */
	private String personIdNumber;
	/** 法人或负责人名称 */
	private String legalpersonName;
	/** 经营地点 */
	private String location;
	/** 当前新发生欠税余额 */
	private String newOwnTaxBalance;
	/** 纳税人名称 */
	private String name;
	/** 欠税余额 */
	private String ownTaxBalance;
	/** 纳税人识别号 */
	private String taxIdNumber;
	/** 0国税 1地税 */
	private String type;
	/** 欠税税种 */
	private String taxCategory;
	/** 发布时间 */
	private String publishDate;
	/** 欠税数量 */
	private String ownTaxAmount;
	/** 部门 */
	private String department;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonIdNumber() {
		return personIdNumber;
	}

	public void setPersonIdNumber(String personIdNumber) {
		this.personIdNumber = personIdNumber;
	}

	public String getLegalpersonName() {
		return legalpersonName;
	}

	public void setLegalpersonName(String legalpersonName) {
		this.legalpersonName = legalpersonName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNewOwnTaxBalance() {
		return newOwnTaxBalance;
	}

	public void setNewOwnTaxBalance(String newOwnTaxBalance) {
		this.newOwnTaxBalance = newOwnTaxBalance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnTaxBalance() {
		return ownTaxBalance;
	}

	public void setOwnTaxBalance(String ownTaxBalance) {
		this.ownTaxBalance = ownTaxBalance;
	}

	public String getTaxIdNumber() {
		return taxIdNumber;
	}

	public void setTaxIdNumber(String taxIdNumber) {
		this.taxIdNumber = taxIdNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaxCategory() {
		return taxCategory;
	}

	public void setTaxCategory(String taxCategory) {
		this.taxCategory = taxCategory;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getOwnTaxAmount() {
		return ownTaxAmount;
	}

	public void setOwnTaxAmount(String ownTaxAmount) {
		this.ownTaxAmount = ownTaxAmount;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
