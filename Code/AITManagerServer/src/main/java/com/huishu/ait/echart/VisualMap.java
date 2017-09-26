package com.huishu.ait.echart;

import java.util.List;

public class VisualMap {

	private int min;
	private int max;
	private String left;
	private String top;
	private List<String> text;
	private boolean calculable;

	public VisualMap() {
		this.left = "left";
		this.top = "bottom";
		this.calculable = true;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public List<String> getText() {
		return text;
	}

	public void setText(List<String> text) {
		this.text = text;
	}

	public boolean isCalculable() {
		return calculable;
	}

	public void setCalculable(boolean calculable) {
		this.calculable = calculable;
	}

	public static VisualMap getVisualMap() {
		VisualMap visualMap = new VisualMap();
		visualMap.setCalculable(false);
		visualMap.setMax(50);
		visualMap.setMin(1);
		visualMap.setLeft("right");
		visualMap.setTop("bottom");
		return visualMap;
	}

}
