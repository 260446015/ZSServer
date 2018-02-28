package com.huishu.ManageServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * 添加h5数据
 *
 * @author yindq
 * @date 2018/2/7
 */
public class HtmlAddDTO implements Serializable {
	private String name;
	private String time;
	private JSONObject[] keyWord;
	private JSONObject[] chain;
	private String[] focus;
	private JSONObject recommend;
	private String[] dynamic;
	private JSONObject industry;

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

	public JSONObject[] getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(JSONObject[] keyWord) {
		this.keyWord = keyWord;
	}

	public JSONObject[] getChain() {
		return chain;
	}

	public void setChain(JSONObject[] chain) {
		this.chain = chain;
	}

	public String[] getFocus() {
		return focus;
	}

	public void setFocus(String[] focus) {
		this.focus = focus;
	}

	public JSONObject getRecommend() {
		return recommend;
	}

	public void setRecommend(JSONObject recommend) {
		this.recommend = recommend;
	}

	public String[] getDynamic() {
		return dynamic;
	}

	public void setDynamic(String[] dynamic) {
		this.dynamic = dynamic;
	}

	public JSONObject getIndustry() {
		return industry;
	}

	public void setIndustry(JSONObject industry) {
		this.industry = industry;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
