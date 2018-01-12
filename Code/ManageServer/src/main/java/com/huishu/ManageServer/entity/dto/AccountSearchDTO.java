package com.huishu.ManageServer.entity.dto;

public class AccountSearchDTO extends AbstractDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6974666918925088524L;
	/** 会员类型 */
	private String type;
	/** 日期 */
	private String time;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
