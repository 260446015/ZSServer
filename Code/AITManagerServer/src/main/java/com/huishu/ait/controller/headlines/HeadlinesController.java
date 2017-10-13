package com.huishu.ait.controller.headlines;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
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
	private HeadlinesService headService;
	
	@Autowired
	private  ExpertOpinionService  expertService;
	
	
	/**
	 * 查询百家论和专家论的全部内容
	 * @param params
	 * @return
	 */
	@RequestMapping("/getExpertOpinion.json")
	@ResponseBody
	public AjaxResult  getExpertOpinion(@RequestBody String params){
		if(StringUtil.isEmpty(params)){
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject param = JSONObject.parseObject(params);
		Page<AITInfo> list = expertService.findExpertOpinionArticleList(param);
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
	@ResponseBody
	public AjaxResult getHeadlineKeyWord(@RequestBody String params){
		if(StringUtil.isEmpty(params)){
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject param = JSONObject.parseObject(params);
		List<KeywordModel> list = headService.findArticleKeyword(param);
		if(list!= null){
			return success(list);
		}else{
			return error("获取产业头条关键词失败！");
		}
	}
	
	@RequestMapping(value = {"{page}"},method = RequestMethod.GET)
	public String pageHeadlines(@PathVariable String page){
		return "industry/"+page;
	}
	/**
	 * 前台传递产业类型，后台返回产业标签
	 * @param industry
	 * @return
	 */
	@RequestMapping(value="/getLabel.json",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject getLabel(String industry){
		JSONObject data = new JSONObject();
		String[] label;
		industry = industry.trim();
		if("互联网".equals(industry)){
			label = new String[]{"网络游戏","大数据","电子商务","网络视听","移动阅读","智能硬件"};
			industry = "互联网+";
		}else if("高科技".equals(industry)){
			label = new String[]{"新一代信息技术","智能机器人","生物医药","节能环保技术装备","新能源","新材料","航空装备"};
		}else if("文化创意".equals(industry)){
			label = new String[]{"动漫制作","影视传媒","图书出版","广告营销"};
		}else if("精英配套".equals(industry)){
			label = new String[]{"精英服务","住宅地产","商业综合体","康体美容","健康产业","教育培训"};
		}else if("其他".equals(industry)){
			label = new String[]{"特色旅游综合体","体育产业"};
		}else{
			label = new String[]{"生鲜贸易","食品加工","冷链物流"};
		}
		data.put("industry", industry);
		data.put("label", label);
		return data;
	}
	@RequestMapping(value="/getDetail.json",method=RequestMethod.GET)
	public ModelAndView getDetail(String id,ModelAndView model){
		AITInfo a = expertService.findDetail(id);
		model.addObject("detail", a);
		model.setViewName("industry/detail");
		return model;
	}
}
