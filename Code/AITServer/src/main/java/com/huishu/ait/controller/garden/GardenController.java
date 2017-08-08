package com.huishu.ait.controller.garden;

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
@RequestMapping("area")
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
		if(null==searchModel || null==searchModel.getPark()){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
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
		if(null==id){
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
		if(null==searchModel || null==searchModel.getPark()){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
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
		if(null==id){
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
		if(null==searchModel || null==searchModel.getPark()){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			JSONArray array = gardenService.getGardenBusinessList(searchModel);
			return success(changeObject(searchModel, array));
		} catch (Exception e) {
			LOGGER.error("getGardenBusinessList查询失败！",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	/**
	 * 获取园区列表
	 * @param dto 传用户id
	 * @return
	 */
	@RequestMapping(value="/findGardensList.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult findGardensList(@RequestBody GardenDTO dto){
		if(null == dto){
			 return error(MsgConstant.ILLEGAL_PARAM);
		}
		dto = initPage(dto);
		JSONArray gardens = null;
		try{
			gardens = gardenService.findGardensList(dto);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error("查询园区列表失败!",e);
			return error(e.getMessage()).setSuccess(false);
		}
		return success(gardens).setSuccess(true);
	}
	/**
	 * 获取园区动态
	 * @param dto 
	 * @return
	 */
	@RequestMapping(value="/findGardensCondition.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult findGardensCondition(@RequestBody GardenDTO dto){
		if(null == dto){
			if(StringUtil.isEmpty(String.valueOf(dto.getUserId())))
				return error(MsgConstant.ILLEGAL_PARAM);
		}
		dto = initPage(dto);
		JSONArray aITInfos = null;
		try{
			aITInfos = gardenService.findGardensCondition(dto);
		}catch(Exception e){
			LOGGER.error("查询园区动态失败!", e);
			return error(e.getMessage()).setSuccess(false);
		}
		return success(aITInfos).setSuccess(true);
	}
	
	/**
	 * 获取关注园区列表
	 * @param dto [id,area,industryType]
	 * @return
	 */
	@RequestMapping(value = "/getAttentionGardenList.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getAttentionGardenList(GardenDTO dto){
	    String msg[] = dto.getMsg();
	    dto.setUserId(Integer.parseInt(msg[0]));
	    dto.setArea(msg[1]);
	    dto.setIndustryType(msg[2]);
	    
	    if(null == dto || StringUtil.isEmpty(String.valueOf(dto.getUserId()))){
	        return error(MsgConstant.ILLEGAL_PARAM);
	    }
	    try{
	        dto=initPage(dto);
	        Page<GardenUser> pagedata = null;
	        pagedata = gardenService.getAttentionGardenList(dto);
	        return success(pagedata);
	    }catch(Exception e){
	        LOGGER.error("查询园区动态失败!", e);
            return error(e.getMessage()).setSuccess(false);
	    }
	}
	
	@RequestMapping("/findGardensAll.json")
	@ResponseBody
	public AjaxResult findGardensAll(){
		JSONArray arr = null;
		try{
			arr = gardenService.findGardensAll();
		}catch(Exception e){
			LOGGER.error("查询园区情报中获取所有园区内容失败!", e);
		}
		return success(arr);
	}
	@RequestMapping(value="/attentionGarden.json",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult attentionGarden(String gardenId,String userId,boolean flag){
		GardenUser gardenUser = null;
		try{
			if(flag)
				gardenUser = gardenService.attentionGarden(gardenId,userId,true);
			else
				gardenUser = gardenService.attentionGarden(gardenId, userId,false);
		}catch(Exception e){
			LOGGER.error("关注园区失败", e);
			return error("关注园区失败");
		}
		return success(gardenUser);
		
	}
	
	
	private GardenDTO initPage(GardenDTO dto){
		if(dto.getPageNum() == null){
			dto.setPageNum(ConcersUtils.ES_MIN_PAGENUMBER);
		}
		if(dto.getPageSize() == null){
			dto.setPageSize(ConcersUtils.PAGE_SIZE);
		}
		if(dto.getPageNum() > ConcersUtils.ES_MAX_PAGENUMBER){
			dto.setPageNum(ConcersUtils.ES_MAX_PAGENUMBER);
		}
		return dto;
	}
}
