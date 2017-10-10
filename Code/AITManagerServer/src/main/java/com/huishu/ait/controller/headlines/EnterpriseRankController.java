package com.huishu.ait.controller.headlines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;

/**
 * @author hhy
 * @date 2017年10月9日
 * @Parem
 * @return 
 * 企业排行的相关操作
 */
@Controller
@RequestMapping("/apis/enterprise")
public class EnterpriseRankController extends BaseController{
	private static Logger LOGGER = LoggerFactory.getLogger(EnterpriseRankController.class);
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "{page}", method = RequestMethod.GET)
	public String showAccount(@PathVariable String page,Model model) {
		
		return "industry/"+page;
	}
}
