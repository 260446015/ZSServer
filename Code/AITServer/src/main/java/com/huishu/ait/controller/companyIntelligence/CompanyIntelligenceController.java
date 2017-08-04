package com.huishu.ait.controller.companyIntelligence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.util.ConcersUtils;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.dto.CompanyIntelligenceDTO;
import com.huishu.ait.service.companyIntelligence.CompanyIntelligenceService;
/**
 * @author yxq
 * @date 2017年8月4日
 * @功能描述：企业情报画像
 */
@RestController
@RequestMapping(value = "intelligence")
public class CompanyIntelligenceController extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(CompanyIntelligenceController.class);
	@Autowired
	private CompanyIntelligenceService companyIntelligenceService;
	/**
	 * @param dto
	 * @return
	 * 获取企业情报列表信息
	 */
	@RequestMapping(value = "getCompanyIntelligenceList.json",method=RequestMethod.POST)
	public AjaxResult getCompanyIntelligenceList(CompanyIntelligenceDTO dto){
		try {
			dto = initPage(dto);
			JSONArray json = companyIntelligenceService.getCompanyIntelligenceList(dto);
			return success(json);
		} catch (Exception e) {
			log.error("获取企业情报失败", e.getMessage());
			return null;
		}
	}
	
	/**
	 * @param dto
	 * 初始化分页的方法
	 */
	private CompanyIntelligenceDTO initPage(CompanyIntelligenceDTO dto){
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
	}
}
