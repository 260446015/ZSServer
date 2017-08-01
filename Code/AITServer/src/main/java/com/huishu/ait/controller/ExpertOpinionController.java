package com.huishu.ait.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.service.ExpertOpinion.ExpertOpinionService;

/**
 * @author yxq
 *	查询专家观点
 */
@RestController
@RequestMapping(value = "/ExpertOpinion")
public class ExpertOpinionController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(ExpertOpinionController.class);
	
	@Autowired
	private ExpertOpinionService expertOpinionService;
	
	private ExpertOpinionDTO initPage(ExpertOpinionDTO dto){
		if(null == dto.getPageNumber()){
			dto.setPageNumber(ConcersUtils.ES_MIN_PAGENUMBER);
		}
		if(null == dto.getPageSize()){
			dto.setPageSize(ConcersUtils.PAGE_SIZE);
		}
		if(dto.getPageNumber() > ConcersUtils.ES_MAX_PAGENUMBER){
			dto.setPageNumber(ConcersUtils.ES_MAX_PAGENUMBER);
		}
		return dto;
	}
	@RequestMapping(value = "findaExpertOpinion.json",method= RequestMethod.POST)
	public AjaxResult getExpertOpinion(ExpertOpinionDTO requestParam){
		try {
			requestParam = initPage(requestParam);
			JSONArray jsonArray = expertOpinionService.getExertOpinionList(requestParam);
			return this.success(jsonArray);
		} catch (Exception e) {
			log.error("查询失败：",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	@RequestMapping(value = "/findExpertOpinionByAuthor.json",method=RequestMethod.POST)
	public AjaxResult getExpertOpinionByAuthor(ExpertOpinionDTO requestParam){
		try {
			requestParam = initPage(requestParam);
			JSONArray jsonArray = expertOpinionService.findExpertOpinionByAuthor(requestParam);
			return this.success(jsonArray);
		} catch (Exception e) {
			log.error("查询失败：",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	@RequestMapping(value = "/findExpertOpinionById.json", method=RequestMethod.POST)
	public AjaxResult getExpertOpinionById(String id){
		try {
			JSONObject json = expertOpinionService.findExpertOpinionById(id);
			return this.success(json);
		} catch (Exception e) {
			log.error("查询失败：",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	@RequestMapping(value = "collectExpertOpinion.json", method= RequestMethod.POST)
	public AjaxResult collectExpertOpinion(@RequestBody ExpertOpinionDTO requestParam){
		try {
			Boolean flag = expertOpinionService.expertOpinionCollect(requestParam);
			AjaxResult result = new AjaxResult();
			result.setSuccess(flag);
			return result;
		} catch (Exception e) {
			log.error("收藏失败：",e);
			return null;
		}
	}
	
}
