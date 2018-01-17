package com.huishu.ManageServer.controller.industryinfo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huishu.ManageServer.controller.BaseController;

/**
 * @author hhy
 * @date 2018年1月15日
 * @Parem
 * @return 
 * 产业动态
 */
@Controller
@RequestMapping("/apis/industryinfo")
public class IndustryInfoController extends BaseController {
	private static final Logger LOGGER = Logger.getLogger(IndustryInfoController.class);
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "{page}" }, method = RequestMethod.GET)
	public String findAccurateCompany(@PathVariable String page) {
		return "/industry/info/" + page;
	}
}
