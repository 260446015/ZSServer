package com.huishu.ZSServer.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

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
	 * 未登录
	 */
	@RequestMapping(value = "apis/login.do", method = RequestMethod.GET)
	public void login(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject object = new JSONObject();
		object.put("code", "1002");
	}

	/**
	 * 没有权限
	 */
	@RequestMapping(value = "apis/unauthorized.do", method = RequestMethod.GET)
	public void unauthorized(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject object = new JSONObject();
		object.put("code", "1004");
	}
	
}
