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
	private List keyWord;
	private JSONObject[] chain;
	private JSONObject[] focus;
	private List recommend;
	private List dynamic;
	private List industry;

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

	public List getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(List keyWord) {
		this.keyWord = keyWord;
	}

	public JSONObject[] getChain() {
		return chain;
	}

	public void setChain(JSONObject[] chain) {
		this.chain = chain;
	}

	public JSONObject[] getFocus() {
		return focus;
	}

	public void setFocus(JSONObject[] focus) {
		this.focus = focus;
	}

	public List getRecommend() {
		return recommend;
	}

	public void setRecommend(List recommend) {
		this.recommend = recommend;
	}

	public List getDynamic() {
		return dynamic;
	}

	public void setDynamic(List dynamic) {
		this.dynamic = dynamic;
	}

	public List getIndustry() {
		return industry;
	}

	public void setIndustry(List industry) {
		this.industry = industry;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
