package com.huishu.ManageServer.entity.dto.dbThird;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2018年4月9日
 * @Parem
 * @return 
 * 新增词以及属性
 */
public class AttributeDTO {
	//词性id
	private Long typeId;
	//关键词
	private String keyword;
	//描述
	private String describe;
	//新增类型值
	private String typeWord;
	//属性值	
	private List<AttributeInfo> msg;
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getTypeWord() {
		return typeWord;
	}
	public void setTypeWord(String typeWord) {
		this.typeWord = typeWord;
	}
	public List<AttributeInfo> getMsg() {
		return msg;
	}
	public void setMsg(List<AttributeInfo> msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
