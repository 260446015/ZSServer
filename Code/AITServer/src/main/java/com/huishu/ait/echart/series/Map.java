package com.huishu.ait.echart.series;

import java.util.HashMap;

/**
 * 地图
 * @author yuwei
 * @param <T>
 */
public class Map<T> extends Serie<T> {
	
	private String mapType;
	
	private java.util.Map<String, Object> label;
	
	public Map() {
		super("map");
		this.label = new HashMap<>();
		java.util.Map<String, Object> show = new HashMap<>();
		show.put("show", true);
		label.put("normal", show);
		label.put("emphasis", show);
	}

	public String getMapType() {
		return mapType;
	}

	public void setMapType(String mapType) {
		this.mapType = mapType;
	}

	public java.util.Map<String, Object> getLabel() {
		return label;
	}

	public void setLabel(java.util.Map<String, Object> label) {
		this.label = label;
	}
	
}
