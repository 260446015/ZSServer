package com.huishu.ManageServer.controller.enterprise;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbFirst.IndusCompany;
import com.huishu.ManageServer.service.enterprise.IndustryCompanyService;

/**
 * @author hhy
 * @date 2018年3月19日
 * @Parem
 * @return 
 * 智能推荐相关控制类
 */
@Controller
@RequestMapping("/apis/inds")
public class IndustryCompanyController extends BaseController {
	private static final Logger LOGGER = Logger.getLogger(IndustryCompanyController.class);
	@Autowired
	private IndustryCompanyService service;
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value={"{page}.html"},method=RequestMethod.GET)
	public String pageJump(@PathVariable String page){
		return "/enterprise/"+page;
	}
	/**
	 * 获取所有的推荐数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/listIndustInfo.json",method= RequestMethod.GET)
	public AjaxResult listIndustInfo(){
		try {
			List<IndusCompany> list = service.ListAllInfo();
			return success(list);
		} catch (Exception e) {
			LOGGER.debug("获取智能推荐失败，原因是:",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	/**
	 * 根据id删除智能推荐产业数据
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteIndusInfo.json",method =RequestMethod.GET,params={"id"})
	public AjaxResult deleteIndusInfo(String id){
		if(StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			boolean info = service.deleteInfoById(id);
			return success(info);
		} catch (Exception e) {
			LOGGER.debug("删除智推企业信息失败,原因是：",e);		
			return error(MsgConstant.SYSTEM_ERROR);
		}		
	}
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateIndusInfo.json",method = RequestMethod.POST)
	public AjaxResult addOrUpdateIndusInfo(@RequestBody IndusCompany ent){
		if(ent==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			boolean info = service.saveOrUpdateInfo(ent);
			return success(info);
		} catch (Exception e) {
			LOGGER.debug("新增或者更新智推企业信息失败,原因是：",e);		
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}
