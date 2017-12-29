package com.huishu.ManageServer.controller.enterprise;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbFirst.Enterprise;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.service.enterprise.EnterpriseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 企业库操作
 *
 * @author yindq
 * @date 2017/12/28
 */
@Controller
@RequestMapping("/apis/enterprise")
public class EnterpriseController extends BaseController {
	@Autowired
	private EnterpriseService enterpriseService;

	private static final Logger LOGGER = Logger.getLogger(EnterpriseController.class);
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "{page}" }, method = RequestMethod.GET)
	public String pageJump(@PathVariable String page,String id,Model model) {
		if("editEnterprise".equals(page)){
			try {
				Enterprise enterprise = enterpriseService.findById(Long.valueOf(id));
				model.addAttribute("enterprise",enterprise);
			}catch (Exception e){
				LOGGER.error("查看详情失败!", e);
			}
		}
		return "/enterprise/" + page;
	}

	/**
	 * 分页查看企业列表
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "listEnterprise.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult listEnterprise(@RequestBody AbstractDTO dto) {
		try {
			Page<Enterprise> page = enterpriseService.listEnterprise(dto);
			return successPage(page,dto.getPageNum()+1);
		}catch (Exception e){
			LOGGER.error("分页查看企业列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 添加/修改企业
	 * @param enter
	 * @return
	 */
	@RequestMapping(value = "saveEnterprise.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveEnterprise(@RequestBody Enterprise enter) {
		if(enter!=null&& StringUtil.isEmpty(enter.getCompany())&& StringUtil.isEmpty(enter.getIndustry())
				&& StringUtil.isEmpty(enter.getRegisterTime())){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = enterpriseService.saveEnterprise(enter);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
		}catch (Exception e){
			LOGGER.error("添加/修改企业失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 删除企业
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "dropEnterprise.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult delIndusCompany(String id) {
		if(id!=null&& StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Long aLong;
		try{
			aLong = Long.valueOf(id);
		}catch (Exception e){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Boolean flag = enterpriseService.dropEnterprise(aLong);
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
