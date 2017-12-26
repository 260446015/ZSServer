package com.huishu.ZSServer.entity.dto;

import java.io.Serializable;

public class LabelDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4991701342748600852L;
	private String industry;
	private String area;
	private String[] registerTime;
	private String[] register;

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

	public String[] getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String[] registerTime) {
		this.registerTime = registerTime;
	}

	public String[] getRegister() {
		return register;
	}

	public void setRegister(String[] register) {
		this.register = register;
	}

}
