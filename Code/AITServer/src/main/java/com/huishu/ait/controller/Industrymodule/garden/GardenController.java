package com.huishu.ait.controller.Industrymodule.garden;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.GardenUser;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.service.garden.GardenService;


/**
 * 全景辖区概览
 * @author yindq
 * @date   2017-7-28
 */
@Controller
@RequestMapping("/apis/area")
public class GardenController extends BaseController{
	private static Logger LOGGER = LoggerFactory.getLogger(GardenController.class);
			
	
	@Autowired
	private GardenService gardenService;

	/**
	 * 辖区政策列表
	 * @param searchModel    查询条件
	 * @return
	 */
	@RequestMapping(value="getGardenPolicyList.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getGardenPolicyList(@RequestBody AreaSearchDTO searchModel){
		if(null==searchModel){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		searchModel.setPark(getUserPark());
		try {
			JSONArray array = gardenService.getGardenPolicyList(searchModel);
			return success(changeObject(searchModel, array));
		} catch (Exception e) {
			LOGGER.error("getGardenPolicyList查询失败！",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 辖区政策详情
	 * @param id   政策ID
	 * @return
	 */
	@RequestMapping(value="getGardenPolicyById.json",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGardenPolicyById(String id){
		if(StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(gardenService.getGardenPolicyById(id));
		} catch (Exception e) {
			LOGGER.error("getGardenPolicyById查询失败！",e);
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
	public AjaxResult getGardenInformationList(@RequestBody AreaSearchDTO searchModel){
		if(null==searchModel || StringUtil.isEmpty(getUserPark())){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		searchModel.setPark(getUserPark());
		try {
			JSONArray array = gardenService.getGardenInformationList(searchModel);
			return success(changeObject(searchModel, array));
		} catch (Exception e) {
			LOGGER.error("getGardenInformationList查询失败！",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 辖区情报详情
	 * @param id   动态ID
	 * @return
	 */
	@RequestMapping(value="getGardenInformationById.json",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGardenInformationById(String id){
		if(StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(gardenService.getGardenInformationById(id));
		} catch (Exception e) {
			LOGGER.error("getGardenInformationById查询失败！",e);
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
	public AjaxResult getGardenBusinessList(@RequestBody AreaSearchDTO searchModel){
		
		if(null==searchModel || StringUtil.isEmpty(getUserPark())){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		searchModel.setPark(getUserPark());
		try {
			JSONArray array = gardenService.getGardenBusinessList(searchModel);
			return success(changeObject(searchModel, array));
		} catch (Exception e) {
			LOGGER.error("getGardenBusinessList查询失败！",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 获取园区的龙头企业，园区政策和园区情报信息，全都只取前五条
	 * @param searchModel    查询条件
	 * @return
	 */
	@RequestMapping(value="getGardenTableData.json",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGardenTableData(String gardenName){
		Long userId = getUserId();
		if(StringUtil.isEmpty(gardenName)||null == userId){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(gardenService.getGardenTableData(gardenName,userId));
		} catch (Exception e) {
			LOGGER.error("getGardenTableData查询失败！",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 根据园区名字获取园区状态
	 * @param gardenName   园区名
	 * @return
	 */
	@RequestMapping(value="getGardenByName.json",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGardenByName(String gardenName){
		if(StringUtil.isEmpty(gardenName)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(gardenService.getGardenByName(gardenName));
		} catch (Exception e) {
			LOGGER.error("根据园区名字获取园区状态查询失败！",e);
			return error("根据园区名字获取园区状态失败");
		}
	}
	
	
	/**
	 * 获取园区列表
	 * @param dto 
	 * @return
	 */
	@RequestMapping(value="/findGardensList.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult findGardensList(@RequestBody GardenDTO dto){
		if(null == dto.getMsg() || dto.getMsg().length == 0){
			 return error(MsgConstant.ILLEGAL_PARAM);
		}
		dto = initPage(dto);
		JSONArray gardens = null;
		try{
			gardens = gardenService.findGardensList(dto);
		}catch(Exception e){
			LOGGER.error("查询园区列表失败!",e);
			return error(e.getMessage());
		}
		return success(gardens);
	}
	/**
	 * 获取园区动态
	 * @param dto 
	 * @return
	 */
	@RequestMapping(value="/findGardensCondition.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult findGardensCondition(@RequestBody GardenDTO dto){
		dto = initPage(dto);
		Long userId = getUserId();
		JSONArray aITInfos = null;
		dto.setUserId(userId.intValue());
		try{
			aITInfos = gardenService.findGardensCondition(dto);
		}catch(Exception e){
			LOGGER.error("查询园区动态失败!", e);
			return error(e.getMessage());
		}
		return success(aITInfos);
	}
	/**
	 * 获取园区动态详情信息
	 */
	@RequestMapping(value="/findGardensConditionById",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult findGardensConditionById(String cid) {
		if(StringUtil.isEmpty(cid)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject obj = null;
		try {
			obj = gardenService.findGardensConditionById(cid);
		}catch(Exception e) {
			LOGGER.error("根据id查询园区动态失败!", e);
			return error(e.getMessage());
		}
		return success(obj);
	}
	
	/**
	 * 获取关注园区列表
	 * @param dto [id,area,industryType]
	 * @return
	 */
	@RequestMapping(value = "/getAttentionGardenList.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getAttentionGardenList(@RequestBody GardenDTO dto){
	    String msg[] = dto.getMsg();
	    Long userId = getUserId();
	    dto.setUserId(userId.intValue());
	    dto.setArea(msg[0]);
	    dto.setIndustryType(msg[1]);
	    
	    if(null == dto || userId == null){
	        return error(MsgConstant.ILLEGAL_PARAM);
	    }
	    try{
	        dto=initPage(dto);
	        Page<GardenUser> pagedata = null;
	        pagedata = gardenService.getAttentionGardenList(dto);
	        return success(pagedata);
	    }catch(Exception e){
	        LOGGER.error("查询园区动态失败!", e);
            return error(e.getMessage());
	    }
	}
	
	
	/**
	 * 关注/取消关注园区
	 * @param gardenId   园区id
	 * @param flag  true关注，false取消关注
	 * @return
	 */
	@RequestMapping(value="/attentionGarden.json",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult attentionGarden(String gardenId,Boolean flag){
		GardenUser gardenUser = null;
		if(StringUtil.isEmpty(gardenId) || null == flag){
			error(MsgConstant.ILLEGAL_PARAM);
		}
		Long userId = getUserId();
		try{
			if(flag)
				gardenUser = gardenService.attentionGarden(gardenId,userId.toString(),true);
			else
				gardenUser = gardenService.attentionGarden(gardenId, userId.toString(),false);
		}catch(Exception e){
			LOGGER.error("关注园区失败", e);
			return error("关注园区失败");
		}
		return success(gardenUser);
		
	}
	
	/**
	 * 按地区查询园区
	 * @param area
	 * @return
	 */
	@RequestMapping(value="/findGardensByArea.json",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult findGardensByArea(String area){
		JSONArray data = null;
		try{
			data = gardenService.findGardensByArea(area);
		}catch(Exception e){
			LOGGER.error("按地域查询多个园区失败", e);
			error("按地域查询多个园区失败");
		}
		return success(data);
	}
	
	
	private GardenDTO initPage(GardenDTO dto){
		if(dto.getPageNumber() == null){
			dto.setPageNumber(ConcersUtils.ES_MIN_PAGENUMBER);
		}
		if(dto.getPageSize() == null){
			dto.setPageSize(ConcersUtils.PAGE_SIZE);
		}
		if(dto.getPageNumber() > ConcersUtils.ES_MAX_PAGENUMBER){
			dto.setPageNumber(ConcersUtils.ES_MAX_PAGENUMBER);
		}
		return dto;
	}
}
