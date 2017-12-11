package com.huishu.ZSServer.controller.industry;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
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
import com.huishu.ZSServer.entity.dto.IndustryInfoDTO;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.service.indus.IndustryInfoService;

/**
 * @author hhy
 * @date 2017年10月31日
 * @Parem
 * @return 
 * 产业动态相关接口
 */
@Controller
@RequestMapping("/indus")
public class IndustryInfoController extends BaseController{
	private Logger LOGGER = LoggerFactory.getLogger(IndustryInfoController.class);
	
	@Autowired
	private IndustryInfoService service;
	
	/**
	 * 产业动态页面跳转
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public String getIndustryInfo(Map<String,Object> map){
		JSONObject json = new JSONObject();
		json.put("dimension", "科学研究");
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("industryLabel", "人工智能");
		arr.add(obj);
		obj = new JSONObject();
		obj.put("industryLabel", "大数据");
		arr.add(obj);
		obj = new JSONObject();
		obj.put("industryLabel", "物联网");
		arr.add(obj);
		obj = new JSONObject();
		obj.put("industryLabel", "生物医药");
		arr.add(obj);
		json.put("industryLabel", arr);
		Page<AITInfo> page = service.findResearchResultList(json);
		
		map.put("content", page.getContent());
		return "/industry/industryDynamics";
	}
	
	
	/**
	 *产业资讯，关键词云
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findKeyWord.json",method=RequestMethod.POST)
	public AjaxResult  findKeyWord(@Param("time") String time){
		if(StringUtil.isEmpty(time)){
			LOGGER.debug("产业资讯查询关键词云传参异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject obj1 = new JSONObject();
		initTime(obj1, time);
		obj1.put("dimension","产业头条");
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("value", "人工智能");
		arr.add(obj);
		obj = new JSONObject();
		obj.put("value", "大数据");
		arr.add(obj);
		obj = new JSONObject();
		obj.put("value", "物联网");
		arr.add(obj);
		obj = new JSONObject();
		obj.put("value", "生物医药");
		arr.add(obj);
		obj1.put("industryLabel", arr);
		JSONArray jsonArray = service.getKeyWordList(obj1);
		return success(jsonArray);
	}
	
	/**
	 *产业资讯，根据关键词查询文章的内容
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findArticleListByKeyWord.json",method=RequestMethod.POST)
	public AjaxResult findArticleListByKeyWord(@Param("time") String time ,@Param("keyWord") String keyWord){
		if(StringUtil.isEmpty(keyWord)||StringUtil.isEmpty(time)){
			LOGGER.debug("产业资讯根据关键词查询文章传参异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		
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
	public AjaxResult  findIndustryInfoArticleList(@RequestBody IndustryInfoDTO dto){
		if(dto==null){
			LOGGER.debug("产业资讯查询关键词云传参异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		String  industry = dto.getIndustry();
		String area = dto.getArea();
		String type = dto.getSort();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		if(industry.equals("全部")){
			obj.put("value", "人工智能");
			array.add(obj);
			obj =new JSONObject();
			obj.put("value", "大数据");
			array.add(obj);
			obj =new JSONObject();
			obj.put("value", "物联网");
			array.add(obj);
			obj =new JSONObject();
			obj.put("value", "生物技术");
			array.add(obj);
		}else{
			obj.put("value", industry);
			array.add(obj);
		}
		json.put("industry", array);
		if(area.equals("全部")){
			area="";
		}
		json.put("area", area);
		json.put("type", type);
		json.put("pageSize", dto.getPageSize());
		json.put("pageNumber", dto.getPageNumber());
		Page<AITInfo> page = service.getIndustryInfoByPage(json);
		page.getContent().forEach(action->{
			String content = action.getContent();
			if(content.length()>150){
				action.setContent(content.substring(0, 150));
			}
		});
		return successPage(page,page.getNumber()+1);
	}
	
}
