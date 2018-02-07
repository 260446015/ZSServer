package com.huishu.ManageServer.controller.industryinfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbFirst.IndustryRank;
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
	@RequestMapping(value = "/findIndustryMapInfo.json", method = RequestMethod.GET ,params = "industry")
	public AjaxResult findIndustryMapInfo( String industry) {
		if(StringUtil.isEmpty(industry)){
			LOGGER.debug("产业资讯查询关键词云传参异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject array = service.findMapInfoByIndustry(industry);
		return success(array);
	}
	/**
	 * 针对产业热度数据更新或新增
	 * @param rank
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateRankInfo.json", method = RequestMethod.POST)
	public AjaxResult saveOrUpdateRankInfo( @RequestBody IndustryRank rank){
		if(rank.getArea()==null||rank.getCount()==null||rank.getIndustry()==null||rank.getId()==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		boolean info = service.saveOrUpdateRank(rank);
		if(info){
			return success("操作成功！");
		}else{
			return error("操作失败！");
		}
	}
	
	/**
	 * 删除产业热度数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteRankInfoById.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult deleteRankInfoById(String id) {
		if(id==null|| StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = service.deleteRankInfoById(id);
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
}
