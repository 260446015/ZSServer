package com.huishu.ManageServer.controller;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.ShiroUtil;
import com.huishu.ManageServer.entity.dbFirst.AdminBase;
import com.huishu.ManageServer.entity.dbFirst.UserBase;
import com.huishu.ManageServer.exception.AccountExpiredException;
import com.huishu.ManageServer.exception.AccountStartException;
import com.huishu.ManageServer.security.RSAUtils;
import com.huishu.ManageServer.service.user.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录相关
 * 
 * @author yindq
 * @date 2017年8月8日
 */
@Controller
public class LoginController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

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
	@RequestMapping(value = "/apis/login.do", method = RequestMethod.GET)
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
	@RequestMapping(value = "/apis/login.do", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult loginAjax(HttpServletRequest request, String username, String type, String password) {
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
			return success(MsgConstant.LOGIN_SUCCESS).setMessage(MsgConstant.LOGIN_SUCCESS);
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
	@RequestMapping(value = "/apis/generateKey.do", method = RequestMethod.GET)
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
	@RequestMapping(value = "/apis/logOut.do", method = RequestMethod.GET)
	public String logOut(HttpServletResponse response) {
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
		return "login";
	}

}
