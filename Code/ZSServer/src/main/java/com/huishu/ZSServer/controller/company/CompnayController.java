package com.huishu.ZSServer.controller.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.service.company.CompanyService;

/**
 * 
 * @author yindawei
 * @date 2017年10月31日下午2:16:21
 * @description
 * @version
 */
@Controller
@RequestMapping(value = "/apis/company")
public class CompnayController extends BaseController {
	@Autowired
	private CompanyService companyService;

	/**
	 * 查询企业列表（模板导入）
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "findCompanyList.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findCompanyList(@RequestBody CompanySearchDTO dto) {
		if (dto.getMsg().length == 0) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(companyService.findCompanyList(dto));
	}
	/**
	 * 关注/取消天眼查查到的公司
	 */
	@RequestMapping(value = "attationCompany.json", method = RequestMethod.GET,params={"companyId","flag"})
	@ResponseBody
	public AjaxResult attationCompany(Long companyId,Boolean flag){
		if(companyId == null || flag == null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Long userId = getUserId();
		return success(companyService.attationCompany(companyId,flag,userId));
	}
	
	/**
	 * 访问链接
	 */
	@RequestMapping(value = { "{page}" })
	public String companyDetail(@PathVariable String page,@RequestParam(name="companyName") String companyName,Model model) {
		model.addAttribute("companyName", companyName);
		return "company/" + page;
	}
	
	/**
	 * 需要联系人
	 */
	@RequestMapping(value = "telContact.json", method = RequestMethod.GET,params={"name","cname"})
	@ResponseBody
	public AjaxResult telContact(String name,String cname){
		Long userId = getUserId();
		if(StringUtil.isEmpty(cname) || StringUtil.isEmpty(name) || userId == null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		boolean flag = companyService.telContact(name,cname,userId);
		return success(flag);
	}

}
