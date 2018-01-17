package com.huishu.ManageServer.controller.industryinfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dto.AccurateDTO;
import com.huishu.ManageServer.service.industry.map.IndustryMapService;

/**
 * @author hhy
 * @date 2018年1月15日
 * @Parem
 * @return 
 * 产业地图数据维护
 */
@Controller
@RequestMapping("/apis/industrymap")
public class IndustryMapController extends BaseController{
	private static final Logger LOGGER = Logger.getLogger(IndustryMapController.class);
	
	@Autowired
	private IndustryMapService service;
	
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "{page}" }, method = RequestMethod.GET)
	public String findAccurateCompany(@PathVariable String page) {
		return "/industry/map/" + page;
	}
	
	/**
	 * 根据产业类型查看数据
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findIndustryMapInfo.json", method = RequestMethod.GET ,params = "industry")
	public AjaxResult findIndustryMapInfo( String industry) {
		if(StringUtil.isEmpty(industry)){
			LOGGER.debug("产业资讯查询关键词云传参异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject array = service.findMapInfoByIndustry(industry);
		return success(array);
	}
}
