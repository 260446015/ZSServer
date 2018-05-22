package com.huishu.ManageServer.controller.industryinfo;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbFirst.Institutional;
import com.huishu.ManageServer.service.industry.map.InstitutionalService;

/**
 * @author hhy
 * @date 2018年2月7日
 * @Parem
 * @return 
 * 国家重点实验室的相关操作
 */
@Controller
@RequestMapping("/apis/institution")
public class InstitutionalController extends BaseController{
	private static final Logger LOGGER = Logger.getLogger(InstitutionalController.class);
	@Autowired
	private InstitutionalService service;
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "{page}.html" }, method = RequestMethod.GET)
	public String findAccurateCompany(@PathVariable String page) {
		return "/industry/ins/" + page;
	}
	/**
	 * 获取重点实验室列表
	 * @param industry
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findInstitutionalInfo.json", method = RequestMethod.GET)
	public AjaxResult findInstitutionalInfo(String industry){
		if(StringUtil.isEmpty(industry)){
			LOGGER.error("获取国家重点实验室时异常，原因是：参数为"+industry);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(service.getInstitutionListByIndustry(industry));
	}
	/**
	 * 根据id删除重点实验室
	 * @param industry
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteInstitutionInfo.json", method = RequestMethod.GET,params={"id"})
	public AjaxResult deleteInstitutionInfo(String id){
		if(StringUtil.isEmpty(id)){
			LOGGER.error("删除国家重点实验室时异常，原因是：参数为"+id);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(service.deleteInstitutionalInfoById(id));
	}
	@ResponseBody
	@RequestMapping(value = "/insertInstitutionInfo.json", method = RequestMethod.POST)
	public AjaxResult insertInstitutionInfo(Institutional ent){
		if(ent==null){
			LOGGER.error("保存国家重点实验室时异常，原因是：参数为"+ent);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(service.saveOrUpdateInstitutionInfo(ent));
	}
	
}
