package com.huishu.ait.controller.authz;

import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConstantKey;
import com.huishu.ait.common.util.HttpUtils;
import com.huishu.ait.common.util.SignatureUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Irving on 2014/11/23. OAuth2 资源服务
 */
@RestController
@RequestMapping("/oauth2")
public class ResourceController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ResourceController.class);
	private static final String ENCODE = "UTF-8";

	private Cache cache;

	@Autowired
	public ResourceController(EhCacheManager ehCacheManager) {
		this.cache = ehCacheManager.getCache("oauth2-cache");
	}

	/*@RequestMapping(value="/sendHttpRequest",method=RequestMethod.GET)
	public void sendHttpRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String accessToken = (String) cache.get("accessToken");
		String redirectUri = null;
		String authId = ConstantKey.OAUTH_AUTH_ID;
		String targetUri = (String) cache.get("targetUri");
		String sign = (String) cache.get("sign");
		String name = request.getParameter(ConstantKey.DEFAULT_NAME_PARAM);
		if(targetUri == null){
			targetUri = request.getParameter(ConstantKey.TARGET_URI);
			cache.put("targetUri", targetUri);
		}
		String username = "ydw";
//		String username = getCurrentShiroUser().getLoginName();
		if(accessToken == null){
			Map<String, String> params = new HashMap<>();
			params.put("client_id", ConstantKey.OAUTH_CLIENT_ID);
			params.put("response_type", "code");
			params.put("redirect_uri", ConstantKey.OAUTH_CLIENT_CALLBACK);
			String responseBody = HttpUtils.sendGet(ConstantKey.OAUTH_CLIENT_ACCESS_CODE, params);
			accessToken = (String) cache.get("accessToken");
			logger.info("responseBody:"+responseBody);
		}
//		if(sign == null){
//			sign = getSign(username, accessToken);
//			cache.put("sign", sign);
//		}
		Map<String, String> params = new LinkedHashMap<>();
		params.put("username", username);
		params.put("sign", sign);
		params.put("authId", authId);
		params.put("redirect_uri", ConstantKey.OAUTH_CLIENT_REDIRECT_URI);
		params.put("redirect_uri_id", ConstantKey.OAUTH_CLIENT_REDIRECT_URI_ID);
		if(!StringUtil.isEmpty(name)){
			params.put("name", name);
		}
		redirectUri = HttpUtils.getParamConcat(targetUri, params);
		cache.remove("targetUri");
		logger.info("重定向到天眼查的地址:"+redirectUri);
		response.sendRedirect(redirectUri);
	}*/
	@RequestMapping(value="/loginOpenEye.json",method=RequestMethod.GET)
	public void loginOpenEye(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String accessToken = getToken();
//		String username = getCurrentShiroUser().getLoginName();
		String username = "ydw";
		Map<String, String> params = new LinkedHashMap<>();
		params.put(ConstantKey.DEFAULT_USERNAME_PARAM, username);
		params.put(ConstantKey.DEFAULT_REDIRECT_PARAM, ConstantKey.OAUTH_CLIENT_REDIRECT_URI);
		params.put(ConstantKey.DEFAULT_AUTH_ID_PARAM, ConstantKey.OAUTH_AUTH_ID);
		params.put(ConstantKey.DEFAULT_REDIRECT_ID_PARAM, ConstantKey.OAUTH_CLIENT_REDIRECT_URI_ID);
		String sign = getSign(params, accessToken);
		Map<String, String> uriParams = new LinkedHashMap<>();
		uriParams.put("username", username);
		uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
		uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
		uriParams.put("redirect_uri", ConstantKey.OAUTH_CLIENT_REDIRECT_URI);
		uriParams.put("redirect_uri_id", ConstantKey.OAUTH_CLIENT_REDIRECT_URI_ID);
		String redirectUri = HttpUtils.getParamConcat(ConstantKey.LOGIN_URI, uriParams);
		logger.info("重定向到天眼查的地址:"+redirectUri);
		response.sendRedirect(redirectUri);
		
	}
	@RequestMapping(value="/getChangeInfo.json",method=RequestMethod.GET)
	public void getChangeInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String name = request.getParameter(ConstantKey.DEFAULT_NAME_PARAM);
		if(StringUtil.isEmpty(name)){
			throw new Exception("name can not be null");
		}
//		String username = getCurrentShiroUser().getLoginName();
		String username = "ydw";
		String accessToken = getToken();
		Map<String, String> params = new LinkedHashMap<>();
		params.put("authId", ConstantKey.OAUTH_AUTH_ID);
		params.put("name", name);
		params.put("pn", "1");
		params.put("ps", "10");
		String sign = getSign(params, accessToken);
		Map<String, String> uriParams = new LinkedHashMap<>();
		uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
		uriParams.put("name", URLEncoder.encode(name,ENCODE));
		uriParams.put("pn", "1");
		uriParams.put("ps", "10");
		uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
		String redirectUri = HttpUtils.getParamConcat(ConstantKey.CHANGE_INFO, uriParams);
		response.sendRedirect(redirectUri);
	}
	
	@RequestMapping(value="/getCompanyDetail.json",method=RequestMethod.GET)
	public void getCompanyDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String name = request.getParameter(ConstantKey.DEFAULT_NAME_PARAM);
		if(StringUtil.isEmpty(name)){
			throw new Exception("name can not be null");
		}
