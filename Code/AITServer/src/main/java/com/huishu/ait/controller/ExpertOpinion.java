package com.huishu.ait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.service.ExpertOpinion.ExpertOpinionService;

/**
 * @author yxq
 *	查询专家观点
 */
@RestController
public class ExpertOpinion extends BaseController{
	
	@Autowired
	private ExpertOpinionService expertOpinionService;
	
	@RequestMapping(value = "findaExpertOpinion.do")
	public AjaxResult getExpertOpinion(ExpertOpinionDTO requestParam){
		JSONArray json = expertOpinionService.getExertOpinionList(requestParam);
		return this.success(json);
		
	}
	@RequestMapping(value = "/findExpertOpinionByAuthor.do")
	public AjaxResult getExpertOpinionByAuthor(ExpertOpinionDTO requestParam){
		JSONArray json = expertOpinionService.findExpertOpinionByAuthor(requestParam);
		return this.success(json);
	}
	
	@RequestMapping(value = "/findExpertOpinionById.do")
	public AjaxResult getExpertOpinionById(String id){
		AITInfo aitinfo = expertOpinionService.findExpertOpinionById(id);
		return this.success(aitinfo);
	}
	
	
}
