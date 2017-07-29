package com.huishu.ait.echart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuwei on 2016/12/26
 */
public abstract class XAxis {
	
	private String type;
	
	private List<String> data;
	
	private Map<String, Object> axisLabel = new HashMap<String, Object>() {
		private static final long serialVersionUID = -1673654673767724327L;
		{
			put("interval", 0);
			put("rotate", 30);
		}
	};

	public void setAxisLabel(Map<String, Object> axisLabel) {
		this.axisLabel = axisLabel;
	}

	protected XAxis(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public XAxis setData(List<String> data) {
		this.data = data;
		return this;
	}
	
	public XAxis addData(String obj) {
		if (data == null) {
			data = new ArrayList<>();
		}
		data.add(obj);
		return this;
	}

	public List<String> getData() {
		return data;
	}

	public Map<String, Object> getAxisLabel() {
		return axisLabel;
	}
}






















