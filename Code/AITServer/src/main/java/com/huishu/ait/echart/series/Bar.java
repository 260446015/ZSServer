package com.huishu.ait.echart.series;

/**
 * Created by yuwei on 2016/12/26
 */
public class Bar<T> extends Serie<T> {

	public Bar() {
		super("bar");
	}

	private String stack;

	public String getStack() {
		return stack;
	}

	public Bar<T> setStack(String stack) {
		this.stack = stack;
		return this;
	}

}
