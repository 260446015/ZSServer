package com.huishu.ManageServer.controller.financing;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dto.CompanySearchDTO;
import com.huishu.ManageServer.es.entity.FinancingInfo;
import com.huishu.ManageServer.service.financing.FinancingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 融资数据
 *
 * @author yindq
 * @date 2018/1/31
 */
@Controller
@RequestMapping("/apis/financing")
public class FinancingController extends BaseController {
	private Logger LOGGER = LoggerFactory.getLogger(FinancingController.class);

	@Autowired
	private FinancingService financingService;

	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return "/financing/"+page;
	}
	
	/**
	 * 获取融资企业列表
	 * 
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCompanyList.json", method = RequestMethod.POST)
	public AjaxResult getCompanyList(@RequestBody CompanySearchDTO dto) {
		if (null == dto || null==dto.getIndustry() || null==dto.getArea()
				|| null==dto.getInvest() || StringUtil.isEmpty(dto.getSort())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Page<FinancingInfo> page = financingService.getCompanyList(dto);
			return successPage(page,dto.getPageNum()+1);
		} catch (Exception e) {
			LOGGER.error("获取融资企业列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

}
