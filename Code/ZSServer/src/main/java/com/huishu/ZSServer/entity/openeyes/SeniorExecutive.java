package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/***
 * 
 * @author yindawei
 * @date 2017年12月4日下午7:26:09
 * @description 查询高管信息（股票）
 * @version
 */
@Table(name = "t_seniorexecutive")
@Entity
public class SeniorExecutive implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6060294153170745355L;
	@Id
	private String id;
	/** 职位 */
	private String position;
	/** 性别 */
	private String sex;
	/** 对应公司id */
	private String graphId;
	/** 教育 */
	private String education;
	/** 类型 1公司 2 ⼈ */
	private Integer cType;
	/** 介绍 */
	private String resume;
	/** 所属组 1董事会、2监事会、3⾼管 */
	private String managerGroup;
	/** 本届任期 */
	private String term;
	/** ⾼管名 */
	private String name;
	/** 年龄 */
	private String age;
	/** 公告⽇期 */
	private String reportDate;
	/** 薪资 */
	private String salary;
	/** 持股数 */
	private String numberOfShares;
	/** 所属公司 */
	private String cname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getGraphId() {
		return graphId;
	}

	public void setGraphId(String graphId) {
		this.graphId = graphId;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Integer getcType() {
		return cType;
	}

	public void setcType(Integer cType) {
		this.cType = cType;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getManagerGroup() {
		return managerGroup;
	}

	public void setManagerGroup(String managerGroup) {
		this.managerGroup = managerGroup;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getNumberOfShares() {
		return numberOfShares;
	}

	public void setNumberOfShares(String numberOfShares) {
		this.numberOfShares = numberOfShares;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
