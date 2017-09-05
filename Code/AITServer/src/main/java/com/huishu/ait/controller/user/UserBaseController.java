package com.huishu.ait.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.UserPasswordDTO;
import com.huishu.ait.service.user.UserBaseService;

/**
 * 用户个人操作相关
 * 
 * @author yindq
 * @date 2017年8月24日
 */
@Controller
@RequestMapping(value = "/apis/user")
public class UserBaseController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserBaseController.class);
	@Autowired
	private UserBaseService userBaseService;

	/**
	 * 查看我的个人信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "findMyInformation.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult findMyInformation() {
		try {
			UserBase base = userBaseService.findUserByUserId(getUserId());
			base.setPassword(null);
			base.setSalt(null);
			return success(base);
		} catch (Exception e) {
			LOGGER.error("findMyInformation失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

	/**
	 * 修改密码
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "modifyPassword.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult modifyPassword(@RequestBody UserPasswordDTO param) {
		if (null == param || StringUtil.isEmpty(param.getOldPassword()) || StringUtil.isEmpty(param.getNewPassword())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			param.setUserId(getUserId());
			return userBaseService.modifyPassword(param);
		} catch (Exception e) {
			LOGGER.error("modifyPassword失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
	
	/**
	 * 修改邮箱
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "modifyEmail.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult modifyEmail(String email) {
		if (StringUtil.isEmpty(email)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return userBaseService.modifyEmail(getUserId(),email);
		} catch (Exception e) {
			LOGGER.error("modifyEmail失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

}
