package com.huishu.ait.echart.series;

/**
 * 词云
 * 
 * @author yuwei
 * @param <T>
 */
public class WordCloud<T> extends Serie<T> {

	public WordCloud() {
		super("wordCloud");
	}

	private String shape;

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

}
