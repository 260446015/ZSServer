package com.huishu.ZSServer.controller.user;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.UserLabel;
import com.huishu.ZSServer.entity.dto.LabelDTO;
import com.huishu.ZSServer.entity.dto.UserDTO;
import com.huishu.ZSServer.entity.user.UserBase;
import com.huishu.ZSServer.service.user.UserBaseService;

/**
 * 个人中心相关
 * 
 * @author yindq
 * @date 2017年12月14日
 */
@Controller
@RequestMapping("/apis/user")
public class UserBaseController extends BaseController {

	private Logger LOGGER = LoggerFactory.getLogger(UserBaseController.class);

	@Autowired
	private UserBaseService userBaseService;

	/**
	 * 直接跳转页面
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page, String articleLink, Model model) {
		if (!StringUtil.isEmpty(articleLink)) {
			model.addAttribute("articleLink", articleLink);
		}
		return "/user/" + page;
	}

	/**
	 * 查看我的个人信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "findMyInformation.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult findMyInformation() {
		try {
			UserBase base = userBaseService.findByUserId(getUserId());
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
	 * @param beforPassword
	 * @param newPassword
	 * @return
	 */
	@RequestMapping(value = "modifyPassword.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult modifyPassword(String beforPassword, String newPassword) {
		if (StringUtil.isEmpty(beforPassword) || StringUtil.isEmpty(newPassword)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			String message = userBaseService.modifyPassword(getUserId(), beforPassword, newPassword);
			if (MsgConstant.OPERATION_SUCCESS.equals(message)) {
				return success(true).setMessage(MsgConstant.OPERATION_SUCCESS);
			} else {
				return success(false).setMessage(message);
			}
		} catch (Exception e) {
			LOGGER.error("modifyPassword失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

	/**
	 * 修改个人信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "modifyInformation.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult modifyInformation(@RequestBody UserDTO dto) {
		if (StringUtil.isEmpty(dto.getRealName()) || StringUtil.isEmpty(dto.getTelphone())
				|| StringUtil.isEmpty(dto.getUserDepartment()) || StringUtil.isEmpty(dto.getUserEmail())
				|| StringUtil.isEmpty(dto.getUserJob()) || StringUtil.isEmpty(dto.getUserPark())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			if (userBaseService.modifyInformation(getUserId(), dto)) {
				return success(true).setMessage(MsgConstant.OPERATION_SUCCESS);
			} else {
				return success(false).setMessage(MsgConstant.OPERATION_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error("modifyPassword失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

	@ResponseBody
	@RequestMapping(value = "/getLabel.json", method = RequestMethod.GET)
	public AjaxResult getLabel() {
		// 获取用户的id，测试使用测试数据
		// Long id = getCurrentShiroUser().getId();
		Long uid = (long) 1;
		UserLabel user = userBaseService.findLabelByUserId(uid);
		if (user == null) {
			return error("没有标签信息");
		} else {
			LabelDTO dto = new LabelDTO();
			dto.setArea(user.getArea());
			dto.setIndustry(user.getIndustry());
			dto.setRegister(user.getRegister().split(","));
			dto.setRegisterTime(user.getRegisterTime().split(","));
			return success(dto);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/updateLabel.json", method = RequestMethod.POST)
	public AjaxResult updateLabel(@RequestBody LabelDTO dto) {
		// 获取用户的id，测试使用测试数据
		// Long id = getCurrentShiroUser().getId();
		Long uid = (long) 1;
		UserLabel user = userBaseService.findLabelByUserId(uid);
		if (user == null) {
			user = new UserLabel();
			user.setUid(uid);
			user.setArea(dto.getArea());
			user.setIndustry(dto.getIndustry());
			user.setRegister(Arrays.toString(dto.getRegister()));
			user.setRegisterTime(Arrays.toString(dto.getRegisterTime()));
		} else {
			user.setArea(dto.getArea());
			user.setIndustry(dto.getIndustry());
			user.setRegister(Arrays.toString(dto.getRegister()).replace("[", "").replace("]", ""));
			user.setRegisterTime(Arrays.toString(dto.getRegisterTime()).replace("[", "").replace("]", ""));
		}
		boolean info = userBaseService.updateLabel(user);
		return success(info);
	}
}
