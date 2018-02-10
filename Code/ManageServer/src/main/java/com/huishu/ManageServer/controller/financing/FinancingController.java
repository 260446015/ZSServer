package com.huishu.ManageServer.controller.financing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dto.CompanySearchDTO;
import com.huishu.ManageServer.entity.dto.FinancingDTO;
import com.huishu.ManageServer.es.entity.FinancingInfo;
import com.huishu.ManageServer.service.financing.FinancingService;

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
	@RequestMapping(value = "/{page}.html", method = RequestMethod.GET)
	public String show(@PathVariable String page,String id,Model model) {
		if("financingEdit".equals(page)){
			model.addAttribute("id",id);
		}
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

	/**
	 * 删除融资企业
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "dropCompany.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult dropCompany(String id) {
		if(StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = financingService.dropCompany(id);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("删除用户失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 查看融资企业详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getCompanyById.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getCompanyById(String id) {
		if(StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(financingService.getCompanyById(id));
		}catch (Exception e){
			LOGGER.error("查看融资企业失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 修改融资企业
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "saveCompany.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveCompany(@RequestBody FinancingDTO dto) {
		if(dto==null||StringUtil.isEmpty(dto.getLogo())||StringUtil.isEmpty(dto.getFinancingAmount())||StringUtil.isEmpty(dto.getFinancingCompany())
				||StringUtil.isEmpty(dto.getFinancingDate())||StringUtil.isEmpty(dto.getInvest())||StringUtil.isEmpty(dto.getInvestor())){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = financingService.saveCompany(dto);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("修改失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

}
