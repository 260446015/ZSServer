package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日下午5:44:13
 * @description 招聘信息实体
 * @version
 */
@Table(name = "t_employment")
@Entity
public class Employment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6177263488699200623L;

	@Id
	private Long id;
	/** 招聘职称,题⽬ */
	private String title;
	/** 所在城市 */
	private String city;
	/** 所在区 */
	private String district;
	/** 公司名字 */
	private String companyName;
	/** 薪资 */
	private String oriSalary;
	/** 外⽹链接 */
	private String urlPath;
	/** 招聘开始⽇期 */
	private Long startdate;
	/** 招聘截⽌⽇期 */
	private Long enddate;
	/** 来源 */
	private String source;
	/** 教育⽔平 */
	private String education;
	/** 招聘⼈数 */
	private String employerNumber;
	/** 经验 */
	private String experience;
	/** 职位描述 */
	private String description;
	/** 创建时间 */
	private Long createTime;
	/** 更新时间 */
	private Long updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOriSalary() {
		return oriSalary;
	}

	public void setOriSalary(String oriSalary) {
		this.oriSalary = oriSalary;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public Long getStartdate() {
		return startdate;
	}

	public void setStartdate(Long startdate) {
		this.startdate = startdate;
	}

	public Long getEnddate() {
		return enddate;
	}

	public void setEnddate(Long enddate) {
		this.enddate = enddate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEmployerNumber() {
		return employerNumber;
	}

	public void setEmployerNumber(String employerNumber) {
		this.employerNumber = employerNumber;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
