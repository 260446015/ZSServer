package com.huishu.ManageServer.controller.yq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huishu.ManageServer.controller.BaseController;

/**
 * @author hhy
 * @date 2018年1月10日
 * @Parem
 * @return 
 * 产业标签关键词管理
 */
@Controller
@RequestMapping("/apis/keywordinfo")
public class YQKeywordController extends BaseController{
	@RequestMapping(value = { "{page}" }, method = RequestMethod.GET)
	public String findKeywordInfo(@PathVariable String page) {
		return "/keyword/" + page;
	}
}
