package com.huishu.ZSServer.controller.industry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.controller.BaseController;

/**
 * @author hhy
 * @date 2017年10月27日
 * @Parem
 * @return
 * 
 */
@Controller
@RequestMapping("/apis/industry")
public class IndustryController extends BaseController{
	private Logger LOGGER = LoggerFactory.getLogger(IndustryController.class);
	
	
	@ResponseBody
	@RequestMapping(value = "/List", method = RequestMethod.POST)
	public AjaxResult ListIndustry(String[] msg) {
		
		String industryLabel = "大数据";
		
		
		return success("查询成功");
	}
}
