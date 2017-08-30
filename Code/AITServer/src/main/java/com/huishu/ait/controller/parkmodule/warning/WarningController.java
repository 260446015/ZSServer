package com.huishu.ait.controller.parkmodule.warning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
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
	public AjaxResult getBusinessOutflowList(@RequestBody AreaSearchDTO searchModel){
		if(StringUtil.isEmpty(getUserPark())){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		searchModel.setPark(getUserPark());
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
	public AjaxResult getInformationChangeList(@RequestBody AreaSearchDTO searchModel){
		if(StringUtil.isEmpty(getUserPark())){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		searchModel.setPark(getUserPark());
		if(null==searchModel || null==searchModel.getPark()){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			JSONArray array = warningService.getInformationChangeList(searchModel);
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
	@RequestMapping(value="getInformationChangeById.json",method=RequestMethod.GET)
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
	/**
	 * 获取辖区预警数量
	 */
	@RequestMapping(value="getGardenWarningCout.json",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGardenWarningCout(){
		AreaSearchDTO searchModel = new AreaSearchDTO();
		searchModel.setPark(getUserPark());
		JSONObject obj = new JSONObject();
		JSONArray arr = warningService.getBusinessOutflowList(searchModel);
		obj.put("count", arr.size());
		return success(obj);
	}
	
}
