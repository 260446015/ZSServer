package com.huishu.ait.entity.dto;

import java.io.Serializable;
import java.util.List;

import com.huishu.ait.entity.PoolCompany;

public class PoolCompanyDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 企业名  */
	private String name;
	/** 子公司集合  */
	private List<PoolCompany> children;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PoolCompany> getChildren() {
		return children;
	}
	public void setChildren(List<PoolCompany> children) {
		this.children = children;
	}
}
