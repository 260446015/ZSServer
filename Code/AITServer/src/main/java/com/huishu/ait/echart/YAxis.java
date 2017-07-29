package com.huishu.ait.echart;

import java.util.List;

/**
 * Created by yuwei on 2016/12/26
 */
public abstract class YAxis {
	
	private String type;
	
	private List<String> data;
	
	protected YAxis(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public List<String> getData() {
		return data;
	}

	public YAxis setData(List<String> data) {
		this.data = data;
		return this;
	}

}
