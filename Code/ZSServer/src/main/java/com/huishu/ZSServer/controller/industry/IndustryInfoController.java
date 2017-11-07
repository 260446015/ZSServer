package com.huishu.ZSServer.controller.industry;

import java.util.List;

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
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.service.company.CompanyService;
import com.huishu.ZSServer.service.indus.IndustryInfoService;

/**
 * @author hhy
 * @date 2017年10月31日
 * @Parem
 * @return 
 * 产业动态相关接口
 */
@Controller
@RequestMapping("/apis/indus")
public class IndustryInfoController extends BaseController{
	private Logger LOGGER = LoggerFactory.getLogger(IndustryInfoController.class);
	
	@Autowired
	private IndustryInfoService service;
	@Autowired
	private CompanyService  cservice;
	/**
	 *产业资讯，关键词云
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findKeyWord.json",method=RequestMethod.POST)
	public AjaxResult  findKeyWord(@RequestBody String time){
		if(StringUtil.isEmpty(time)){
			LOGGER.debug("产业资讯查询关键词云传参异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject obj = new JSONObject();
		initTime(obj, time);
		obj.put("dimension","产业头条");
		JSONArray jsonArray = service.getKeyWordList(obj);
		return success(jsonArray);
	}
	
	/**
	 *产业资讯，根据关键词查询文章的内容
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findArticleListByKeyWord.json",method=RequestMethod.POST)
	public AjaxResult findArticleListByKeyWord(@RequestBody String[] msg){
		if(msg.length==0){
			LOGGER.debug("产业资讯根据关键词查询文章传参异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		String time = msg[0];
		String keyWord  = msg[1];
		JSONObject obj = new JSONObject();
		initTime(obj, time);
		obj.put("keyWord", keyWord);
		obj.put("dimension","产业头条");	
		JSONArray json = service.getArticleListByKeyWord(obj);
		return success(json);
	}
	
	
	
	/**
	 * 产业资讯-获取产业资讯的内容
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findIndustryInfoArticleList.json",method=RequestMethod.POST)
	public AjaxResult  findIndustryInfoArticleList(@RequestBody String[] msg){
		if(msg.length==0){
			LOGGER.debug("产业资讯查询关键词云传参异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		String  industry = msg[0];
		String industryLabel = msg[1];
		String area = msg[2];
		String type = msg[3];
		JSONObject json = new JSONObject();
		json.put("industry", industry);
		json.put("industryLabel", industryLabel);
		json.put("area", area);
		json.put("type", type);
		Page<AITInfo> page = service.getIndustryInfoByPage(json);
		return success(page);
	}
	
	/**
	 * 获取 科研成果 的 数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findResearchResultList.json",method = RequestMethod.GET)
	public  AjaxResult findResearchResultList(){
		JSONObject json = new JSONObject();
		json.put("dimension", "科学研究");
		Page<AITInfo> array =  service.findResearchResultList(json);
		return success(array);
	}
	
	/**
	 * 根据地区显示公司名称 
	 */
	@RequestMapping(value="/getBusinessListByArea.json",method=RequestMethod.GET)
	public AjaxResult getBusinessListByArea(JSONArray json ){
		JSONObject obj = json.getJSONObject(0);
		String area = obj.getString("area");
		String industry = obj.getString("industry");
		List<String> list = cservice.findCompanyName(area ,industry);
		return success(list);
	}
	
}
