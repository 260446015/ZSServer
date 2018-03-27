package com.huishu.ManageServer.entity.dto.dbThird;

import java.io.Serializable;

/**
 * @author hhy
 * @date 2018年3月21日
 * @Parem
 * @return 
 * 
 */
public class Serizal implements  Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long options;
	private String describe;
	
	public Long getOptions() {
		return options;
	}
	public void setOptions(Long options) {
		this.options = options;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	

}
