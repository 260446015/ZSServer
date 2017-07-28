package com.huishu.ait.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.GardenService;

/**
 * 全景辖区概览
 * @author yindq
 * @date
 */
@Controller
public class GardenController extends BaseController{
	private static Logger LOGGER = Logger.getLogger(GardenController.class);
	
	@Autowired
	private GardenService gardenService;

	/**
	 * 辖区政策列表
	 * @param park   园区
	 * @return
	 */
	@RequestMapping("getGardenPolicyList.do")
	@ResponseBody
	public AjaxResult getGardenPolicyList(String park,String pageNumber,String pageSize){
		List<JSONObject> data = gardenService.getGardenPolicyList(park, pageNumber, pageSize);
		JSONObject object = new JSONObject();
		object.put("park", park);
		object.put("pageNumber", pageNumber);
		object.put("pageSize", pageSize);
		object.put("list", data);
		return success(object);
	}
	
	/**
	 * 辖区情报
	 * @param park   园区
	 * @return
	 */
	@RequestMapping("getGardenInformationList.do")
	@ResponseBody
	public AjaxResult getGardenInformationList(String park){
		LOGGER.info("输出---");
		return error("无数据---");
		
	}
	
}
