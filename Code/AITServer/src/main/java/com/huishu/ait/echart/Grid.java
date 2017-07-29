package com.huishu.ait.echart;

/**
 * Created by yuwei on 2016/12/26
 */
public class Grid {
	
	private Object left;
	
	private Object right;
	
	public Grid() {
		EchartConfig conf = EchartConfig.getInstance();
		this.left = conf.getLeft();
		this.right = conf.getRight();
	}

	public Object getLeft() {
		return left;
	}

	public void setLeft(Object left) {
		this.left = left;
	}

	public Object getRight() {
		return right;
	}

	public void setRight(Object right) {
		this.right = right;
	}

}
