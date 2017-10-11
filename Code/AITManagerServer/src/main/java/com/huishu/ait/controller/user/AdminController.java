package com.huishu.ait.controller.user;

import java.util.List;

import javax.annotation.Resource;

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

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.ImgConstant;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.AccountSearchDTO;
import com.huishu.ait.security.CaptchaManager;
import com.huishu.ait.service.user.UserBaseService;
import com.huishu.ait.service.user.backstage.AdminService;

/**
 * 后台系统-全局管理与账号审核
 * 
 * @author yindq
 * @date 2017年8月24日
 */
@Controller
@RequestMapping(value = "/apis/back/admin")
public class AdminController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserBaseService userBaseService;
	@Resource
	private CaptchaManager captchaManager;

	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "{page}", method = RequestMethod.GET)
	public String showAccount(@PathVariable String page) {
		return "account/"+page;
	}
	
	/**
	 * 全局管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "globalManagement.json", method = RequestMethod.GET)
	public String globalManagement(Model model) {
		try {
			AjaxResult result = adminService.globalManagement();
			model.addAttribute("data",result);
		} catch (Exception e) {
			LOGGER.error("globalManagement失败！", e);
		}
		return "global/globalManagement";
	}
	/**
	 * 园区管理
	 * @return
	 */
	@RequestMapping(value = "gardenManagement.json", method = RequestMethod.GET)
	public String gardenManagement(){
		return "yuanquguanli/gardenManagement";
	}

	/**
	 * 查看待审核会员账号分页列表
	 * 
	 * @param searchModel
	 * @return
	 */
	@RequestMapping(value = "getAccountList.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getAccountList(@RequestBody AccountSearchDTO searchModel) {
		if (null == searchModel || null == searchModel.getType() || null == searchModel.getDay()) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		if (searchModel.getType().equals("1")) {
			// 试用会员申请变成正式会员，状态为9
			searchModel.setType("9");
		}
		try {
			List<UserBase> list = adminService.getAccountList(searchModel);
			for (UserBase base : list) {
				base.setPassword(null);
				base.setSalt(null);
			}
			return success(list);
		} catch (Exception e) {
			LOGGER.error("getAccountList查询失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 查看账号详情（即查看名片）
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getAccountImg.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getAccountImg(Long id) {
		if (null == id) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			UserBase base = userBaseService.findUserByUserId(id);
			String imgUrl = ImgConstant.IP_PORT + base.getImageUrl();
			JSONObject object = new JSONObject();
			object.put("img", imgUrl);
			return success(object);
		} catch (Exception e) {
			LOGGER.error("auditAccount失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

	/**
	 * 账号审核
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "auditAccount.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult auditAccount(Long id) {
		if (null == id) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			UserBase base = userBaseService.findUserByUserId(id);
			Boolean status = adminService.auditAccount(base);
			if (status) {
				boolean result = captchaManager.notice(base.getTelphone());
				return result ? success(null).setMessage("审核成功，用户将收到短信通知") : error("短信发送失败，请稍后再试");
			}
			return error("审核失败，请稍后再试");
		} catch (Exception e) {
			LOGGER.error("auditAccount失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

	/**
	 * 查看预到期会员账号分页列表
	 * 
	 * @param searchModel
	 * @return
	 */
	@RequestMapping(value = "getWarningAccountList.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getWarningAccountList(@RequestBody AccountSearchDTO searchModel) {
		if (null == searchModel || null == searchModel.getType() || null == searchModel.getDay()) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			List<UserBase> list = adminService.getWarningAccountList(searchModel);
			for (UserBase base : list) {
				base.setPassword(null);
				base.setSalt(null);
			}
			return success(list);
		} catch (Exception e) {
			LOGGER.error("getWarningAccountList查询失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 账号预警
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "warnAccount.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult warnAccount(Long id) {
		if (null == id) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return adminService.warnAccount(id, 1);
		} catch (Exception e) {
			LOGGER.error("warnAccount失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
	
	/**
	 * 查看已到期会员账号分页列表
	 * 
	 * @param searchModel
	 * @return
	 */
	@RequestMapping(value = "getDelayAccountList.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getDelayAccountList(@RequestBody AccountSearchDTO searchModel) {
		if (null == searchModel || null == searchModel.getType() || null == searchModel.getDay()) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			List<UserBase> list = adminService.getDelayAccountList(searchModel);
			for (UserBase base : list) {
				base.setPassword(null);
				base.setSalt(null);
			}
			return success(list);
		} catch (Exception e) {
			LOGGER.error("getDelayAccountList查询失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 账号延期
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delayAccount.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult delayAccount(Long id,Integer month) {
		if (null == id || month == null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return adminService.delayAccount(id, month);
		} catch (Exception e) {
			LOGGER.error("delayAccount失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
}
