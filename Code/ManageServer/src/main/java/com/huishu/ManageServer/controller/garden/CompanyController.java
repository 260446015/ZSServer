package com.huishu.ManageServer.controller.garden;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbFirst.Company;
import com.huishu.ManageServer.entity.dto.CompanySearchDTO;
import com.huishu.ManageServer.service.garden.GardenCompanyService;

@Controller
@RequestMapping("/apis/company/")
public class CompanyController extends BaseController{

	@Autowired
	private GardenCompanyService comService;
	private static Logger log = LoggerFactory.getLogger(CompanyController.class);
	/**
	 * 查询园区内企业
	 */
	@RequestMapping(value = "/findGardenCompany.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findGardenCompany(@RequestBody CompanySearchDTO dto) {
		Page<Company> page = null;
		try {
			page = comService.findCompanyList(dto);
		} catch (Exception e) {
			log.info("保存园区失败!", e);
		}
		return success(page);
	}
	
	/**
	 * 保存园区内企业
	 */
	@RequestMapping(value = "/saveGardenCompany.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveGardenCompany(@RequestBody Company company) {
		boolean flag = false;
		try {
			flag = comService.saveGardenCompany(company);
		} catch (Exception e) {
			log.info("保存园区失败!", e);
			return error("保存园区失败");
		}
		return success(flag);
	}
}
