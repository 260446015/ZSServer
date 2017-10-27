package com.huishu.ZSServer.controller.garden;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.dto.GardenDTO;
import com.huishu.ZSServer.service.garden.GardenService;


/**
 * 处理园区的controller
 * @author yindawei 
 * @date 2017年10月27日上午11:39:44
 * @description 
 * @version
 */
@RestController
@RequestMapping("/apis/area")
public class GardenController extends BaseController{
	private static Logger LOGGER = LoggerFactory.getLogger(GardenController.class);
	@Autowired
	private GardenService gardenService;

	/**
	 * 获取园区动态
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/findGardensCondition.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findGardensCondition(@RequestBody GardenDTO dto) {
//		Long userId = getUserId();
		Long userId = 1L;
		JSONArray aITInfos = null;
		dto.setUserId(userId.intValue());
		try {
			aITInfos = gardenService.findGardensCondition(dto);
		} catch (Exception e) {
			LOGGER.error("查询园区动态失败!", e);
			return error(e.getMessage());
		}
		return success(aITInfos);
	}
}
