package com.huishu.ZSServer.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.user.UserBase;
import com.huishu.ZSServer.service.user.UserBaseService;

/**
 * 个人中心相关
 * 
 * @author yindq
 * @date 2017年12月14日
 */
@Controller
@RequestMapping("/user")
public class UserBaseController extends BaseController{

	private Logger LOGGER = LoggerFactory.getLogger(UserBaseController.class);
	
	@Autowired
	private UserBaseService userBaseService;
	
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/{page}",method=RequestMethod.GET)
	public String show(@PathVariable String page) {
		return "/user/"+page;
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
}
