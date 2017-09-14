package com.huishu.ait.controller.authz;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
		OAuthAuthzResponse oauthAuthzResponse = null;
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
			HttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(ConstantKey.OAUTH_CLIENT_ACCESS_TOKEN);
			List<NameValuePair> list = new ArrayList<>();
			list.add(new BasicNameValuePair("code", code));
			list.add(new BasicNameValuePair("client_id", ConstantKey.OAUTH_CLIENT_ID));
			list.add(new BasicNameValuePair("client_secret", ConstantKey.OAUTH_CLIENT_SECRET));
			list.add(new BasicNameValuePair("grant_type", ConstantKey.OAUTH_CLIENT_GRANT_TYPE));
			list.add(new BasicNameValuePair("redirect_uri", ConstantKey.OAUTH_CLIENT_CALLBACK));
			post.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
			HttpResponse response = client.execute(post);
			InputStream content = response.getEntity().getContent();
			InputStreamReader reader = new InputStreamReader(content);
			BufferedReader br = new BufferedReader(reader);
			StringBuilder sb = new StringBuilder();
			String str = "";
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			String accessToken = sb.toString().substring(sb.toString().indexOf("access_token")+17,sb.toString().indexOf("}", sb.toString().indexOf("access_token"))-2);
//			String strSub = sb.toString().substring(1, sb.toString().length() - 1);
//			strSub = strSub.replace("\\", "");
//			JSONObject parse = JSONObject.parseObject(strSub);
//			String accessToken = parse.getString("access_token");
//			Long expiresIn = parse.getLong("expires_in");
			cache.put("accessToken", accessToken);
			logger.info("获取到的token值是:" + accessToken);
//			logger.info("accessToken: " + accessToken + " expiresIn: " + expiresIn);
		} catch (Exception ex) {
			logger.error("getToken OAuthSystemException : " + ex.getMessage());
			model.addAttribute("errorMsg", ex.getMessage());
		}
	}

}
