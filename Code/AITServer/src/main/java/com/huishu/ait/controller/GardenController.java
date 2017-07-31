package com.huishu.ait.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.entity.dto.GardenDTO;
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
	 * @param searchModel    查询条件
	 * @return
	 */
	@RequestMapping(value="getGardenPolicyList.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getGardenPolicyList(SearchModel searchModel){
		if(null==searchModel || null==searchModel.getPark()){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			JSONArray array = gardenService.getGardenPolicyList(searchModel);
			return success(changeObject(searchModel, array));
		} catch (Exception e) {
			LOGGER.error("getGardenPolicyList查询失败！"+e.getMessage());
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 辖区政策详情
	 * @param id   政策ID
	 * @return
	 */
	@RequestMapping(value="getGardenPolicyById.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getGardenPolicyById(String id){
		if(null==id){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(gardenService.getGardenPolicyById(id));
		} catch (Exception e) {
			LOGGER.error("getGardenPolicyById查询失败！"+e.getMessage());
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 辖区情报列表
	 * @param searchModel    查询条件
	 * @return
	 */
	@RequestMapping(value="getGardenInformationList.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getGardenInformationList(SearchModel searchModel){
		if(null==searchModel || null==searchModel.getPark()){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			JSONArray array = gardenService.getGardenInformationList(searchModel);
			return success(changeObject(searchModel, array));
		} catch (Exception e) {
			LOGGER.error("getGardenInformationList查询失败！"+e.getMessage());
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 辖区情报详情
	 * @param id   动态ID
	 * @return
	 */
	@RequestMapping(value="getGardenInformationById.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getGardenInformationById(String id){
		if(null==id){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(gardenService.getGardenInformationById(id));
		} catch (Exception e) {
			LOGGER.error("getGardenInformationById查询失败！"+e.getMessage());
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 龙头企业列表
	 * @param searchModel    查询条件
	 * @return
	 */
	@RequestMapping(value="getGardenBusinessList.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getGardenBusinessList(SearchModel searchModel){
		if(null==searchModel || null==searchModel.getPark()){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			JSONArray array = gardenService.getGardenBusinessList(searchModel);
			return success(changeObject(searchModel, array));
		} catch (Exception e) {
			LOGGER.error("getGardenBusinessList查询失败！"+e.getMessage());
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	/**
	 * 获取园区列表
	 * @param dto 传用户id
	 * @return
	 */
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
	/**
	 * 获取园区动态
	 * @param dto 
	 * @return
	 */
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
	
	private JSONObject changeObject(SearchModel searchModel,JSONArray data){
		JSONObject object = new JSONObject();
		object.put("park", searchModel.getPark());
		object.put("list",data);
		object.put("totalSize", searchModel.getTotalSize());
		object.put("totalPage", searchModel.getTotalPage());
		object.put("pageNumber", searchModel.getPageNumber());
		return object;
	}
	
}
