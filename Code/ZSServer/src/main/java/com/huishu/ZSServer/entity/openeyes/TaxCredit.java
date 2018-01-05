package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 税务评级
 * 
 * @author yindawei
 * @date 2017年11月10日上午10:47:36
 * @description
 * @version
 */
@Table(name = "t_taxcredit")
@Entity
public class TaxCredit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8269120299408848643L;
	@Id
	private String id;
	/** 纳税等级 */
	private String grade;
	/** 年份 */
	private String year;
	/** 评价单位 */
	private String evalDepartment;
	/** 0国税 1地税 */
	private String type;
	/** 纳税人识别号 */
	private String idNumber;
	/** 纳税人名称 */
	private String name;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getEvalDepartment() {
		return evalDepartment;
	}

	public void setEvalDepartment(String evalDepartment) {
		this.evalDepartment = evalDepartment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
