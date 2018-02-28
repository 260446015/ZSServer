package com.huishu.ManageServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 报告段落添加DTO
 *
 * @author yindq
 * @date 2018/2/8
 */
public class ParagraphAddDTO implements Serializable {
	private Long id;
	private JSONObject[] obj;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JSONObject[] getObj() {
		return obj;
	}

	public void setObj(JSONObject[] obj) {
		this.obj = obj;
	}
}
