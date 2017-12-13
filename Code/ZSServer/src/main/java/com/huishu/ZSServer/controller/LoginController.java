package com.huishu.ZSServer.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.util.ShiroUtil;

/**
 * 登录与相关模块
 * 
 * @author yindq
 * @date 2017年10月31日
 */
@Controller
public class LoginController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return page;
	}

	/**
	 * 没有权限
	 */
	@RequestMapping(value = "/apis/unauthorized.do", method = RequestMethod.GET)
	public void unauthorized(HttpServletResponse response) {
		ShiroUtil.writeResponse(response, "对不起,您没有访问权限！");
	}
	
}
