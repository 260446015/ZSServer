package com.huishu.ManageServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 添加h5数据
 *
 * @author yindq
 * @date 2018/2/7
 */
public class HtmlAddDTO implements Serializable {
	private String name;
	private String time;
	private JSONObject[] arr;
	private JSONObject[] arr2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public JSONObject[] getArr() {
		return arr;
	}

	public void setArr(JSONObject[] arr) {
		this.arr = arr;
	}

	public JSONObject[] getArr2() {
		return arr2;
	}

	public void setArr2(JSONObject[] arr2) {
		this.arr2 = arr2;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
