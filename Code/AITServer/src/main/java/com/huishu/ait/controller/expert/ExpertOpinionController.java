package com.huishu.ait.controller.expert;

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
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.service.ExpertOpinion.ExpertOpinionService;

/**
 * @author yxq
 *	查询专家观点
 */
@RestController
@RequestMapping(value = "expertOpinion")
public class ExpertOpinionController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(ExpertOpinionController.class);
	
	@Autowired
	private ExpertOpinionService expertOpinionService;
	
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
	 * @param requestParam
	 * @return
	 * 查询专家观点
	 */
	@RequestMapping(value = "findaExpertOpinion.json")
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
	@RequestMapping(value = "findExpertOpinionByAuthor.json")
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
	@RequestMapping(value = "findExpertOpinionById.json")
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
	@RequestMapping(value = "collectExpertOpinion.json")
	public AjaxResult collectExpertOpinion(@RequestBody String id){
		try {
			Boolean flag = expertOpinionService.expertOpinionCollect(id);
			AjaxResult result = new AjaxResult();
			result.setSuccess(flag);
			return result;
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
	@RequestMapping(value = "cancelCollectExpertOpinion.json")
	public AjaxResult cancelCollectExpertOpinion(@RequestBody String id){
		try {
			Boolean flag = expertOpinionService.cancelExpertOpinionCollect(id);
			AjaxResult result = new AjaxResult();
			result.setSuccess(flag);
			return result;
		} catch (Exception e) {
			log.error("取消收藏失败：",e.getMessage());
			return null;
		}
	}
	
}
