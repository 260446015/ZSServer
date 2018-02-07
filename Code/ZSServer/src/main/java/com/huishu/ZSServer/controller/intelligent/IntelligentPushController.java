package com.huishu.ZSServer.controller.intelligent;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.IndusCompany;
import com.huishu.ZSServer.entity.dto.IndusCompanyDTO;
import com.huishu.ZSServer.service.company.EnterPriseService;
import com.huishu.ZSServer.service.company.IndusCompanyDTOService;
import com.huishu.ZSServer.service.company.IndustryCompanyService;

/**
 * @author hhy
 * @date 2017年11月30日
 * @Parem
 * @return 
 * 智能推荐相关接口文档
 */
@Controller
@RequestMapping("/intelligent")
public class IntelligentPushController extends BaseController{
	private Logger LOGGER = LoggerFactory.getLogger(IntelligentPushController.class);
	
	@Autowired
	private  IndustryCompanyService  service;
	
	@Autowired
	private EnterPriseService epservice;
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return "/search/"+page;
	}
	
	/**
	 * 获取智推企业列表数据
	 */
	@ResponseBody
	@RequestMapping(value="/list.json",method=RequestMethod.GET)
	public AjaxResult getInteList(){
		List<IndusCompany> list = service.listCompany();
		JSONArray arr = new JSONArray();
		list.forEach(action->{
			JSONObject obj = new JSONObject();
			if(!action.getCreateTime().equals(action.getUpdateTime())){
				 obj.put("flag", true);
			}else{
				obj.put("flag", false);
				
			}
			if(StringUtil.isEmpty(action.getIndustry().trim())){
				obj.put("industry", null);
			}else{
				obj.put("industry", action.getIndustry());
			}
			if(StringUtil.isEmpty( action.getInduszero())){
				obj.put("industryZero", null);
				
			}else{
				
				obj.put("industryZero", action.getInduszero());
			}
			if(StringUtil.isEmpty(action.getIndustryLabel())){
				obj.put("industryLabel", null);
				
			}else{
				
				obj.put("industryLabel", action.getIndustryLabel());
			}
			obj.put("company", action.getCompany());
			obj.put("companyName", action.getCompanyName());
			obj.put("id", action.getId());
			arr.add(obj);
		});
		return success(arr);
	}
	
	/**
	 * 根据别名查看详细信息
	 */
	@ResponseBody
	@RequestMapping(value="/getCompanyInfoByName.json",method=RequestMethod.GET)
	public AjaxResult getCompanyInfoByName( String name){
		
		if(StringUtil.isEmpty(name)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}

		//根据全称获取相应的信息
		JSONObject obj = epservice.getCompanyInfoByCompany(name);
		if(obj==null){
			return error(MsgConstant.SYSTEM_ERROR);
		}
		return success(obj);
	}
	
}
