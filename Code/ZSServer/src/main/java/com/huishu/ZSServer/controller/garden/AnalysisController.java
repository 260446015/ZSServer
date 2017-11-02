package com.huishu.ZSServer.controller.garden;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.CompanyAnnals;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.service.garden.AnalysisService;

/**
 * 关注园区-园区分析
 * 
 * @author yindq
 * @date 2017年10月31日
 */
@RestController
@RequestMapping("/apis/analysis")
public class AnalysisController extends BaseController{
	private Logger LOGGER = LoggerFactory.getLogger(AnalysisController.class);
	
	@Autowired
	private AnalysisService analysisService;	
	
	/**
	 * 企业融资分布
	 * 
	 * @param park
	 * @return
	 */
	@RequestMapping(value = "/getFinancingDistribution.json", method = RequestMethod.GET)
	public AjaxResult getFinancingDistribution(String park) {
		if (StringUtil.isEmpty(park)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			List<JSONObject> list =analysisService.getFinancingDistribution(park);
			return success(list);
		} catch (Exception e) {
			LOGGER.error("获取企业融资分布失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 获取某轮次融资企业列表
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getCompanyList.json", method = RequestMethod.POST)
	public AjaxResult getCompanyList(@RequestBody CompanySearchDTO dto) {
		if (null == dto || StringUtil.isEmpty(dto.getPark()) || StringUtil.isEmpty(dto.getInvest())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Page<Company> page = analysisService.getCompanyList(dto);
			return success(page);
		} catch (Exception e) {
			LOGGER.error("获取某轮次融资企业列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 获取价值榜分布图    
	 * 
	 * @param park
	 * @param type（年产值，年税收）
	 * @return
	 */
	@RequestMapping(value = "/getValueDistribution.json", method = RequestMethod.GET)
	public AjaxResult getValueDistribution(String park,String type) {
		if (StringUtil.isEmpty(park)||StringUtil.isEmpty(type)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			List<JSONObject> list = analysisService.getValueDistribution(park,type);
			return success(list);
		} catch (Exception e) {
			LOGGER.error("获取价值榜分布图失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 获取TOP企业
	 * 
	 * @param park
	 * @param industry
	 * @return
	 */
	@RequestMapping(value = "/getTopCompany.json", method = RequestMethod.GET)
	public AjaxResult getTopCompany(String park,String industry) {
		if (StringUtil.isEmpty(park)||StringUtil.isEmpty(industry)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Page<CompanyAnnals> page = analysisService.getTopCompany(park,industry);
			return success(page);
		} catch (Exception e) {
			LOGGER.error("获取TOP企业失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}
