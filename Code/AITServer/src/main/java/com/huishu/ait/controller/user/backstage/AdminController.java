package com.huishu.ait.controller.user.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.AccountSearchDTO;
import com.huishu.ait.security.CaptchaManager;
import com.huishu.ait.service.user.AdminService;
import com.huishu.ait.service.user.UserBaseService;

/**
 * 后台系统-全局管理与账号审核
 * 
 * @author yindq
 * @date 2017年8月24日
 */
@RestController
@RequestMapping(value = "/apis/admin")
public class AdminController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserBaseService userBaseService;
	@Resource
	private CaptchaManager captchaManager;
	
	/**
	 * 全局管理
	 * @return
	 */
	@RequestMapping(value = "globalManagement.json", method = RequestMethod.GET)
	public AjaxResult globalManagement() {
		try {
			return adminService.globalManagement();
		} catch (Exception e) {
			LOGGER.error("globalManagement失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
	
	/**
	 * 查看会员账号分页列表
	 * @param searchModel
	 * @return
	 */
	@RequestMapping(value = "getAccountList.json", method = RequestMethod.POST)
	public AjaxResult getAccountList(@RequestBody AccountSearchDTO searchModel) {
		if (null==searchModel || null==searchModel.getType()|| null==searchModel.getDay()) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			List<UserBase> list = adminService.getAccountList(searchModel);
			for (UserBase base : list) {
				base.setPassword(null);
				base.setSalt(null);
			}
			return success(changeObject(searchModel, list));
		} catch (Exception e) {
			LOGGER.error("getAccountList查询失败！",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 账号审核
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "auditAccount.json", method = RequestMethod.GET)
	public AjaxResult auditAccount(Long id) {
		if (null==id) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			UserBase base = userBaseService.findUserByUserId(id);
			Boolean status = adminService.auditAccount(base);
			if(status){
				boolean result = captchaManager.notice(base.getTelphone());
				return  result ? success(null).setMessage("审核成功，用户将收到短信通知") : error("短信发送失败，请稍后再试");
			}
			return error("审核失败，请稍后再试");
		} catch (Exception e) {
			LOGGER.error("auditAccount失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
	
	/**
	 * 查看预到期会员账号分页列表
	 * @param searchModel
	 * @return
	 */
	@RequestMapping(value = "getWarningAccountList.json", method = RequestMethod.POST)
	public AjaxResult getWarningAccountList(@RequestBody AccountSearchDTO searchModel) {
		if (null==searchModel || null==searchModel.getType()|| null==searchModel.getDay()) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			List<UserBase> list = adminService.getWarningAccountList(searchModel);
			for (UserBase base : list) {
				base.setPassword(null);
				base.setSalt(null);
			}
			return success(changeObject(searchModel, list));
		} catch (Exception e) {
			LOGGER.error("getAccountList查询失败！",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 账号预警
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "warnAccount.json", method = RequestMethod.GET)
	public AjaxResult warnAccount(Long id) {
		if (null==id) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return adminService.warnAccount(id,1);
		} catch (Exception e) {
			LOGGER.error("auditAccount失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
	
}
