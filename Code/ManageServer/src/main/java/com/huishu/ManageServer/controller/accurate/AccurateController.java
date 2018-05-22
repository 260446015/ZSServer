package com.huishu.ManageServer.controller.accurate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbFirst.IndusCompany;
import com.huishu.ManageServer.entity.dto.AccurateDTO;
import com.huishu.ManageServer.service.accurate.AccurateService;

/**
 * 精准筛选controller
 * 
 * @author yindawei
 * @date 2017年12月27日下午2:43:53
 * @description
 * @version
 */
@Controller
@RequestMapping("/apis/accurate")
public class AccurateController extends BaseController {

	@Autowired
	private AccurateService accurateService;

	@RequestMapping(value = "indusCompany.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findIndusCompany(@RequestBody AccurateDTO dto) {

		return success(accurateService.findAllIndusCompany(dto));
	}

	@RequestMapping(value = "saveIndusCompany.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveIndusCompany(@RequestBody IndusCompany indus) {
		boolean flag = accurateService.saveIndusCompany(indus);
		if (flag) {
			return success(flag);
		} else {
			return error("保存失败!");
		}
	}

	@RequestMapping(value = "delIndusCompany.json", method = RequestMethod.GET, params = "id")
	@ResponseBody
	public AjaxResult delIndusCompany(String id) {
		boolean flag = accurateService.delIndusCompany(id);
		if (flag) {
			return success(flag);
		} else {
			return error("删除失败!");
		}
	}

	@RequestMapping(value = { "{page}.html" }, method = RequestMethod.GET)
	public String findAccurateCompany(@PathVariable String page) {
		return "/accurate/" + page;
	}
}
