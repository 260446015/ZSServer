package com.huishu.ait.controller.headlines;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.dto.ArticleListDTO;
import com.huishu.ait.service.ExpertOpinion.ExpertOpinionService;
import com.huishu.ait.service.company.CompanyService;
import com.huishu.ait.service.headline.HeadlinesService;
import com.huishu.ait.service.industrialPolicy.IndustrialPolicyService;
import com.merchantKey.itemModel.KeywordModel;

/**
 * @author hhy
 * @date 2017年9月26日
 * @Parem
 * @return 
 * 产业的相关接口
 */
@Controller
@RequestMapping("/head")
public class HeadlinesController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(HeadlinesController.class);
	
	@Autowired
	private IndustrialPolicyService service;
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private HeadlinesService headService;
	
	@Autowired
	private  ExpertOpinionService  expertService;
	
	/**
	 * 查询产业政策的相关内容(产业政策，高峰论坛，科学研究)
	 * @return
	 */
	@RequestMapping(value="/getInfo.json",method=RequestMethod.GET)
	public AjaxResult getInfo(String params){
		if(StringUtil.isEmpty(params)){
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject param = StringUtil.paramToJson(params);
		Page<ArticleListDTO> list =service.findArticleList(param);
		return success(list);
	}
	
	/**
	 * 查询企业排行的全部内容
	 * @param params
	 * @return
	 */
	
	@RequestMapping("/getCompanyInfo.json")
	public AjaxResult getCompanyInfo(String params){
		if(StringUtil.isEmpty(params)){
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject param = StringUtil.paramToJson(params);
		Page<ArticleListDTO> list =companyService.findArticleList(param);
		return success(list);
	}
	/**
	 * 查询百家论和专家论的全部内容
	 * @param params
	 * @return
	 */
	@RequestMapping("/getExpertOpinion.json")
	public AjaxResult  getExpertOpinion(String params){
		if(StringUtil.isEmpty(params)){
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject param = StringUtil.paramToJson(params);
		Page<ArticleListDTO> list = expertService.findExpertOpinionArticleList(param);
		return success(list);
	}
	
	/**
	 * 查询产业头条的内容(根据类型，关键词查询文章内容)
	 * @return
	 */
	@RequestMapping(value="/getHeadlineInfo.json",method=RequestMethod.POST)
	public AjaxResult getHeadlineInfo(String params){
		if(StringUtil.isEmpty(params)){
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject param = StringUtil.paramToJson(params);
		 Page<ArticleListDTO> list = headService.findArticleList(param);
		if(list!= null){
			return success(list);
		}else{
			return error("获取产业头条关键词失败！");
		}
	}
	/**
	 * 根据筛选条件，获得关键词
	 * @param params
	 * @return
	 */
	@RequestMapping("/getHeadlineKeyWord.json")
	public AjaxResult getHeadlineKeyWord(String params){
		if(StringUtil.isEmpty(params)){
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject param = StringUtil.paramToJson(params);
		List<KeywordModel> list = headService.findArticleKeyword(param);
		if(list!= null){
			return success(list);
		}else{
			return error("获取产业头条关键词失败！");
		}
	}
}
