package com.huishu.ait.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.Constans;
import com.huishu.ait.common.util.HttpUtils;
import com.huishu.ait.entity.SkyEyeAuthEntity;

/**
 * @author yindawei 
 * @date 2017年8月2日下午4:30:41
 * @description 本类主要实现与天眼查之间接口调用的方法
 * @version 1.0
 */
public abstract class SkyEyeAbstractService extends AbstractService {

	/**
	 * 用于重新获取token令牌的方法
	 */
	private SkyEyeAuthEntity getTokenAuthEntity(){
		boolean hasLock = false;
		//验证hasLock
		if(hasLock){
			//获取锁表示拥有获取token的权限可以调接口，获取认证权限
			String spec = "http://XXXXX";
			Map<String, String> params = new HashMap<String, String>();
			params.put("account", "zkdj");
			params.put("signature", "XXX");//待定
			String result = HttpUtils.sendPost(spec, params);//待定
			
			JSONObject jsonObject = JSONObject.parseObject(result);
			
			String success = jsonObject.getString("success");
			if(success.equals("true")){
				SkyEyeAuthEntity authEntity = jsonObject.getObject("data", SkyEyeAuthEntity.class);
				//这里处理一系列的token值得处理策略
				return authEntity;
			}
			return null;
		}else{
			//待做处理
			return null;
		}
	}
	/**
	 * 【web版】调用天眼查接口的基类
	 */
	protected String getSkyEyeMsg(String spec,Map<String, String> params,HttpServletRequest request,HttpServletResponse response){
		String account = Constans.SKYEYE_ACCOUNT;
		params.put("account", account);
		String accessToken = getAccessToken(request);//这里用来取token值，但是具体存在哪还未定
		if(accessToken == null){
			SkyEyeAuthEntity authEntity = getTokenAuthEntity();
			accessToken = authEntity.getAccessToken();//重新获取token，（还没写）
			Cookie cookie = new Cookie("accessToken", accessToken);
			response.addCookie(cookie);
		}
		params.put("accessToken", accessToken);
		String resultStr = HttpUtils.sendGet(spec, params);
		
		//授权码过期
		if("401".equals(resultStr)){
			SkyEyeAuthEntity entity = getTokenAuthEntity();
			params.put("accessToken", entity.getAccessToken());
			resultStr = HttpUtils.sendGet(spec, params);
		}
		
		JSONObject obj = JSONObject.parseObject(resultStr);
		String result = "";
		String success = obj.getString("success");
		if("true".equals(success)){
			result = obj.getString("data");
		}
		return result;
	}
	/**
	 * @return 返回存储的token值
	 */
	private String getAccessToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if("accessToken".equals(cookies[i].getName())){
				return cookies[i].getValue();
			}
		}
		return null;
		
	}
	/**
	 * 这里想整合一下把service层的调用整合成一个统一的方法
	 */
	public JSONArray sendHttpsRequest(List<String> specList,Map<String, String> params,HttpServletRequest request,HttpServletResponse response){
		JSONArray arr = new JSONArray();
		for (String spec : specList) {
//			String str = spec.substring(spec.lastIndexOf("/"), spec.lastIndexOf("."));
			arr.add(JSONObject.parseObject(getSkyEyeMsg(spec, params, request, response)));
		}
		return arr;
	}
}
