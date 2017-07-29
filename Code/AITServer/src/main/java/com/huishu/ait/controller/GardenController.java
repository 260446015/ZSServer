package com.huishu.ait.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.service.garden.GardenService;


/**
 * 全景辖区概览
 * @author yindq
 * @date   2017-7-28
 */
@Controller
@RequestMapping("garden")
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
	public AjaxResult getGardenPolicyList(SearchModel searchModel){
		if(null==searchModel || null==searchModel.getPark()){
			return error("请传入完整的参数！");
		}
		try {
			JSONObject object = new JSONObject();
			object.put("park", searchModel.getPark());
			object.put("list",gardenService.getGardenPolicyList(searchModel));
			object.put("totalSize", searchModel.getTotalSize());
			object.put("totalPage", searchModel.getTotalPage());
			object.put("pageNumber", searchModel.getPageNumber());
			return success(object);
		} catch (Exception e) {
			LOGGER.error("getGardenPolicyList查询失败！"+e.getMessage());
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
		if(null==id){
			return error("请传入完整的参数！");
		}
		try {
			return success(gardenService.getGardenPolicyById(id));
		} catch (Exception e) {
			LOGGER.error("getGardenPolicyById查询失败！"+e.getMessage());
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
	public AjaxResult getGardenInformationList(SearchModel searchModel){
		if(null==searchModel || null==searchModel.getPark()){
			return error("请传入完整的参数！");
		}
		try {
			JSONObject object = new JSONObject();
			object.put("park", searchModel.getPark());
			object.put("list",gardenService.getGardenInformationList(searchModel));
			object.put("totalSize", searchModel.getTotalSize());
			object.put("totalPage", searchModel.getTotalPage());
			object.put("pageNumber", searchModel.getPageNumber());
			return success(object);
		} catch (Exception e) {
			LOGGER.error("getGardenInformationList查询失败！"+e.getMessage());
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
		if(null==id){
			return error("请传入完整的参数！");
		}
		try {
			return success(gardenService.getGardenInformationById(id));
		} catch (Exception e) {
			LOGGER.error("getGardenInformationById查询失败！"+e.getMessage());
			return error("查询动态详情失败！");
		}
	}
	
}
