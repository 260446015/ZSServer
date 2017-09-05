package com.huishu.ait.entity.common;

import java.io.Serializable;
/**
 * 计算比例传输对象
 * 
 * @author yindq
 * @create 2017年9月5日
 */
public class Ratio implements Serializable {
	/**
	 * 可序列化
	 */
	private static final long serialVersionUID = 1L;
	/** 字段名 */
	private String name;
	/** 数量 */
	private int num;
	/** 百分比*/
	private double percent;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
}
