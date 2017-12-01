package com.huishu.ZSServer.entity.vo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class CompanyVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String estiblishTime;
	private String regCapital;
    private Integer companyType;
    private String name;
    private Long id;
    private Integer type;
    private String legalPersonName;
    private String base;
	public String getEstiblishTime() {
		return estiblishTime;
	}
	public void setEstiblishTime(String estiblishTime) {
		this.estiblishTime = estiblishTime;
	}
	public String getRegCapital() {
		return regCapital;
	}
	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}
	public Integer getCompanyType() {
		return companyType;
	}
	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
    
}
