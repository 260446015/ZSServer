package com.huishu.ait.controller.skyeye;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.skyeye.SkyEyeService;

/**
 * @author yindawei 
 * @date 2017年8月8日下午1:55:23
 * @description 实现与天眼查系统接口对接的Controller
 * @version 1.0
 */
@RestController
@RequestMapping("/skyEye")
public class SkyEyeController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SkyEyeController.class); 
	@Autowired
	private SkyEyeService service;

	/**
	 * 查询企业画像的Controller
	 * @param params  前台传递过来的数据，暂定是通过键值对的方式
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findBaseCompany",method=RequestMethod.GET)
	public AjaxResult findBaseCompany(Map<String, String> params,HttpServletRequest request,HttpServletResponse response){
		JSONArray arr = null;
		try{
			arr = service.findBaseCompany(params,request,response);
		}catch(Exception e){
			error("查询企业画像出错");
			LOGGER.error("查询企业画像出错",e);
		}
		return success(arr);
	}
	
	
	/**
	 * 查询企业业务画像的Controller
	 * @param params   前台传递过来的数据，暂定是通过键值对的方式
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findBaseCompanyBusiness",method=RequestMethod.GET)
	public AjaxResult findBaseCompanyBusiness(Map<String, String> params,HttpServletRequest request,HttpServletResponse response){
		JSONArray arr = null;
		try{
			arr = service.findBaseCompanyBusiness(params,request,response);
		}catch(Exception e){
			error("查询企业业务画像出错");
			LOGGER.error("查询企业业务画像出错",e);
		}
		return success(arr);
	}
	
	/**
	 * 查询查询企业图谱的Controller
	 * @param params   企业id
	 * @return
	 */
	@RequestMapping(value="/findCompanyAtlas",method=RequestMethod.GET)
	public AjaxResult findCompanyAtlas(Map<String, String> params,HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = null;
		try{
			obj = service.findBaseService("xxx", params, request, response);
		}catch(Exception e){
			error("查询企业图谱出错");
			LOGGER.error("查询企业图谱出错",e);
		}
		return success(obj);
	}
	
	/**
	 * 查询企业拓扑图的Controller
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findCompanyTopology",method=RequestMethod.GET)
	public AjaxResult findCompanyTopology(Map<String, String> params,HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = null;
		try{
			obj = service.findBaseService("xxx", params, request, response);
		}catch(Exception e){
			error("查询企业图谱出错");
			LOGGER.error("查询企业图谱出错",e);
		}
		return success(obj);
	}

	/**
	 * 查询企业关系挖掘的Controller
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findRelationExcavate",method=RequestMethod.GET)
	public AjaxResult findRelationExcavate(Map<String, String> params,HttpServletRequest request,HttpServletResponse response){
		JSONObject obj = null;
		try{
			obj = service.findBaseService("xxx", params, request, response);
		}catch(Exception e){
			error("查询企业图谱出错");
			LOGGER.error("查询企业图谱出错",e);
		}
		return success(obj);
	}
	
	@RequestMapping(value="/searchFromSkyEye",method=RequestMethod.GET)
	public AjaxResult searchFromSkyEye(String searchName){
		//增加自动登录功能
		if(StringUtil.isEmpty(searchName)){
			//调用天眼查搜索接口
		}else{
			//调用天眼查搜索详细查询功能
		}
		return success("");
	}
	
	public AjaxResult findInfoChanges(){
		return success("");
	}
	
	
}
