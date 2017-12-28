package com.huishu.ManageServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.entity.dto.AccurateDTO;
import com.huishu.ManageServer.service.accurate.AccurateService;

/**
 * 精准筛选controller
 * @author yindawei 
 * @date 2017年12月27日下午2:43:53
 * @description 
 * @version
 */
@Controller
@RequestMapping("/apis/accurate")
public class AccurateController {

	private AccurateService accurateService;
	
	@RequestMapping(value="indusCompany",method=RequestMethod.POST)
	public AjaxResult findIndusCompany(@RequestBody AccurateDTO dto){
		accurateService.findAllIndusCompany(dto);
		return null;
	}
	
	@RequestMapping(value={"{page}"},method=RequestMethod.GET)
	public String findAccurateCompany(@PathVariable String page){
		return "/accurate/"+page;
	}
}
