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

	
}
