package com.huishu.ZSServer.common;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年11月22日
 * @Parem
 * @return 
 * 
 */
public class Data {
	private String name;
	private Long value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
