package com.huishu.ZSServer.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huishu.ZSServer.controller.BaseController;

/**
 * @author hhy
 * @date 2017年12月11日
 * @Parem
 * @return 
 * 个人中心相关控制
 */
@Controller
@RequestMapping("/user")
public class UserInfoController extends BaseController{

	private Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
	
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/{page}",method=RequestMethod.GET)
	public String show(@PathVariable String page) {
		return "/user/"+page;
	}
}
