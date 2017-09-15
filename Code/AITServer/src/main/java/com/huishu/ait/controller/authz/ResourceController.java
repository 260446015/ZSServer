package com.huishu.ait.controller.authz;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ConstantKey;
import com.huishu.ait.common.util.HttpUtils;
import com.huishu.ait.common.util.SignatureUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.ChangeInfo;
import com.huishu.ait.entity.SearchTrack;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.company.CompanyService;
import com.huishu.ait.service.skyeye.SkyeyeService;

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
	@Autowired
	private SkyeyeService skyeyeService;
	@Autowired
	private CompanyService companyService;

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
		String responseBody = HttpUtils.sendGet(ConstantKey.LOGIN_URI, uriParams);
		JSONObject obj = new JSONObject();
		try{
			obj = JSONObject.parseObject(responseBody);
		}catch(JSONException e){
			obj.put(ConstantKey.INVALID_SPECIAL, ConstantKey.OPENEYE_WARN_TOKEN_461);
		}
		if(ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL)) || ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))){
			cache.remove("accessToken");
			accessToken = getToken();
			sign = getSign(params, accessToken);
			uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
		}
		String redirectUri = HttpUtils.getParamConcat(ConstantKey.LOGIN_URI, uriParams);
		logger.info("重定向到天眼查的地址:"+redirectUri);
		response.sendRedirect(redirectUri);
		
	}
	@RequestMapping(value="/getChangeInfo.json",method=RequestMethod.GET)
	public AjaxResult getChangeInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userpark = getUserPark();
		List<String> cnames = companyService.findCname(userpark);
		String accessToken = getToken();
		Map<String, String> params = new LinkedHashMap<>();
		params.put("authId", ConstantKey.OAUTH_AUTH_ID);
		params.put("pn", "1");
		params.put("ps", "10");
		JSONArray arr = new JSONArray();
		try{
			for (String name : cnames) {
				params.put("name", name);
				String sign = getSign(params, accessToken);
				Map<String, String> uriParams = new LinkedHashMap<>();
				uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
				uriParams.put("name", URLEncoder.encode(name,ENCODE));
				uriParams.put("pn", "1");
				uriParams.put("ps", "10");
				uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
				String responseBody = HttpUtils.sendGet(ConstantKey.CHANGE_INFO, uriParams);
				JSONObject obj = JSONObject.parseObject(responseBody);
				JSONObject data = new JSONObject();
				JSONArray save = new JSONArray();
				if(ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL)) || ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))){
					cache.remove("accessToken");
					accessToken = getToken();
					sign = getSign(params, accessToken);
					uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
					responseBody = HttpUtils.sendGet(ConstantKey.CHANGE_INFO, uriParams);
					logger.info("responseBody:"+responseBody);
					obj = JSONObject.parseObject(responseBody);
					data = obj.getJSONObject("data");
					save = data.getJSONArray("result");
					arr.add(save);
				}
				data = obj.getJSONObject("data");
				if(data == null){
					continue;
				}
				save = data.getJSONArray("result");
				List<ChangeInfo> list = new ArrayList<ChangeInfo>();
				save.forEach((change)->{
					ChangeInfo info = JSON.parseObject(change.toString(), ChangeInfo.class);
					info.setCompany(name);
					info.setId(info.toString().hashCode());
					info.setDr(0);
					info.setPark(userpark);
					info.setTag("企业");
					list.add(info);
				});
				skyeyeService.saveChangeInfo(list);
				arr.add(save);
			}
		}catch(Exception e){
			logger.error("获取信息变更预警失败!",e.getMessage());
		}
		return success(arr);
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
		if(ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL)) || ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))){
			cache.remove("accessToken");
			accessToken = getToken();
			sign = getSign(params, accessToken);
			uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
			responseBody = HttpUtils.sendGet(ConstantKey.GID, uriParams);
			obj = JSONObject.parseObject(responseBody);
		}
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
		if(ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL)) || ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))){
			cache.remove("accessToken");
			accessToken = getToken();
			sign = getSign(params, accessToken);
			uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
			responseBody = HttpUtils.sendGet(ConstantKey.ATTENTION_GROUP, uriParams);
			obj = JSONObject.parseObject(responseBody);
		}
		return success(obj.get("data"));
	}
	/**
	 * 获取用户关注企业分组下的所有企业信息
	 */
	@RequestMapping(value="/getCompanyByGroup.json",method=RequestMethod.GET)
	public AjaxResult getCompanyByGroup(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tags = request.getParameter(ConstantKey.DEFAULT_TAGS_PARAM);
		String ps = request.getParameter(ConstantKey.DEFAULT_PS_PARAM);
		String pn = request.getParameter(ConstantKey.DEFAULT_PN_PARAM);
		if(StringUtil.isEmpty(tags) || StringUtil.isEmpty(ps) || StringUtil.isEmpty(pn)){
			throw new Exception("tags,ps,pn can not be null");
		}
		String accessToken = getToken();
		Map<String, String> params = new LinkedHashMap<>();
		params.put("username", getCurrentShiroUser().getLoginName());
		params.put("authId", ConstantKey.OAUTH_AUTH_ID);
		params.put("tags", tags);
		params.put("pn", pn);
		params.put("ps", ps);
		String sign = getSign(params, accessToken);
		Map<String, String> uriParams = new LinkedHashMap<>();
		uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
		uriParams.put("username", getCurrentShiroUser().getLoginName());
		uriParams.put("tags", tags);
		uriParams.put("pn", pn);
		uriParams.put("ps", ps);
		uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
		String responseBody = HttpUtils.sendGet(ConstantKey.GID_COMPANY, uriParams);
		JSONObject obj = JSONObject.parseObject(responseBody);
		if(ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL)) || ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))){
			cache.remove("accessToken");
			accessToken = getToken();
			sign = getSign(params, accessToken);
			uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
			responseBody = HttpUtils.sendGet(ConstantKey.GID_COMPANY, uriParams);
			obj = JSONObject.parseObject(responseBody);
		}
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
		if(ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL)) || ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))){
			cache.remove("accessToken");
			accessToken = getToken();
			sign = getSign(params, accessToken);
			uriParams.put("sign", URLEncoder.encode(sign,ENCODE));
			responseBody = HttpUtils.sendGet(ConstantKey.SEARCH_TRACK, uriParams);
			obj = JSONObject.parseObject(responseBody);
		}
		JSONObject data = obj.getJSONObject("data");
		JSONArray items = data.getJSONArray("items");
		List<SearchTrack> list = new ArrayList<SearchTrack>();
		items.forEach((st)->{
			SearchTrack searchTrack = JSON.parseObject(st.toString(), SearchTrack.class);
			list.add(searchTrack);
		});
		skyeyeService.saveSearchTrack(list);
		JSONObject returnData = skyeyeService.findSearchTrack();
		return success(returnData);
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