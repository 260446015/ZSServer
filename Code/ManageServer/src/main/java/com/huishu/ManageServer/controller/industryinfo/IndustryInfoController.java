package com.huishu.ManageServer.controller.industryinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.service.industry.info.IndustryInfoService;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.entity.dto.IndustryInfoDTO;
import com.huishu.ManageServer.es.entity.AITInfo;
import com.huishu.ManageServer.es.entity.SummitInfo;

/**
 * @author hhy
 * @date 2018年1月15日
 * @Parem
 * @return 
 * 产业动态
 */
@Controller
@RequestMapping("/apis/industryinfo")
public class IndustryInfoController extends BaseController {
	private static final Logger LOGGER = Logger.getLogger(IndustryInfoController.class);
	
	@Autowired
	private IndustryInfoService service;
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "{page}" }, method = RequestMethod.GET)
	public String findAccurateCompany(@PathVariable String page) {
		return "/industry/info/" + page;
	}
	@RequestMapping(value = "/editIndustryinfo.json", method = RequestMethod.GET)
	public String editIndustryinfo(String id,Model model){
		if(StringUtil.isEmpty(id)){
			AITInfo info = service.findIndustryInfoById(id);
			model.addAttribute("info", info);
		}
		return "/industry/info/editIndustryInfo.html";
	}
	/**
	 * 获取科研成果的数据
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/findAITInfo.json", method = RequestMethod.GET)
	public AjaxResult findAITInfo(){
		JSONObject json = new JSONObject();
		json.put("dimension", "科学研究");
	    addData(json);
	    List<AITInfo> list = service.findResearchList(json);
		return success(list);
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
		json.put("dimension", "产业头条");
		json.put("pageSize", dto.getPageSize());
		json.put("pageNumber", dto.getPageNum());
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
	
	
	private void addData(JSONObject obj1) {
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
		obj1.put("time", time);
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
		JSONArray json = service.findArticleInfo(time,keyWord);
		return success(json);
	}
	
	/**
	 * 产业资讯，删除产业资讯的内容
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteIndustryInfoById.json",method=RequestMethod.GET,params="id")
	public AjaxResult deleteIndustryInfoById(String id){
		if(StringUtil.isEmpty(id)){
			LOGGER.debug("产业资讯删除文章传参异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		boolean info = service.deleteArticleInfoById(id);
		if(info){
			return success("删除成功！");
		}else{
			return error("删除失败！");
		}
	}
	/**
	 * 删除研究成果的数据
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteInfoById.json",method=RequestMethod.GET,params="id")
	public AjaxResult deleteInfoById(String id){
		if(StringUtil.isEmpty(id)){
			LOGGER.debug("研究成果删除文章传参异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		boolean info = service.deleteInfoById(id);
		if(info){
			return success("删除成功！");
		}else{
			return error("删除失败！");
		}
	}
	
	/**
	 * 保存或者修改产业资讯数据
	 * @param enter
	 * @return
	 */
	@RequestMapping(value = "/saveIndustryinfo.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveIndustryinfo(@RequestBody AITInfo enter){
		try {
			
			boolean flag = service.saveIndudustryInfo(enter);
			if(flag){
				return success("保存成功！");
			}else{
				return error("操作失败！");
			}
			
		} catch (Exception e) {
			LOGGER.debug("保存或者修改峰会信息失败！",e);
			return error("操作失败！");
		}
	}
}
