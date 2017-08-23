package com.huishu.ait.controller.companyInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.indusCompany.IndusCompanyService;

/**
 * @author hhy
 * @date 2017年8月21日
 * @Parem
 * @return
 * 
 */
@Controller
@RequestMapping("/apis/comp")
public class CompanyInfoController extends BaseController {
	@Autowired
	private IndusCompanyService service;

	/**
	 * 根据公司全名查询公司的详细信息
	 * 
	 * @param company
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCompanyInfoByCompany.json", method = RequestMethod.POST)

	public AjaxResult getCompanyInfoByCompany(@RequestBody String company) {
		if (company.isEmpty()) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject  json = (JSONObject) JSONObject.parse(company);
		String str =(String) json.get("company").toString();
		return success(service.findInfo(str).get("data"));
	}
}
