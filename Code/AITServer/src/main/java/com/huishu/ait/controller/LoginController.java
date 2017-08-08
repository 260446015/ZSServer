package com.huishu.ait.controller;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.exception.AccountExpiredException;
import com.huishu.ait.exception.IncorrectCaptchaException;
import com.huishu.ait.security.RSAUtils;

/**
 * 用户登录相关
 * @author yindq
 * @date 2017年8月8日
 */
@Controller
public class LoginController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * 根路径访问
     * @return  返回登录模板页
     */
    @RequestMapping(value = "/")
    public String tologin() {
        return "login";
    }
    
    /**
     * 模板页
     * @param request 请求
     * @return 返回登录模板页
     */
    @RequestMapping(value = "login.do", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        Subject user = SecurityUtils.getSubject();
        if (user.isAuthenticated()) {
            user.logout();
        }
        return "login";
    }
    
    /**
     * 登录过滤器放行后进入此接口
     * @param request 携带成功或失败的request
     * @return 返回响应
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult loginAjax(HttpServletRequest request) {
        if (request.getAttribute("success") != null && (boolean) request.getAttribute("success")) {
            return success(MsgConstant.LOGIN_SUCCESS);
        }
        
        // 登录失败从request中获取shiro处理的异常信息。
        String message = MsgConstant.LOGIN_ERRROR;
        String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        LOGGER.info("登陆失败的原因"+error);
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
        }
        return error(message);
    }
    
    /**
     * 生成密码私钥和公钥
     * @param request http请求
     * @return 返回公钥
     */
    @RequestMapping(value = "/security/generateKey.do")
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
