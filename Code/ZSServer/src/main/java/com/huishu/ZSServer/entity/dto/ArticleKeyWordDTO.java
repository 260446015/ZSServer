package com.huishu.ZSServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年10月31日
 * @Parem
 * @return 
 * 
 */
public class ArticleKeyWordDTO extends AbstractDTO{

	private static final long serialVersionUID = 1L;
	
	private String id ; 
	private String keyword;
	private String industryLabel;
	private String industry;
	private String title;
	private String area;
	private String [] msg;
	public void setId(String id) {
		this.id = id;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setIndustryLabel(String industryLabel) {
		this.industryLabel = industryLabel;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setMsg(String[] msg) {
		this.msg = msg;
	}
	public String getId() {
		return id;
	}
	public String getKeyword() {
		return keyword;
	}
	public String getIndustryLabel() {
		return industryLabel;
	}
	public String getIndustry() {
		return industry;
	}
	public String getTitle() {
		return title;
	}
	public String getArea() {
		return area;
	}
	public String[] getMsg() {
		return msg;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
