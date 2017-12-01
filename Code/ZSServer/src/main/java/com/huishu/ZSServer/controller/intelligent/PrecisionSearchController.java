package com.huishu.ZSServer.controller.intelligent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huishu.ZSServer.controller.BaseController;

/**
 * @author hhy
 * @date 2017年11月30日
 * @Parem
 * @return 
 * 精确筛选相关controller
 */
@Controller
@RequestMapping("/accurateScreening")
public class PrecisionSearchController extends BaseController{
	private Logger LOGGER = LoggerFactory.getLogger(PrecisionSearchController.class);
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return "/search/"+page;
	}
	
}
