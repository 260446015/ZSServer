package com.huishu.ZSServer.controller;

import com.huishu.ZSServer.common.AjaxResult;

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
}
