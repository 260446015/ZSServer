package com.huishu.ZSServer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年11月15日
 * @Parem
 * @return 
 * 
 */
@Entity
@Table(name = "t_institutional_repository")
public class Institutional implements Serializable {

	private static final long serialVersionUID = -1464325025293349591L;
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id ;
	//国家重点实验室名称
	@Column(name = "t_laboratory_name")
	private String LaboratoryName;
	//机构类别
	@Column(name = "t_institutional_category")
	private String InstitutionalCategory;
	//所属产业
	@Column(name = "t_industry")
	private String industry;
	//地区
	private String area;
	//依托单位
	@Column(name = "t_supporting_unit")
	private String supportUnit;
	//学科带头人
	@Column(name = "t_academic_leader")
	private String academicLeader;
	//网址
	private String url;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLaboratoryName() {
		return LaboratoryName;
	}

	public void setLaboratoryName(String laboratoryName) {
		LaboratoryName = laboratoryName;
	}

	public String getInstitutionalCategory() {
		return InstitutionalCategory;
	}

	public void setInstitutionalCategory(String institutionalCategory) {
		InstitutionalCategory = institutionalCategory;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSupportUnit() {
		return supportUnit;
	}

	public void setSupportUnit(String supportUnit) {
		this.supportUnit = supportUnit;
	}

	public String getAcademicLeader() {
		return academicLeader;
	}

	public void setAcademicLeader(String academicLeader) {
		this.academicLeader = academicLeader;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
}
