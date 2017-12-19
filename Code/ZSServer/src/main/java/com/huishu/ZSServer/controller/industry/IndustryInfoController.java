package com.huishu.ZSServer.controller.industry;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.DateUtils;
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
	@RequestMapping(value="/{page}",method=RequestMethod.GET)
	public String getIndustryInfo(Map<String,Object> map,@PathVariable String page){
		JSONObject json = new JSONObject();
		json.put("dimension", "科学研究");
		json = StringUtil.getIndustry(json);
		Page<AITInfo> page1 = service.findResearchResultList(json);
		
		map.put("content", page1.getContent());
		return "/industry/"+page;
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
		DateUtils.initTime(obj1, time);
		addData(obj1);
		JSONArray jsonArray = service.getKeyWordList(obj1);
		return success(jsonArray);
	}


	private void addData(JSONObject obj1) {
		obj1.put("dimension","产业头条");
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("value", "大数据");
		arr.add(obj);
		obj = new JSONObject();
		obj.put("value", "人工智能");
		arr.add(obj);
		obj = new JSONObject();
		obj.put("value", "物联网");
		arr.add(obj);
		obj = new JSONObject();
		obj.put("value", "生物医药");
		arr.add(obj);
		obj1.put("industryLabel", arr);
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
		
		JSONObject obj1 = new JSONObject();
		DateUtils.initTime(obj1, time);
		obj1.put("keyWord", keyWord);
		String info = StringUtil.getIndustryInfo(keyWord);
		if(StringUtil.isNotEmpty(info)){
			obj1.put("dimension","产业头条");
			JSONArray arr = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("value", info);
			arr.add(obj);
			obj1.put("industryLabel", arr);
		}else{
			addData(obj1);
		}
		JSONArray json = service.getArticleListByKeyWord(obj1);
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
			obj.put("value", "生物医药");
			array.add(obj);
		}else{
			if(industry.equals("生物技术")){
				
				obj.put("value", "生物医药");
			}else{
				
				obj.put("value", industry);
			}
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
			if(action.getIndustryLabel().equals("生物医药")){
				action.setIndustryLabel("生物技术");
			}
				String content = action.getContent();
				if(content.length()>=300){
					String substring = content.substring(0, 300);
					action.setContent( StringUtil.replaceHtml(substring));
				}else if(content.length()>=150){
					String substring = content.substring(0, 150);
					action.setContent( StringUtil.replaceHtml(substring));
				}else{
					String substring = content.substring(0, 50);
					action.setContent( StringUtil.replaceHtml(substring));
				}
		
		});
		JSONObject result = new JSONObject();
		result.put("dataList", page.getContent());
		if(page.getTotalElements()>=6000){
			result.put("totalNumber",6000);
		}else{
			result.put("totalNumber",page.getTotalElements());
		}
		if(page.getTotalPages()>=1000){
			result.put("totalPage",1000);
		}else{
			result.put("totalPage",page.getTotalPages());
		}
		result.put("pageNumber",page.getNumber()+1);
		return new AjaxResult().setData(result).setSuccess(true).setStatus(0);
	}
	
}
