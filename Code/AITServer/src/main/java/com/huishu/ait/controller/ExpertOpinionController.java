package com.huishu.ait.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.headlines.HeadlinesController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.ExpertOpinionDTO;
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
	
	@RequestMapping(value = "findaExpertOpinion.do")
	public AjaxResult getExpertOpinion(ExpertOpinionDTO requestParam){
		try {
			JSONArray jsonArray = expertOpinionService.getExertOpinionList(requestParam);
			return this.success(jsonArray);
		} catch (Exception e) {
			log.error("查询失败：",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	@RequestMapping(value = "/findExpertOpinionByAuthor.do")
	public AjaxResult getExpertOpinionByAuthor(ExpertOpinionDTO requestParam){
		try {
			JSONArray jsonArray = expertOpinionService.findExpertOpinionByAuthor(requestParam);
			return this.success(jsonArray);
		} catch (Exception e) {
			log.error("查询失败：",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	@RequestMapping(value = "/findExpertOpinionById.do")
	public AjaxResult getExpertOpinionById(String id){
		try {
			JSONObject json = expertOpinionService.findExpertOpinionById(id);
			return this.success(json);
		} catch (Exception e) {
			log.error("查询失败：",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	
	@RequestMapping(value = "collectExpertOpinion.do")
	public AjaxResult collectExpertOpinion(ExpertOpinionDTO requestParam){
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
