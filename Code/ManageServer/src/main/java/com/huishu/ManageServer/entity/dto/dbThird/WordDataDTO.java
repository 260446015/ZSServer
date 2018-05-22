package com.huishu.ManageServer.entity.dto.dbThird;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.entity.dbThird.AttributeEntity;
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
	private String keywordNumber;
	private String typeWord;
		

	public ThesaurusEntity getEntntity() {
		return entntity;
	}


	public void setEntntity(ThesaurusEntity entntity) {
		this.entntity = entntity;
	}


	public String getKeywordNumber() {
		return keywordNumber;
	}


	public void setKeywordNumber(String keywordNumber) {
		this.keywordNumber = keywordNumber;
	}


	public String getTypeWord() {
		return typeWord;
	}


	public void setTypeWord(String typeWord) {
		this.typeWord = typeWord;
	}


	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
