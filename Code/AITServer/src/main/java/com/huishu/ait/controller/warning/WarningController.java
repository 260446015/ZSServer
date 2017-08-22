package com.huishu.ait.controller.warning;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.service.warning.WarningService;

/**
 * 园区预警
 * @author yindq
 * @date 2017年8月3日
 */
@Controller
@RequestMapping(value="/apis/warning")
public class WarningController extends BaseController{
	private static Logger LOGGER = LoggerFactory.getLogger(WarningController.class);
	
	@Autowired
	private WarningService warningService;
	
	/**
	 * 企业疑似外流列表
	 * @param searchModel   查询条件
	 * @return
	 */
	@RequestMapping(value="getBusinessOutflowList.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getBusinessOutflowList(@RequestBody  AreaSearchDTO searchModel){
		//假数据
		searchModel.setPark("中关村软件园");
		if (null==searchModel || null==searchModel.getPark()) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			JSONArray array = warningService.getBusinessOutflowList(searchModel);
			return success(changeObject(searchModel, array));
		} catch (Exception e) {
			LOGGER.error("getBusinessOutflowList查询失败！",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 企业疑似外流详情
	 * @param id   政策ID
	 * @return
	 */
	@RequestMapping(value="getBusinessOutflowById.json",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getBusinessOutflowById(String id){
		if(null==id){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(warningService.getBusinessOutflowById(id));
		} catch (Exception e) {
			LOGGER.error("getBusinessOutflowById查询失败！",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 信息变更预警列表
	 * @param searchModel   查询条件
	 * @return
	 */
	@RequestMapping(value="getInformationChangeList.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getInformationChangeList(AreaSearchDTO searchModel,HttpServletRequest request,HttpServletResponse response){
		//假数据
		searchModel.setPark("中关村软件园");
		if(null==searchModel || null==searchModel.getPark()){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			JSONArray array = warningService.getInformationChangeList(searchModel,request,response);
			return success(changeObject(searchModel, array));
		} catch (Exception e) {
			LOGGER.error("getBusinessOutflowList查询失败！",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 信息变更预警详情
	 * @param id   政策ID
	 * @return
	 */
	@RequestMapping(value="getInformationChangeById.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getInformationChangeById(String id){
		if(null==id){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(warningService.getInformationChangeById(id));
		} catch (Exception e) {
			LOGGER.error("getBusinessOutflowById查询失败！",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
}
