package com.huishu.ait.controller.supervise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.gardenSupervise.GardenSuperviseService;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：园区监管的controller
 */
@RestController
@RequestMapping(value = "supervise")
public class GardenSuperviseController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(GardenSuperviseController.class);
	
	@Autowired
	private GardenSuperviseService gardenSuperviseService;
	
	//TODO:获取当前用户所在的园区
	//获取当前用户所在的园区
	String park = "中关村软件园";
	/**
	 * @return
	 * 获取当前园区的信息
	 */
	@RequestMapping(value = "getGardenInfo.json")
	public AjaxResult getGardenInfo(){
		try {
			JSONObject json = gardenSuperviseService.getGardenInfo(park);
			return success(json);
		} catch (Exception e) {
			log.error("获取园区信息失败", e.getMessage());
			return null;
		}
	}
	/**
	 * @return
	 * 获取当前园区中的所有企业列表
	 */
	@RequestMapping(value = "getCompanyFromGarden.json")
	public AjaxResult getCompanyFromGarden(){
		try {
			JSONArray jsonArray = gardenSuperviseService.getCompanyFromGarden(park);
			return success(jsonArray);
		} catch (Exception e) {
			log.error("查询园区企业失败", e.getMessage());
			return null;
		}
	}
	
}
