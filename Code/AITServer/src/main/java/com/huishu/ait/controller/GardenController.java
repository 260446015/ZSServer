package com.huishu.ait.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.garden.GardenService;


/**
 * 全景辖区概览
 * @author yindq
 * @date   2017-7-28
 */
@Controller
@RequestMapping("garden")
public class GardenController extends BaseController{
	private static Logger LOGGER = Logger.getLogger(GardenController.class.getName());
	
	@Autowired
	private GardenService gardenService;

	/**
	 * 辖区政策列表
	 * @param park   园区
	 * @return
	 */
	@RequestMapping("getGardenPolicyList.do")
	@ResponseBody
	public AjaxResult getGardenPolicyList(String park){
		try {
			JSONObject data = gardenService.getGardenPolicyList(park);
			JSONObject object = new JSONObject();
			object.put("park", park);
			object.put("totalSize", data.get("total"));
			object.put("list", data.get("list"));
			return success(object);
		} catch (Exception e) {
			LOGGER.error("getGardenPolicyList查询失败！"+e);
			return error("查询政策列表失败！");
		}
	}
	
	/**
	 * 辖区政策详情
	 * @param id   政策ID
	 * @return
	 */
	@RequestMapping("getGardenPolicyById.do")
	@ResponseBody
	public AjaxResult getGardenPolicyById(String id){
		try {
			return success(gardenService.getGardenPolicyById(id));
		} catch (Exception e) {
			LOGGER.error("getGardenPolicyById查询失败！"+e);
			return error("查询政策详情失败！");
		}
	}
	
	/**
	 * 辖区动态列表
	 * @param park   园区
	 * @return
	 */
	@RequestMapping("getGardenInformationList.do")
	@ResponseBody
	public AjaxResult getGardenInformationList(String park){
		try {
			JSONObject data = gardenService.getGardenInformationList(park);
			JSONObject object = new JSONObject();
			object.put("park", park);
			object.put("totalSize", data.get("total"));
			object.put("list", data.get("list"));
			return success(object);
		} catch (Exception e) {
			LOGGER.error("getGardenInformationList查询失败！"+e);
			return error("查询动态列表失败！");
		}
	}
	
	/**
	 * 辖区动态详情
	 * @param id   动态ID
	 * @return
	 */
	@RequestMapping("getGardenInformationById.do")
	@ResponseBody
	public AjaxResult getGardenInformationById(String id){
		try {
			return success(gardenService.getGardenInformationById(id));
		} catch (Exception e) {
			LOGGER.error("getGardenInformationById查询失败！"+e);
			return error("查询动态详情失败！");
		}
	}
	
}
