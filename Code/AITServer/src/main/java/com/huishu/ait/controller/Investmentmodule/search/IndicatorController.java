package com.huishu.ait.controller.Investmentmodule.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.dto.IndicatorDTO;
import com.huishu.ait.service.indicator.IndicatorService;

/**
 * @author hhy
 * @date 2017年9月5日
 * @Parem
 * @return 
 * 
 */
@Controller
@RequestMapping("/apis")
public class IndicatorController extends BaseController{
	
	@Autowired
	private IndicatorService service;
	/**
	 * 获取四级产业分类
	 * /apis/getIndicator.json
	 * 
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getIndicator.json",method=RequestMethod.POST)
	public  AjaxResult  getIndicator(IndicatorDTO dto){
//		if(dto == null){
//			return error(MsgConstant.ILLEGAL_PARAM);
//		}
		JSONArray jsonArray = service.getIndicatorList(dto);
		return success(jsonArray);
	}
	/**
	 * 根据指标获取公司名录
	 * /apis/getBusinessByIndicator.json
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getBusinessByIndicator.json",method=RequestMethod.POST)
	public  AjaxResult  getBusinessByIndicator(IndicatorDTO dto){
		if(dto == null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONArray jsonArray = service.getBusinessByIndicator(dto);
		return success(jsonArray);
	}
}
