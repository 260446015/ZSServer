package com.huishu.ZSServer.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.dto.CaptchaDTO;
import com.huishu.ZSServer.entity.dto.RegisterDTO;
import com.huishu.ZSServer.entity.user.UserBase;
import com.huishu.ZSServer.security.CaptchaManager;
import com.huishu.ZSServer.service.user.UserBaseService;


/**
 * @author hhy
 * @date 2018年3月2日
 * @Parem
 * @return 
 * 申请试用账户
 */
@Controller
@RequestMapping("/apis/reg")
public class RegisterController extends BaseController {
	private Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
	
	@Resource
	private CaptchaManager captchaManager;
	
	@Autowired
	private UserBaseService userBaseService;
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = {"/{page}.html","/{page}.htm"}, method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return "/register/"+page;
	}
	
	/**
	 * 申请试用账户
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/regiterUser.do",method = RequestMethod.POST)
	public AjaxResult regiterUser(@RequestBody RegisterDTO dto){
		if(dto==null){
			LOGGER.debug("申请试用账户异常，异常原因是："+dto);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		if(!captchaManager.checkCaptcha(dto.getTelphone(), dto.getCaptcha())){
			return error(MsgConstant.INCORRECT_CAPTCHA);
		}
		try {
			return userBaseService.addRegisterUser(dto);
		} catch (Exception e) {
			LOGGER.debug("申请试用账户异常，异常原因是：",e);			
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	/**
	 * 根据用户真实姓名和手机号获取手机验证码
	 * @param realName
	 * @param telphone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="/getPhoneCaptchaByUserNameAndPhone.do",method = RequestMethod.POST)
	public AjaxResult getPhoneCaptchaByUserNameAndPhone(@RequestBody CaptchaDTO dto){
		if(StringUtil.isEmpty(dto.getTelphone())||StringUtil.isEmpty(dto.getRealName())){
			LOGGER.debug("获取手机验证码信息失败，失败的原因是：用户名的真实姓名为》》》》"+dto.getRealName()+",手机号为："+dto.getTelphone());
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		UserBase user = userBaseService.findUserByTelphoneAndRealName(dto.getTelphone(),dto.getRealName());
		if(user!=null){
			return error("手机号已注册，不能重复使用");
		}else{
			boolean info = captchaManager.send(dto.getTelphone());
			return info ? success(captchaManager.getCaptcha(dto.getTelphone())).setMessage("验证码已发送") : error("如未收到验证码请稍后再试");
		}
	}
}