//		String username = getCurrentShiroUser().getLoginName();
		String username = "ydw";
		String accessToken = getToken();
		Map<String, String> params = new LinkedHashMap<>();
		params.put("authId", ConstantKey.OAUTH_AUTH_ID);
		params.put("name", name);
		String sign = getSign(params, accessToken);
		Map<String, String> uriParams = new LinkedHashMap<>();
		uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
		uriParams.put("name", URLEncoder.encode(name,ENCODE));
		uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
		String responseBody = HttpUtils.sendGet(ConstantKey.GID, uriParams);
		JSONObject obj = JSONObject.parseObject(responseBody);
		String id = obj.getString("data");
		Map<String, String> paramsLogin = new LinkedHashMap<>();
		paramsLogin.put(ConstantKey.DEFAULT_USERNAME_PARAM, username);
		paramsLogin.put(ConstantKey.DEFAULT_REDIRECT_PARAM, ConstantKey.OAUTH_CLIENT_REDIRECT_URI);
		paramsLogin.put(ConstantKey.DEFAULT_AUTH_ID_PARAM, ConstantKey.OAUTH_AUTH_ID);
		paramsLogin.put(ConstantKey.DEFAULT_REDIRECT_ID_PARAM, ConstantKey.OAUTH_CLIENT_REDIRECT_URI_ID+"company/"+id+"/icinfo");
		String sign2 = getSign(paramsLogin, accessToken);
		Map<String, String> uriParams2 = new LinkedHashMap<>();
		uriParams2.put("username", username);
		uriParams2.put("sign", URLEncoder.encode(sign2,ENCODE));
		uriParams2.put("authId", ConstantKey.OAUTH_AUTH_ID);
		uriParams2.put("redirect_uri", ConstantKey.OAUTH_CLIENT_REDIRECT_URI);
		uriParams2.put("redirect_uri_id", ConstantKey.OAUTH_CLIENT_REDIRECT_URI_ID+"company/"+id+"/icinfo");
		String redirectUri = HttpUtils.getParamConcat(ConstantKey.LOGIN_URI, uriParams2);
		response.sendRedirect(redirectUri);
	}
	
	/**
	 * 获取关注企业分组
	 * @throws Exception 
	 */
	@RequestMapping(value="/getAttentionGroup.json",method=RequestMethod.GET)
	public AjaxResult getAttentionGroup(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		String username = getCurrentShiroUser().getLoginName();
		String username = "ydw";
		String accessToken = getToken();
		Map<String, String> params = new LinkedHashMap<>();
		params.put("authId", ConstantKey.OAUTH_AUTH_ID);
		params.put("username", username);
		String sign = getSign(params, accessToken);
		Map<String, String> uriParams = new LinkedHashMap<>();
		uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
		uriParams.put("username", username);
		uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
		String responseBody = HttpUtils.sendGet(ConstantKey.ATTENTION_GROUP, uriParams);
		JSONObject obj = JSONObject.parseObject(responseBody);
		return success(obj.get("data"));
	}
	/**
	 * 获取用户关注企业分组下的所有企业信息
	 */
	@RequestMapping(value="/getCompanyByGroup.json",method=RequestMethod.GET)
	public AjaxResult getCompanyByGroup(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		String username = getCurrentShiroUser().getLoginName();
		String tags = request.getParameter(ConstantKey.DEFAULT_TAGS_PARAM);
		if(StringUtil.isEmpty(tags)){
			throw new Exception("tags can not be null");
		}
		String username = "ydw";
		String accessToken = getToken();
		Map<String, String> params = new LinkedHashMap<>();
		params.put("username", username);
		params.put("authId", ConstantKey.OAUTH_AUTH_ID);
		params.put("tags", tags);
		params.put("pn", "1");
		params.put("ps", "10");
		String sign = getSign(params, accessToken);
		Map<String, String> uriParams = new LinkedHashMap<>();
		uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
		uriParams.put("username", username);
		uriParams.put("tags", tags);
		uriParams.put("pn", "1");
		uriParams.put("ps", "10");
		uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
		String responseBody = HttpUtils.sendGet(ConstantKey.GID_COMPANY, uriParams);
		JSONObject obj = JSONObject.parseObject(responseBody);
		return success(obj.get("data"));
	}

	/**
	 * 签名程序
	 * @throws IOException 
	 * 
	 * @throws Exception
	 * 
	 */
	private String getToken() throws IOException{
		if(cache.get("accessToken") == null){
			Map<String, String> params = new HashMap<>();
			params.put("client_id", ConstantKey.OAUTH_CLIENT_ID);
			params.put("response_type", "code");
			params.put("redirect_uri", ConstantKey.OAUTH_CLIENT_CALLBACK);
			HttpUtils.sendGet(ConstantKey.OAUTH_CLIENT_ACCESS_CODE, params);
		}
		return (String) cache.get("accessToken");
	}
	
	private String getSign(Map<String, String> params, String accessToken) throws Exception{
		return SignatureUtils.generate("POST", params, accessToken);
	}

}