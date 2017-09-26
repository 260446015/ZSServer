package com.huishu.ait.echart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuwei on 2016/12/26
 */
public class Legend {

	private List<String> data;

	public Legend setData(List<String> data) {
		this.data = data;
		return this;
	}

	public List<String> getData() {
		return data;
	}

	public Legend addData(String data) {
		if (this.data == null) {
			this.data = new ArrayList<>();
		}
		this.data.add(data);
		return this;
	}

}
