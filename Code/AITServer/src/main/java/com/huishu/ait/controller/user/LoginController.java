package com.huishu.ait.controller.user;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.PersonalValidtor;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.RegisterDTO;
import com.huishu.ait.exception.AccountExpiredException;
import com.huishu.ait.exception.IncorrectCaptchaException;
import com.huishu.ait.security.CaptchaManager;
import com.huishu.ait.security.RSAUtils;
import com.huishu.ait.service.user.UserBaseService;

/**
 * 用户登录相关
 * 
 * @author yindq
 * @date 2017年8月8日
 */
@Controller
public class LoginController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserBaseService userBaseService;
	@Resource
	private CaptchaManager captchaManager;

	/**
	 * 根路径访问
	 * 
	 * @return 返回登录模板页
	 */
	@RequestMapping(value = "/")
	public String tologin() {
		return "login";
	}

	/**
	 * 模板页
	 * 
	 * @param request
	 *            请求
	 * @return 返回消息
	 */
	@RequestMapping(value = "apis/login.do", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult login(HttpServletRequest request) {
		return error("请先登录").setStatus(2);
	}

	/**
	 * 登录过滤器放行后进入此接口
	 * 
	 * @param request
	 *            携带成功或失败的request
	 * @return 返回响应
	 */
	@RequestMapping(value = "apis/login.do", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult loginAjax(HttpServletRequest request) {
		if (request.getAttribute("success") != null && (boolean) request.getAttribute("success")) {
			return success(MsgConstant.LOGIN_SUCCESS).setMessage(MsgConstant.LOGIN_SUCCESS);
		}

		// 登录失败从request中获取shiro处理的异常信息。
		String message = MsgConstant.LOGIN_ERRROR;
		String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		LOGGER.info("登陆失败的原因" + error);
		if (error != null) {
			if (error.equals(IncorrectCredentialsException.class.getName())) {
				message = MsgConstant.CREDENTIAL_ERROR;
				Object passwordErrorCount = request.getSession().getAttribute("passwordErrorCount");
				if (passwordErrorCount != null) {
					Integer errorCount = (Integer) passwordErrorCount;
					request.getSession().setAttribute("passwordErrorCount", ++errorCount);
				} else {
					request.getSession().setAttribute("passwordErrorCount", 1);
				}
			} else if (error.equals(IncorrectCaptchaException.class.getName())) {
				message = MsgConstant.INCORRECT_CAPTCHA;
			} else if (error.equals(AccountExpiredException.class.getName())) {
				message = MsgConstant.ACCOUNTEXPIRED;
			}
		} else if (null != getCurrentShiroUser()) {
			message = MsgConstant.REPETITIVEOPERATION;
		}
		return error(message);
	}

	/**
	 * 生成密码私钥和公钥
	 * 
	 * @param request
	 *            http请求
	 * @return 返回公钥
	 */
	@RequestMapping(value = "apis/security/generateKey.do")
	@ResponseBody
	public AjaxResult generateKeyAjax(HttpServletRequest request) {
		AjaxResult result = new AjaxResult();
		KeyPair keyPair = RSAUtils.getKeys();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		// 私钥保存在session中，用于解密
		request.getSession().setAttribute("privateKey", privateKey);

		String publicKeyExponent = publicKey.getPublicExponent().toString(16);
		String publicKeyModulus = publicKey.getModulus().toString(16);
		Map<String, Object> keys = new HashMap<>();
		keys.put("publicKeyExponent", publicKeyExponent);
		keys.put("publicKeyModulus", publicKeyModulus);
		result.setSuccess(true).setData(keys);
		return result;
	}

	/**
	 * 获取手机验证码
	 *
	 * @param param
	 *            参数
	 * @return 获取结果
	 */
	@ResponseBody
	@RequestMapping(value = "apis/getPhoneCaptcha.json", method = RequestMethod.POST)
	public AjaxResult getPhoneCaptcha(@RequestBody JSONObject param) {
		if (param == null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		String telphone = param.getString("telphone");
		String type = param.getString("type");
		String userAccount = param.getString("userAccount");
		if (StringUtil.isEmpty(telphone)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		if (StringUtils.isEmpty(type)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		UserBase user = userBaseService.findUserByTelphone(telphone);
		if ("findPassword".equals(type)) {
			if (user == null || user.getUserAccount().equals(userAccount)) {
				return error(MsgConstant.PHONE_ERROR);
			}
		} else {
			if (user != null) {
				return error(MsgConstant.PHONE_REPEAT);
			}
		}
		boolean result = captchaManager.send(telphone);
		return result ? success(null).setMessage("验证码已发送") : error("稍后再试");
	}

	/**
	 * 申请试用
	 *
	 * @param dto
	 *            试用用户dto
	 * @param result
	 *            参数验证结果
	 * @return 申请结果
	 */
	@ResponseBody
	@RequestMapping(value = "apis/register.json", method = RequestMethod.POST)
	public AjaxResult register(@Valid @RequestBody RegisterDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			for (ObjectError objectError : result.getAllErrors()) {
				sb.append(((FieldError) objectError).getField() + " : ").append(objectError.getDefaultMessage());
			}
			return error(sb.toString());
		}
		if (!captchaManager.checkCaptcha(dto.getTelphone(), dto.getCaptcha())) {
			return error(MsgConstant.INCORRECT_CAPTCHA);
		}
		return userBaseService.addRegisterUser(dto);
	}

	/**
	 * 绑定PersonalValidator
	 * 
	 * @param webDataBinder
	 */
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new PersonalValidtor());
	}
}
