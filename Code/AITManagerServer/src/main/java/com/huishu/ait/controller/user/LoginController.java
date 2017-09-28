package com.huishu.ait.controller.user;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ShiroUtil;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.FindPasswordDTO;
import com.huishu.ait.exception.AccountExpiredException;
import com.huishu.ait.exception.AccountStartException;
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
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return page;
	}
	
	/**
	 * 未登录
	 */
	@RequestMapping(value = "apis/login.do", method = RequestMethod.GET)
	public String login(HttpServletResponse response) {
		return "login";
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
}
