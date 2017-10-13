package com.huishu.ait.controller;

import java.io.OutputStream;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ShiroUtil;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CaptchaDTO;
import com.huishu.ait.entity.dto.FindPasswordDTO;
import com.huishu.ait.entity.dto.RegisterDTO;
import com.huishu.ait.exception.AccountExpiredException;
import com.huishu.ait.exception.AccountStartException;
import com.huishu.ait.security.CaptchaManager;
import com.huishu.ait.security.CaptchaUsernamePasswordToken;
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
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return page;
	}
	
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "account/{page}", method = RequestMethod.GET)
	public String showAccount(@PathVariable String page) {
		return "account/"+page;
	}

	/**
	 * 未登录
	 */
	@RequestMapping(value = "apis/login.do", method = RequestMethod.GET)
	public void login(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject object = new JSONObject();
		object.put("code", "1002");
		ShiroUtil.writeResponse(response, object);
	}

	/**
	 * 没有权限
	 */
	@RequestMapping(value = "apis/unauthorized.do", method = RequestMethod.GET)
	public void unauthorized(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject object = new JSONObject();
		object.put("code", "1004");
		ShiroUtil.writeResponse(response, object);
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
	public AjaxResult loginAjax(HttpServletRequest request, String username,String type, String password) {
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
			} else if (error.equals(AccountExpiredException.class.getName())) {
				message = MsgConstant.ACCOUNTEXPIRED;
			} else if (error.equals(AccountStartException.class.getName())) {
				message = MsgConstant.ACCOUNTSTART;
			} else if (error.equals(ExcessiveAttemptsException.class.getName())) {
				message = MsgConstant.LOCKING;
			}
		} else if (null != getCurrentShiroUser()) {
			RSAPrivateKey priKey = (RSAPrivateKey) request.getSession().getAttribute("privateKey");
			UserBase base = userBaseService.findUserByUserAccount(username,type);
			String inPassword = getInPassword(password, base.getSalt(), priKey);
			String dbPassword = base.getPassword();
			if (dbPassword.equals(inPassword)) {
				return success(MsgConstant.LOGIN_SUCCESS).setMessage(MsgConstant.LOGIN_SUCCESS);
			}
			message = MsgConstant.CREDENTIAL_ERROR;
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
	@RequestMapping(value = "apis/security/generateKey.do", method = RequestMethod.GET)
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
	public AjaxResult getPhoneCaptcha(@RequestBody CaptchaDTO param) {
		if (param == null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		String telphone = param.getTelphone();
		String type = param.getType();
		if (StringUtil.isEmpty(telphone)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		if (StringUtils.isEmpty(type)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		UserBase user = userBaseService.findUserByTelphone(telphone);
		if ("findPassword".equals(type)) {
			if (user == null) {
				return error("该手机号未被注册");
			}
		} else {
			if (user != null) {
				return error(MsgConstant.PHONE_REPEAT);
			}
		}
		boolean result = captchaManager.send(telphone);
		return result ? success(null).setMessage("验证码已发送") : error("如未收到验证码请稍后再试");
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
	 * 用户登出
	 *
	 * @return
	 */
	@RequestMapping(value = "apis/logOut.do", method = RequestMethod.GET)
	public void logOut(HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		try {
			if (subject.isAuthenticated()) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("user {} to logout", subject.getPrincipal());
				}
				subject.logout();
			}
		} catch (Exception e) {
			LOGGER.error("logout失败！", e);
			ShiroUtil.writeResponse(response, MsgConstant.SYSTEM_ERROR);
		}
		ShiroUtil.writeResponse(response, "注销成功");
	}

	/**
	 * 找回密码
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "apis/findPassword.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findPassword(@RequestBody FindPasswordDTO dto) {
		if (null == dto || StringUtil.isEmpty(dto.getCaptcha()) || StringUtil.isEmpty(dto.getNewPassword())
				|| StringUtil.isEmpty(dto.getTelphone())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		if (!captchaManager.checkCaptcha(dto.getTelphone(), dto.getCaptcha())) {
			return error(MsgConstant.INCORRECT_CAPTCHA);
		}
		try {
			return userBaseService.findPassword(dto);
		} catch (Exception e) {
			LOGGER.error("findPassword失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	@RequestMapping(value = "apis/temporaryDemo.do", method = RequestMethod.GET)
	public void temporaryDemo(HttpServletRequest request, HttpServletResponse response) {
		try {
			KeyPair keyPair = RSAUtils.getKeys();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			String modulus = publicKey.getModulus().toString();
			String public_exponent = publicKey.getPublicExponent().toString();
			String private_exponent = privateKey.getPrivateExponent().toString();
			// 使用模和指数生成公钥和私钥
			RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);
			RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent);
			request.getSession().setAttribute("privateKey", priKey);
			// 加密后的密文
			String mi = RSAUtils.encryptByPublicKey("nagnoix", pubKey);

			UsernamePasswordToken token = new CaptchaUsernamePasswordToken("xiongan", mi.toCharArray(), false, "", "",
					"user");
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			if (getCurrentShiroUser() == null) {
				response.setContentType("application/json");
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(JSON.toJSONString("免登陆失败，请联系管理员").getBytes("UTF-8"));
				outputStream.flush();
				outputStream.close();
			}
			 response.sendRedirect("http://58.16.181.24:9208/intelligence/headlines");
			 //response.sendRedirect("http://127.0.0.1:8000/intelligence/headlines");
		} catch (Exception e) {
			LOGGER.error("免登陆失败！", e);
		}
	}
}
