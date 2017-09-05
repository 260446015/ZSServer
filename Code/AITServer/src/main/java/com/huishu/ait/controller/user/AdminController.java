package com.huishu.ait.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.user.AdminService;

/**
 * 后台管理人员操作相关
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
	
	/**
	 * 全局管理
	 * @return
	 */
	@RequestMapping(value = "globalManagement.json", method = RequestMethod.GET)
	public AjaxResult globalManagement() {
		try {
			return adminService.globalManagement(getUserPark());
		} catch (Exception e) {
			LOGGER.error("globalManagement失败！", e);
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
		try {
			return adminService.auditAccount(id);
		} catch (Exception e) {
			LOGGER.error("auditAccount失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

}
