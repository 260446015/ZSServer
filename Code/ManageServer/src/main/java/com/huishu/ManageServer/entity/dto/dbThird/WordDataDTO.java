package com.huishu.ManageServer.entity.dto.dbThird;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.entity.dbThird.ThesaurusEntity;

/**
 * @author hhy
 * @date 2018年3月28日
 * @Parem
 * @return 
 * 
 */
public class WordDataDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ThesaurusEntity entntity;
	private List<AttributeInfo> info;
	public ThesaurusEntity getEntntity() {
		return entntity;
	}
	public void setEntntity(ThesaurusEntity entntity) {
		this.entntity = entntity;
	}
	public List<AttributeInfo> getInfo() {
		return info;
	}
	public void setInfo(List<AttributeInfo> info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
