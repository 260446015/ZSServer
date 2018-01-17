package com.huishu.ManageServer.controller.industryinfo;

import java.util.List;

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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dto.IndustrySummitDTO;
import com.huishu.ManageServer.service.industry.summit.IndustrySummitService;
import com.huishu.ManageServer.es.entity.SummitInfo;

import net.minidev.json.JSONArray;

/**
 * @author hhy
 * @date 2018年1月15日
 * @Parem
 * @return 
 * 产业峰会的后台管理
 */
@Controller
@RequestMapping("/apis/industrysummit")
public class IndustrySummitController extends BaseController{
	private static final Logger LOGGER = Logger.getLogger(IndustrySummitController.class);
	
	@Autowired
	private IndustrySummitService service;
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "{page}" }, method = RequestMethod.GET)
	public String findAccurateCompany(@PathVariable String page,String id,Model model) {
		if("editIndustrySummit".equals(page)){
			if(StringUtil.isNotEmpty(id)){
				SummitInfo info = 	service.findSummitInfoById(id);
				model.addAttribute("info", info);
			}
		}
		return "/industry/summit/" + page;
	}
	
	/**
	 * 产业峰会列表展示
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findIndustrySummitInfo.json", method = RequestMethod.POST)
	public AjaxResult findIndustrySummitInfo(@RequestBody IndustrySummitDTO dto){
		if(dto.getIndustry()== null||dto.getSort()== null||dto.getArea()== null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject json = new JSONObject(); 
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		if(dto.getIndustry().equals("全部")){
			obj.put("value", "人工智能");
			array.add(obj);
			obj = new JSONObject();
			obj.put("value", "大数据");
			array.add(obj);
			obj = new JSONObject();
			obj.put("value", "物联网");
			array.add(obj);
			obj = new JSONObject();
			obj.put("indus", "生物产业");
			array.add(obj);
		}else{
			if(dto.getIndustry().equals("生物技术")){
				obj.put("indus", "生物产业");
			}else{
				obj.put("value", dto.getIndustry());
			}
			array.add(obj);
		}
		json.put("industry", array);
		if(dto.getArea().equals("全部")){
			json.put("area", "");
		}else{
			json.put("area", dto.getArea());
		}
		json.put("type", dto.getSort());
		json.put("size", dto.getPageSize());
		json.put("number", dto.getPageNumber());
		try {
			Page<SummitInfo> page = service.getIndustryList(json);
			return successPage(page, page.getNumber()+1);
		} catch (Exception e) {
			LOGGER.error("获取产业峰会列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 根据产业获取地域信息
	 * @param industry
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getLabel.json", method = RequestMethod.GET,params="industry")
	public AjaxResult getLabel(String industry){
		if(StringUtil.isEmpty(industry)){
			LOGGER.debug("根据产业信息获取地域标签传参异常："+industry);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		List<String> list = service.getAreaLabel(industry);
		if(list.size()==0){
			return error(MsgConstant.SYSTEM_ERROR);
		}else{
			return success(list);
		}
	}
	
	/**
	 * 删除企业
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteIndustrySummit.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult delIndusCompany(String id) {
		if(id==null|| StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = service.deleteSummitInfoById(id);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("删除企业失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	/**
	 * 保存或者修改峰会数据
	 * @param enter
	 * @return
	 */
	@RequestMapping(value = "/saveIndustrysummit.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveIndustrysummit(@RequestBody SummitInfo enter){
		try {
			boolean flag = service.saveIndudustrySummitInfo(enter);
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
