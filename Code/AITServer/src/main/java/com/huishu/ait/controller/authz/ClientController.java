package com.huishu.ait.controller.authz;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ConstantKey;
import com.huishu.ait.common.util.HttpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author yindawei
 * @date 2017年9月11日下午4:40:32
 * @description 客户端获取token
 * @version
 */
@Controller
@RequestMapping("/apis/oauth")
public class ClientController {

	private static Logger logger = LoggerFactory.getLogger(ClientController.class);

	private Cache cache;

	@Autowired
	public ClientController(EhCacheManager ehCacheManager) {
		this.cache = ehCacheManager.getCache("oauth2-cache");
	}

	/*
	 * grant_type：表示使用的授权模式，必选项，此处的值固定为"authorization_code"
	 * code：表示上一步获得的授权码，必选项。 redirect_uri：表示重定向URI，必选项，且必须与A步骤中的该参数值保持一致
	 * client_id：表示客户端ID，必选项
	 */
	/**
	 * 获得令牌
	 * 
	 * @return oauth_callback?code=?
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping(value = "/oauth_callback", method = RequestMethod.GET)
	public void getTokenLogin(HttpServletRequest request, Model model) {
		try {
			String code = request.getParameter(ConstantKey.DEFAULT_CODE_PARAM);
//			oauthAuthzResponse = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
//			String code = oauthAuthzResponse.getCode();
//			OAuthClientRequest oauthClientRequest = OAuthClientRequest
//                    .tokenLocation(ConstantKey.OAUTH_CLIENT_ACCESS_TOKEN)
//                    .setGrantType(GrantType.AUTHORIZATION_CODE)
//                    .setClientId(ConstantKey.OAUTH_CLIENT_ID)
//                    .setClientSecret(ConstantKey.OAUTH_CLIENT_SECRET)
//                    .setRedirectURI(ConstantKey.OAUTH_CLIENT_CALLBACK)
//                    .setCode(code)
//                    .buildQueryMessage();
			// 获取access token
//			OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//			OAuthJSONAccessTokenResponse oAuthResponse = oAuthClient.accessToken(oauthClientRequest,
//					OAuth.HttpMethod.POST);
//			String token = oauthAuthzResponse.getAccessToken();
			Map<String, String> params = new HashMap<>();
			params.put("code", code);
			params.put("client_id", ConstantKey.OAUTH_CLIENT_ID);
			params.put("client_secret", ConstantKey.OAUTH_CLIENT_SECRET);
			params.put("grant_type", ConstantKey.OAUTH_CLIENT_GRANT_TYPE);
			params.put("redirect_uri", ConstantKey.OAUTH_CLIENT_CALLBACK);
			String responseBody = HttpUtils.sendPost(ConstantKey.OAUTH_CLIENT_ACCESS_TOKEN, params);
			String res = StringEscapeUtils.unescapeJava(responseBody);
			logger.info("responseBody:"+res);
			res = res.substring(1, res.length()-1);
			JSONObject.parseObject(res);
			JSONObject parse = JSONObject.parseObject(res);
			String accessToken = parse.getString("access_token");
			cache.put("accessToken", accessToken);
			logger.info("获取到的token值是:" + accessToken);
		} catch (Exception ex) {
			logger.error("getToken OAuthSystemException : " + ex.getMessage());
			model.addAttribute("errorMsg", ex.getMessage());
		}
	}

}
