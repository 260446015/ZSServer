package com.huishu.ait.echart;

import java.util.List;

public class Data<T> {

	private String name;

	List<T> value;

	public List<T> getValue() {
		return value;
	}

	public void setValue(List<T> value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
