package com.huishu.ManageServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * 关注企业列表
 * 
 * @author yindq
 * @date 2017年12月21日
 */
public class CompanyDTO extends AbstractDTO{

	private static final long serialVersionUID = 7479964523375035506L;
	/** 产业 */
	private String industry;
	/** 成立时间 */
	private String time;
	/** 注册资本 */
	private String money;
	/** 地域 */
	private String area;
	/** 企业分组 */
	private String group;
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		if("全部".equals(industry)||"不限".equals(industry)){
			this.industry = "%%";
		}else{
			this.industry = industry;
		}
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		if("全部".equals(time)||"不限".equals(time)){
			this.time = "";
		}else{
			this.time = time;
		}
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		if("全部".equals(money)||"不限".equals(money)){
			this.money = "";
		}else{
			this.money = money;
		}
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		if("全部".equals(area)||"不限".equals(area)){
			this.area = "%%";
		}else{
			this.area = area;
		}
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		if("全部".equals(group)||"不限".equals(group)){
			this.group = "%%";
		}else{
			this.group = group;
		}
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
