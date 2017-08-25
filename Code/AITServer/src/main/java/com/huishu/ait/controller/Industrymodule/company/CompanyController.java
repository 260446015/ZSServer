package com.huishu.ait.controller.Industrymodule.company;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanyDTO;
import com.huishu.ait.service.company.CompanyService;

/**
 * 企业排行榜
 * 
 * @author yindawei
 *
 */
@RestController
@RequestMapping("/apis/business")
public class CompanyController extends BaseController {

	@Resource
	private CompanyService cs;

	private Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

	/**
	 * 查询企业排行
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/findCompaniesDesc.json", method = RequestMethod.POST)
	public AjaxResult findCompanies(@RequestBody CompanyDTO dto) {
		if (null == dto.getMsg() || dto.getMsg().length == 0) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		dto = initPage(dto);
		JSONArray arr = null;
		try {
			arr = cs.findCompaniesOder(dto);
		} catch (Exception e) {
			LOGGER.error("查询企业列表失败", e);
		}
		return success(arr);
	}

	/**
	 * 查询企业排行榜详情内容
	 * 
	 * @param coid
	 * @return
	 */
	@RequestMapping(value = "/findCompanieOrderById", method = RequestMethod.GET)
	public AjaxResult findCompanieOrderById(String coid) {
		if (StringUtil.isEmpty(coid)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject obj = null;
		try {
			obj = cs.findCompanieOrderById(coid);
		} catch (Exception e) {
			LOGGER.error("给据企业排行榜id查询失败", e);
		}
		return success(obj);
	}

	/**
	 * 初始化分页
	 * 
	 * @param dto
	 * @return
	 */
	private CompanyDTO initPage(CompanyDTO dto) {
		if (dto.getPageNumber() == null) {
			dto.setPageNumber(ConcersUtils.ES_MIN_PAGENUMBER);
		}
		if (dto.getPageSize() == null) {
			dto.setPageSize(ConcersUtils.PAGE_SIZE);
		}
		if (dto.getPageNumber() > ConcersUtils.ES_MAX_PAGENUMBER) {
			dto.setPageNumber(ConcersUtils.ES_MAX_PAGENUMBER);
		}
		return dto;
	}
}
