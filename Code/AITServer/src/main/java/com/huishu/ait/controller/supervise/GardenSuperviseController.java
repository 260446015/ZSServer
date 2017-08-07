package com.huishu.ait.controller.supervise;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ConcersUtils;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.CompanyGroup;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.es.entity.dto.CompanyIntelligenceDTO;
import com.huishu.ait.service.companyIntelligence.CompanyIntelligenceService;
import com.huishu.ait.service.gardenSupervise.GardenSuperviseService;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：园区监管的controller
 */
@RestController
@RequestMapping(value = "supervise")
public class GardenSuperviseController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(GardenSuperviseController.class);
	
	@Autowired
	private GardenSuperviseService gardenSuperviseService;
	
	//TODO:获取当前用户所在的园区
	//获取当前用户所在的园区
	String park = "中关村软件园";
	/**
	 * @return
	 * 获取当前园区的信息
	 */
	@RequestMapping(value = "getGardenInfo.json",method=RequestMethod.GET)
	public AjaxResult getGardenInfo(){
		try {
			JSONObject json = gardenSuperviseService.getGardenInfo(park);
			return success(json);
		} catch (Exception e) {
			log.error("获取园区信息失败", e.getMessage());
			return null;
		}
	}
	/**
	 * @return
	 * 获取当前园区中的所有企业列表
	 */
	@RequestMapping(value = "getCompanyFromGarden.json",method=RequestMethod.GET)
	public AjaxResult getCompanyFromGarden(){
		try {
			JSONArray jsonArray = gardenSuperviseService.getCompanyFromGarden(park);
			return success(jsonArray);
		} catch (Exception e) {
			log.error("查询园区企业失败", e.getMessage());
			return null;
		}
	}
	/**
	 * @return
	 * 添加企业分组
	 */
	@RequestMapping(value = "addCompanyGroup.json",method=RequestMethod.GET)
	public AjaxResult addCompanyGroup(String groupName){
		JSONObject result = new JSONObject();
		try {
			String state = gardenSuperviseService.addCompanyGroup(groupName);
			if ("success".equals(state)) {
				result.put("state", "success");
			}
			if ("分组已经存在".equals(state)) {
				result.put("state", "分组已经存在");
			}
			return success(result);
		} catch (Exception e) {
			result.put("state", "failure");
			log.error("分组添加失败", e.getMessage());
			return success(result);
		}
	}
	/**
	 * @return
	 * 查询分组信息
	 */
	@RequestMapping(value = "selectCompanyGroup.json",method=RequestMethod.GET)
	public AjaxResult selectCompanyGroup(){
		try {
			List<CompanyGroup> list = gardenSuperviseService.selectCompanyGroup();
			return success(list);
		} catch (Exception e) {
			log.error("查询失败",e.getMessage());
			return null;
		}
	}
	
	/**
	 * @param dto
	 * 初始化分页的方法
	 */
	/*private ExpertOpinionDTO initPage(ExpertOpinionDTO dto){
		if(dto.getPageNumber() == null){
			dto.setPageNumber(1);
		}
		if(dto.getPageSize() == null){
			dto.setPageSize(ConcersUtils.PAGE_SIZE);
		}
		if(dto.getPageNumber() > ConcersUtils.ES_MAX_PAGENUMBER){
			dto.setPageNumber(ConcersUtils.ES_MAX_PAGENUMBER);
		}
		return dto;
	}*/
}
