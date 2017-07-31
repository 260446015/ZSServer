package com.huishu.ait.garden.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.Garden;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.garden.service.GardenService;

@RestController
public class GardenController extends BaseController {
	
	@Resource
	private GardenService gardenService;
	private static Logger LOGGER = Logger.getLogger(GardenController.class);

	@RequestMapping("/findGardensList")
	public AjaxResult findGardensList(GardenDTO dto){
		if(null == dto){
			 return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONArray gardens = null;
		try{
			gardens = gardenService.findGardensList(dto);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.info(e);
			return error(e.getMessage()).setSuccess(false);
		}
		return success(gardens).setSuccess(true);
	}
	@RequestMapping("/findGardensCondition")
	public AjaxResult findGardensCondition(GardenDTO dto){
		if(null == dto){
			 return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONArray aITInfos = null;
		try{
			aITInfos = gardenService.findGardensCondition(dto);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.info(e);
			return error(e.getMessage()).setSuccess(false);
		}
		return success("").setSuccess(true);
	}
	
}
