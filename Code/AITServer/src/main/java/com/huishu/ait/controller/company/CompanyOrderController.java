package com.huishu.ait.controller.company;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanyDTO;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.service.company.CompanyService;
/**
 * 企业排行榜
 * @author yindawei 
 *
 */
@RestController
@RequestMapping("/business")
public class CompanyOrderController extends BaseController{

	@Resource
	private CompanyService cs;
	
	private Logger LOGGER = LoggerFactory.getLogger(CompanyOrderController.class);
	/**
	 * 查询企业排行
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/findCompaniesDesc.json")
	public AjaxResult findCompanies(CompanyDTO dto){
		if(null == dto){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		dto = initPage(dto);
		JSONArray arr = null;
		try{
			arr = cs.findCompaniesOder(dto);
		}catch(Exception e){
			LOGGER.error("查询园区列表失败", e);
		}
		return success(arr);
	}
	
	private CompanyDTO initPage(CompanyDTO dto){
		if(dto.getPageNum() == null){
			dto.setPageNum(ConcersUtils.ES_MIN_PAGENUMBER);
		}
		if(dto.getPageSize() == null){
			dto.setPageSize(ConcersUtils.PAGE_SIZE);
		}
		if(dto.getPageNum() > ConcersUtils.ES_MAX_PAGENUMBER){
			dto.setPageNum(ConcersUtils.ES_MAX_PAGENUMBER);
		}
		return dto;
	}
}
