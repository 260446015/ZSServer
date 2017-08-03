package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：企业信息的实体类
 */
@Entity
@Table(name = "t_company")
public class Company implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4365522363158952716L;
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "company_id")
	private String companyId;
	@Column(name = "company_name")
	private String companyName;
	//建议采用多对一
	@ManyToOne(cascade=CascadeType.REFRESH,optional=false)
	@JoinColumn(name = "group_id",nullable=false)
	private CompanyGroup companyGroup;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public CompanyGroup getCompanyGroup() {
		return companyGroup;
	}
	public void setCompanyGroup(CompanyGroup companyGroup) {
		this.companyGroup = companyGroup;
	}
}
