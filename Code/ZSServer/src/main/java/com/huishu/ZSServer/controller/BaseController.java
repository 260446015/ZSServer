package com.huishu.ZSServer.controller;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.util.ConcersUtils.DateUtil;
import com.huishu.ZSServer.security.ShiroDbRealm.ShiroUser;

/**
 * @author hhy
 * @date 2017年10月27日
 * @Parem
 * @return 
 * 
 */
public abstract class BaseController {
	public AjaxResult success(Object data) {
		return new AjaxResult().setData(data).setSuccess(true).setStatus(0);
	}

	public AjaxResult error(String message) {
		return new AjaxResult().setMessage(message).setSuccess(false).setStatus(1);
	}
	
	public AjaxResult successPage(Page<?> data,Integer pageNum) {
		JSONObject result = new JSONObject();
		result.put("dataList", data.getContent());
		result.put("totalNumber",data.getTotalElements());
		result.put("totalPage",data.getTotalPages());
		result.put("pageNumber",pageNum);
		return new AjaxResult().setData(result).setSuccess(true).setStatus(0);
	}
	
	/**
	 * 获取shiro中用户信息
	 * @return
	 */
	public ShiroUser getCurrentShiroUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
	
	public Long getUserId() {
		return getCurrentShiroUser().getId();
	}

	public String getUserAccount() {
		return getCurrentShiroUser().getLoginName();
	}

	public JSONObject initTime(JSONObject obj,String time){
		Date date = new Date();
		String endTime = DateUtil.getFormatDate(date, DateUtil.FORMAT_DATE); // 今天的当前时间（获取服务端时间）
		String startTime = DateUtil.getFormatDate(DateUtil.getStartTime(), DateUtil.FORMAT_DATE); // 今天的起始时间
		String yesterAgo = DateUtil.getFormatDate(DateUtil.getYesterAgoStartTime(date), DateUtil.FORMAT_DATE); // 昨天的起始时间
		String weekAgo = DateUtil.getFormatDate(DateUtil.getWeekAgoStartTime(date), DateUtil.FORMAT_DATE); // 近7天的起始时间
		String monthAgo = DateUtil.getFormatDate(DateUtil.getMonthAgoStartTime(date), DateUtil.FORMAT_DATE); // 一个月内
		String halfYearAgo = DateUtil.getFormatDate(DateUtil.getHalfYearStartTime(date), DateUtil.FORMAT_DATE); // 半年内
		String yearAgo = DateUtil.getFormatDate(DateUtil.getYearStartTime(date), DateUtil.FORMAT_DATE); // 一年内
		if(time.equals("昨天")){
			obj.put("startTime", yesterAgo);
			obj.put("endTime", startTime);
		}else if(time.equals("近一周")){
			obj.put("startTime", weekAgo);
			obj.put("endTime", endTime);
		} else if(time.equals("近一个月")){
			obj.put("startTime",monthAgo);
			obj.put("endTime", endTime);
		} else if(time.equals("近6个月")){
			obj.put("startTime",halfYearAgo);
			obj.put("endTime", endTime);
		} else if(time.equals("近一年")){
			obj.put("startTime",yearAgo);
			obj.put("endTime", endTime);
		}
		return obj;
	}
}
