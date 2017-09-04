package com.huishu.ait.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.user.UserBaseService;

/**
 * 后台管理人员操作相关
 * 
 * @author yindq
 * @date 2017年8月24日
 */
@Controller
@RequestMapping(value = "/apis/admin")
public class AdminController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private UserBaseService userBaseService;
	
	/**
	 * 账号审核
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "auditAccount.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult auditAccount(Long id) {
		try {
			UserBase base = userBaseService.findUserByUserId(getUserId());
			return success(base);
		} catch (Exception e) {
			LOGGER.error("findMyInformation失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

}
