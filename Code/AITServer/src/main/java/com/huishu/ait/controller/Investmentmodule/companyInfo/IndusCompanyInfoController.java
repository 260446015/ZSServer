package com.huishu.ait.controller.Investmentmodule.companyInfo;

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
 * @date 2017年8月11日
 * 
 * 
 */
@Controller
@RequestMapping("/apis/indus")
public class IndusCompanyInfoController extends BaseController {
	@Autowired
	private IndusCompanyService service;

	/**
	 * 根据产业名查询公司的信息
	 * 
	 * @param industry
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCompanyInfoByIndustry.json", method = RequestMethod.POST)
	public AjaxResult getCompanyInfoByIndustry() {
		
		return success(service.findIndusInfoByIndustry());
	}
	/*public AjaxResult getCompanyInfoByIndustry(@RequestBody String industry) {
		if (industry.isEmpty()) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject jsonobj = (JSONObject) JSONObject.parse(industry);
		String str = (String) jsonobj.get("industry");
		return success(service.findIndusInfoByIndustry(str));
	}*/

}
