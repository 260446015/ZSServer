package com.huishu.ait.controller.authz;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ConstantKey;
import com.huishu.ait.common.util.HttpUtils;
import com.huishu.ait.common.util.SignatureUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author yindawei 
 * @date 2017年9月13日上午9:17:56
 * @description 本地访问天眼查的Controller,先获取token,再生成签名
 * @version
 */
@RestController
@RequestMapping("/apis/oauth")
public class ResourceController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ResourceController.class);
	private static final String ENCODE = "UTF-8";

	private Cache cache;

	@Autowired
	public ResourceController(EhCacheManager ehCacheManager) {
		this.cache = ehCacheManager.getCache("oauth2-cache");
	}

	@RequestMapping(value="/loginOpenEye.json",method=RequestMethod.GET)
	public void loginOpenEye(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String accessToken = getToken();
		Map<String, String> params = new LinkedHashMap<>();
		params.put(ConstantKey.DEFAULT_USERNAME_PARAM, getCurrentShiroUser().getLoginName());
		params.put(ConstantKey.DEFAULT_REDIRECT_PARAM, ConstantKey.OAUTH_CLIENT_REDIRECT_URI);
		params.put(ConstantKey.DEFAULT_AUTH_ID_PARAM, ConstantKey.OAUTH_AUTH_ID);
		params.put(ConstantKey.DEFAULT_REDIRECT_ID_PARAM, ConstantKey.OAUTH_CLIENT_REDIRECT_URI_ID);
		String sign = getSign(params, accessToken);
		Map<String, String> uriParams = new LinkedHashMap<>();
		uriParams.put("username", getCurrentShiroUser().getLoginName());
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
		String accessToken = getToken();
		Map<String, String> params = new LinkedHashMap<>();
		params.put("authId", ConstantKey.OAUTH_AUTH_ID);
		params.put("name",  name);
		String sign = getSign(params, accessToken);
		Map<String, String> uriParams = new LinkedHashMap<>();
		uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
		uriParams.put("name", URLEncoder.encode(name,ENCODE));
		uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
		String responseBody = HttpUtils.sendGet(ConstantKey.GID, uriParams);
		JSONObject obj = JSONObject.parseObject(responseBody);
		String id = obj.getString("data");
		Map<String, String> paramsLogin = new LinkedHashMap<>();
		paramsLogin.put(ConstantKey.DEFAULT_USERNAME_PARAM, getCurrentShiroUser().getLoginName());
		paramsLogin.put(ConstantKey.DEFAULT_REDIRECT_PARAM, ConstantKey.OAUTH_CLIENT_REDIRECT_URI);
		paramsLogin.put(ConstantKey.DEFAULT_AUTH_ID_PARAM, ConstantKey.OAUTH_AUTH_ID);
		paramsLogin.put(ConstantKey.DEFAULT_REDIRECT_ID_PARAM, ConstantKey.OAUTH_CLIENT_REDIRECT_URI_ID+"company/"+id+"/icinfo");
		String sign2 = getSign(paramsLogin, accessToken);
		Map<String, String> uriParams2 = new LinkedHashMap<>();
		uriParams2.put("username", getCurrentShiroUser().getLoginName());
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
		String accessToken = getToken();
		Map<String, String> params = new LinkedHashMap<>();
		params.put("authId", ConstantKey.OAUTH_AUTH_ID);
		params.put("username", getCurrentShiroUser().getLoginName());
		String sign = getSign(params, accessToken);
		Map<String, String> uriParams = new LinkedHashMap<>();
		uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
		uriParams.put("username", getCurrentShiroUser().getLoginName());
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
		String tags = request.getParameter(ConstantKey.DEFAULT_TAGS_PARAM);
		if(StringUtil.isEmpty(tags)){
			throw new Exception("tags can not be null");
		}
		String accessToken = getToken();
		Map<String, String> params = new LinkedHashMap<>();
		params.put("username", getCurrentShiroUser().getLoginName());
		params.put("authId", ConstantKey.OAUTH_AUTH_ID);
		params.put("tags", tags);
		params.put("pn", "1");
		params.put("ps", "10");
		String sign = getSign(params, accessToken);
		Map<String, String> uriParams = new LinkedHashMap<>();
		uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
		uriParams.put("username", getCurrentShiroUser().getLoginName());
		uriParams.put("tags", tags);
		uriParams.put("pn", "1");
		uriParams.put("ps", "10");
		uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
		String responseBody = HttpUtils.sendGet(ConstantKey.GID_COMPANY, uriParams);
		JSONObject obj = JSONObject.parseObject(responseBody);
		return success(obj.get("data"));
	}
	/**
	 * 获取用户查询记录
	 */
	@RequestMapping(value="/getSearchTrack.json",method=RequestMethod.GET)
	public AjaxResult getSearchTrack(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String accessToken = getToken();
		Map<String, String> params = new LinkedHashMap<>();
		params.put("username", getCurrentShiroUser().getLoginName());
		params.put("authId", ConstantKey.OAUTH_AUTH_ID);
		String sign = getSign(params, accessToken);
		Map<String, String> uriParams = new LinkedHashMap<>();
		uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
		uriParams.put("username", getCurrentShiroUser().getLoginName());
		uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
		String responseBody = HttpUtils.sendGet(ConstantKey.SEARCH_TRACK, uriParams);
		JSONObject obj = JSONObject.parseObject(responseBody);
//		JSONObject data = obj.getJSONObject("data");
//		JSONArray items = data.getJSONArray("items");
//		List<SearchTrack> list = new ArrayList<SearchTrack>();
//		items.forEach((st)->{
//			SearchTrack searchTrack = JSON.parseObject(st.toString(), SearchTrack.class);
//			list.add(searchTrack);
//			SearchTrack st2 = JSONObject.parseObject(st, SearchTrack.class);
//		});
//		skyeyeService.saveSearchTrack(list);
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
			logger.info("缓存中数据被清空,重新获取token");
			Map<String, String> params = new HashMap<>();
			params.put("client_id", ConstantKey.OAUTH_CLIENT_ID);
			params.put("response_type", "code");
			params.put("redirect_uri", ConstantKey.OAUTH_CLIENT_CALLBACK);
			HttpUtils.sendGet(ConstantKey.OAUTH_CLIENT_ACCESS_CODE, params);
		}
		logger.info("缓存中的accessToken:"+cache.get("accessToken"));
		return (String) cache.get("accessToken");
	}
	
	private String getSign(Map<String, String> params, String accessToken) throws Exception{
		return SignatureUtils.generate("POST", params, accessToken);
	}

}