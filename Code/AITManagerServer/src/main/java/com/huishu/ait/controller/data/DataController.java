package com.huishu.ait.controller.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 数据管理模块
 * 
 * @author yindq
 * @create 2017年10月24日
 */
@Controller
@RequestMapping(value = "/apis/data")
public class DataController {
	
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return "data/"+page;
	}
}
