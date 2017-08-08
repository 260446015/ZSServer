package com.huishu.ait.controller.skyeye;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
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
@ResponseBody
@RequestMapping("/skyEye")
public class SkyEyeController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SkyEyeController.class); 
	@Autowired
	private SkyEyeService service;

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
}
