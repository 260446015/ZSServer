package com.huishu.ManageServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * 报告段落添加DTO
 *
 * @author yindq
 * @date 2018/2/8
 */
public class ParagraphAddDTO {
	private Long id;
	private String text;
	private Object[] obj;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Object[] getObj() {
		return obj;
	}

	public void setObj(Object[] obj) {
		this.obj = obj;
	}
}
