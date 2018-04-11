package com.huishu.ZSServer.controller;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.ShiroUtil;
import com.huishu.ZSServer.exception.AccountExpiredException;
import com.huishu.ZSServer.exception.AccountStartException;
import com.huishu.ZSServer.security.RSAUtils;
import com.huishu.ZSServer.service.user.impl.UserLogoServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录与相关模块
 * 
 * @author yindq
 * @date 2017年10月31日
 */
@Controller
public class LoginController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserLogoServiceImpl userLogoServiceImpl;
	
	/**
	 * 没有权限
	 * @param response
	 */
	@RequestMapping(value = "/unauthorized.do", method = RequestMethod.GET)
	public void unauthorized(HttpServletResponse response) {
		ShiroUtil.writeResponse(response, "对不起,您没有访问权限！");
	}
	
	/**
	 * 未登录
	 * @param response
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login2(HttpServletResponse response) {
		if(getCurrentShiroUser()==null){
			return "login";
		}
		return "/indusMap/industryMap";
	}
	
	/**
	 * 未登录
	 * @param response
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(HttpServletResponse response) {
		if(getCurrentShiroUser()==null){
			return "login";
		}
		return "/indusMap/industryMap";
	}

	/**
	 * 用户登出
	 * @return
	 */
	@RequestMapping(value = "/logOut.do", method = RequestMethod.GET)
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
	
	/**
	 * 登录过滤器放行后进入此接口
	 * 
	 * @param request
	 *            携带成功或失败的request
	 * @return 返回响应
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult loginAjax(HttpServletRequest request, String username,String type, String password) {
		if (request.getAttribute("success") != null && (boolean) request.getAttribute("success")) {
			userLogoServiceImpl.addLoginLogo(getUserId());
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
	@RequestMapping(value = "/security/generateKey.do", method = RequestMethod.GET)
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
	
}
