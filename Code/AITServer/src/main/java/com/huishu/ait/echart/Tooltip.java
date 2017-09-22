package com.huishu.ait.echart;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuwei on 2016/12/26
 */
public class Tooltip {

	private String trigger;

	private Map<String, String> axisPointer = new HashMap<>();

	public Tooltip() {
		this.trigger = "axis";
		String axisPointerType = EchartConfig.getInstance().getAxisPointerType();
		axisPointer.put("type", axisPointerType);
	}

	public String getTrigger() {
		return trigger;
	}

	public Tooltip setTrigger(String trigger) {
		this.trigger = trigger;
		return this;
	}

	public Map<String, String> getAxisPointer() {
		return axisPointer;
	}

	public Tooltip setAxisPointer(Map<String, String> axisPointer) {
		this.axisPointer = axisPointer;
		return this;
	}

}
