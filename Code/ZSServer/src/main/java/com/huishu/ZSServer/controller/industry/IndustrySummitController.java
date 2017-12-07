package com.huishu.ZSServer.controller.industry;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.dto.IndustrySummitDTO;
import com.huishu.ZSServer.es.entity.Essay;
import com.huishu.ZSServer.es.entity.SummitInfo;
import com.huishu.ZSServer.service.indus.IndusSummitService;

import net.minidev.json.JSONArray;

/**
 * @author hhy
 * @date 2017年10月27日
 * @Parem
 * @return
 * 产业峰会接口文档
 */
@Controller
@RequestMapping("/summit")
public class IndustrySummitController extends BaseController{
	private Logger LOGGER = LoggerFactory.getLogger(IndustrySummitController.class);
	
	@Autowired
	private IndusSummitService service ;
	/**
	 * 产业峰会页面跳转
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getInfo",method=RequestMethod.GET)
	public String getIndustrySummitInfo(Map<String,Object> map){
		return "/industry/industrySummitMeeting";
	}
	
	/**
	 * 文章详情页面跳转
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getEssayDetails.json",method=RequestMethod.GET)
	public String getEssayDetails(String essayId,Model model){
		if(!StringUtil.isEmpty(essayId)){
			model.addAttribute("essayId",essayId);
		}
		return "/essay/essayDetails";
	}
	
	
	/**
	 * 查看峰会列表
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public AjaxResult ListIndustry( @RequestBody IndustrySummitDTO dto) {
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
			page.getContent().forEach(action->{
				String[] split = action.getAddress().split("\n");
				if(split.length!=0){
					action.setAddress(split[0]);
				}
			});;
			JSONObject obj1 = new JSONObject();
			obj1.put("content", page.getContent());
			obj1.put("number", page.getNumber());
			obj1.put("size", page.getSize());
			obj1.put("totalPages", page.getTotalPages());
			return success(obj1);
		} catch (Exception e) {
			LOGGER.error("获取产业峰会列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 根据id查看文章详情
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public AjaxResult getSummitInfoById(String id){
		if(StringUtil.isEmpty(id)){
			LOGGER.debug("差看文章详情异常:"+id);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Essay info = service.getSummitInfoById(id);
		return success(info);
	}
	
	/**
	 * 关注 峰会
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public AjaxResult insertSummitInfoById(@Param("aid") String aid){
		if(StringUtil.isEmpty(aid)){
			LOGGER.debug("关注 峰会 详情 异常:"+aid);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Long uid = (long) 1;
		String a = service.saveSummitInfoById(aid,uid);
		return success(a);
	}
	
	/**
	 * 取消 关注 峰会
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public AjaxResult deleteSummitInfoById(@RequestBody Long  id){
		if(id==null||id==0){
			LOGGER.debug("删除峰会详情异常:"+id);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		boolean info = service.deleteSummitInfoById(id);
		return success(info);
	}
	
	
}
