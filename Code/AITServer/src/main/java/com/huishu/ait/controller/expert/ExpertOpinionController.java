package com.huishu.ait.controller.expert;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.Specialist;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.service.ExpertOpinion.ExpertOpinionService;
import com.huishu.ait.service.Specialist.SpecialistService;

/**
 * @author yxq
 *	查询专家观点
 */
@RestController
@RequestMapping(value = "expert")
public class ExpertOpinionController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(ExpertOpinionController.class);
	
	@Autowired
	private ExpertOpinionService expertOpinionService;
	@Autowired
	private SpecialistService specialistService;
	
	/**
	 * @param dto
	 * 初始化分页的方法
	 */
	private ExpertOpinionDTO initPage(ExpertOpinionDTO dto){
		if(dto.getPageNumber() == null){
			dto.setPageNumber(1);
		}
		if(dto.getPageSize() == null){
			dto.setPageSize(ConcersUtils.PAGE_SIZE);
		}
		if(dto.getPageNumber() > ConcersUtils.ES_MAX_PAGENUMBER){
			dto.setPageNumber(ConcersUtils.ES_MAX_PAGENUMBER);
		}
		return dto;
	}
	/**
	 * @return
	 * 获取专家信息列表
	 */
	@RequestMapping(value = "getSpecialist.json",method=RequestMethod.GET)
	public AjaxResult getSpecialist(){
		try {
			List<Specialist> findAll = specialistService.findAll();
			return success(findAll);
		} catch (Exception e) {
			log.error("查询失败：",e.getMessage());
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	/**
	 * @param requestParam
	 * @return
	 * 查询专家观点
	 */
	@RequestMapping(value = "findaExpertOpinion.json",method=RequestMethod.POST)
	public AjaxResult getExpertOpinion(ExpertOpinionDTO requestParam){
		try {
			requestParam = initPage(requestParam);
			JSONArray jsonArray = expertOpinionService.getExertOpinionList(requestParam);
			return this.success(jsonArray);
		} catch (Exception e) {
			log.error("查询失败：",e.getMessage());
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	/**
	 * @param requestParam
	 * @return
	 * 根据作者名称查询观点列表
	 */
	@RequestMapping(value = "findExpertOpinionByAuthor.json",method=RequestMethod.POST)
	public AjaxResult getExpertOpinionByAuthor(ExpertOpinionDTO requestParam){
		try {
			requestParam = initPage(requestParam);
			JSONArray jsonArray = expertOpinionService.findExpertOpinionByAuthor(requestParam);
			return this.success(jsonArray);
		} catch (Exception e) {
			log.error("查询失败：",e.getMessage());
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	/**
	 * @param id
	 * @return
	 * 根据ID查询专家观点详情
	 */
	@RequestMapping(value = "findExpertOpinionById.json",method=RequestMethod.GET)
	public AjaxResult getExpertOpinionById(String id){
		try {
			JSONObject json = expertOpinionService.findExpertOpinionById(id);
			return this.success(json);
		} catch (Exception e) {
			log.error("查询失败：",e.getMessage());
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	/**
	 * @param requestParam
	 * @return
	 * 收藏专家观点
	 */
	@RequestMapping(value = "collectExpertOpinion.json",method=RequestMethod.GET)
	public AjaxResult collectExpertOpinion(String id){
		try {
			JSONObject json = expertOpinionService.expertOpinionCollect(id);
			return success(json);
		} catch (Exception e) {
			log.error("收藏失败：",e.getMessage());
			return null;
		}
	}
	/**
	 * @param requestParam
	 * @return
	 * 取消收藏专家观点
	 */
	@RequestMapping(value = "cancelCollectExpertOpinion.json",method=RequestMethod.GET)
	public AjaxResult cancelCollectExpertOpinion(String id){
		try {
			JSONObject json = expertOpinionService.cancelExpertOpinionCollect(id);
			return success(json);
		} catch (Exception e) {
			log.error("取消收藏失败：",e.getMessage());
			return null;
		}
	}
	
}
