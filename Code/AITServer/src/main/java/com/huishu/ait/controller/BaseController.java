package com.huishu.ait.controller;

import java.util.List;

/*import org.apache.shiro.SecurityUtils;*/
import org.springframework.beans.factory.annotation.Autowired;

import com.huishu.ait.entity.common.AjaxResult;



public abstract class BaseController {
	
	
	
	public AjaxResult success(Object data) {
		return new AjaxResult().setData(data).setSuccess(true).setStatus(0);
	}
	
	public AjaxResult error(String message) {
		return new AjaxResult().setMessage(message).setSuccess(false).setStatus(1);
	}

//	public ShiroUser getCurrentShiroUser() {
//		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
//		return user;
//	}
//	
//	public Long getUserId() {
//		return getCurrentShiroUser().getId();
//	}
//	
//	public String getUserAccount() {
//		return getCurrentShiroUser().getLoginName();
//	}
//	
	

}
