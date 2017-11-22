package com.huishu.ZSServer.controller.financing;

import java.util.Arrays;
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
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.es.entity.FinancingInfo;
import com.huishu.ZSServer.service.financing.FinancingService;

/**
 * 融资快报
 * 
 * @author yindq
 * @date 2017年10月30日
 */
@RestController
@RequestMapping("/apis/financing")
public class FinancingController extends BaseController {
	private Logger LOGGER = LoggerFactory.getLogger(FinancingController.class);

	@Autowired
	private FinancingService financingService;

	/**
	 * 获取融资企业列表
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getCompanyList.json", method = RequestMethod.POST)
	public AjaxResult getCompanyList(@RequestBody CompanySearchDTO dto) {
		if (null == dto || null==dto.getIndustry() || null==dto.getArea()
				|| null==dto.getInvest() || StringUtil.isEmpty(dto.getSort())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Page<FinancingInfo> page = financingService.getCompanyList(dto);
			return successPage(page);
		} catch (Exception e) {
			LOGGER.error("获取融资企业列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 获取融资柱状图    
	 * 
	 * @param type（week, month, season, year）
	 * @return
	 */
	@RequestMapping(value = "/getHistogram.json", method = RequestMethod.GET)
	public AjaxResult getHistogram(String type) {
		if (StringUtil.isEmpty(type)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			List<JSONObject> list = financingService.getHistogram(type);
			return success(list);
		} catch (Exception e) {
			LOGGER.error("获取融资柱状图失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 获取融资动态数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getFinancingDynamic.json", method = RequestMethod.GET)
	public AjaxResult getFinancingDynamic() {
		try {
			Page<FinancingInfo> page = financingService.getFinancingDynamic();
			return successPage(page);
		} catch (Exception e) {
			LOGGER.error("获取融资动态数据失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 获取某产业融资企业推荐列表
	 * 
	 * @param industry
	 * @return
	 */
	@RequestMapping(value = "/getFinancingCompany.json", method = RequestMethod.POST)
	public AjaxResult getFinancingCompany(@RequestBody String[] industry) {
		if (null == industry) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		if(industry.length==0){
			return success(null);
		}
		try {
			List<JSONObject> list = financingService.getFinancingCompany(Arrays.asList(industry));
			return success(list);
		} catch (Exception e) {
			LOGGER.error("获取某产业融资企业推荐列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}
