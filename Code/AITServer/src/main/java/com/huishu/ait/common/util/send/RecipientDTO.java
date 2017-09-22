package com.huishu.ait.common.util.send;

/**
 * 收件人
 * 
 * @author yuwei
 */
public class RecipientDTO {

	/** 地址（手机号/邮箱） */
	private String address;

	/** 姓名 */
	private String name;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
