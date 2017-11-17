package com.huishu.ait.service.skyeye.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ConstantKey;
import com.huishu.ait.common.util.HttpUtils;
import com.huishu.ait.common.util.SignatureUtils;
import com.huishu.ait.entity.ChangeInfo;
import com.huishu.ait.entity.SearchTrack;
import com.huishu.ait.repository.skyeye.ChangeRepository;
import com.huishu.ait.repository.skyeye.SearchTrackRepository;
import com.huishu.ait.security.Digests;
import com.huishu.ait.security.Encodes;
import com.huishu.ait.service.company.CompanyService;
import com.huishu.ait.service.garden.GardenService;
import com.huishu.ait.service.skyeye.SkyeyeService;

@Service
public class SkyeyeServiceImpl implements SkyeyeService {

	@Autowired
	private SearchTrackRepository searchTrackRepository;
	private static final String ENCODE = "UTF-8";

	@Autowired
	private ChangeRepository changeRepository;
	@Autowired
	private GardenService gardenService;
	@Autowired
	private CompanyService companyService;
	private Cache<String, String> cache;

	private static Logger logger = LoggerFactory.getLogger(SkyeyeServiceImpl.class);

	public SkyeyeServiceImpl(EhCacheManager ehCacheManager) {
		this.cache = ehCacheManager.getCache("oauth2-cache");
	}

	@Override
	public boolean saveSearchTrack(Collection<SearchTrack> s) {
		Iterable<SearchTrack> it = searchTrackRepository.save(s);
		if (it != null) {
			return true;
		}
		return false;

	}

	@Override
	public JSONObject findSearchTrack(String username) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Page<SearchTrack> page = null;
		try {
			page = searchTrackRepository.findByUsername(username,
					new PageRequest(0, 10, new Sort(Direction.DESC, "updateTime")));
			obj.put("total", page.getTotalElements());
			page.getContent().forEach((st) -> {
				arr.add(st);
			});
			obj.put("items", arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean saveChangeInfo(Collection<ChangeInfo> s) {
		Iterable<ChangeInfo> it = changeRepository.save(s);
		if (it != null) {
			return true;
		}
		return false;

	}

	@Override
	public List<Integer> findChangeId(String companyName) {
		return changeRepository.findChangeId(companyName);

	}

	@Override
	public String loginOpenEye(String userName) {
		String redirectUri = null;
		try {
			String accessToken = getToken();
			Map<String, String> params = new LinkedHashMap<>();
			params.put(ConstantKey.DEFAULT_USERNAME_PARAM, userName);
			params.put(ConstantKey.DEFAULT_REDIRECT_PARAM, ConstantKey.OAUTH_CLIENT_REDIRECT_URI);
			params.put(ConstantKey.DEFAULT_AUTH_ID_PARAM, ConstantKey.OAUTH_AUTH_ID);
			params.put(ConstantKey.DEFAULT_REDIRECT_ID_PARAM, ConstantKey.OAUTH_CLIENT_REDIRECT_URI_ID);
			String sign = getSign(params, accessToken);
			Map<String, String> uriParams = new LinkedHashMap<>();
			uriParams.put("username", userName);
			uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
			uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
			uriParams.put("redirect_uri", ConstantKey.OAUTH_CLIENT_REDIRECT_URI);
			uriParams.put("redirect_uri_id", ConstantKey.OAUTH_CLIENT_REDIRECT_URI_ID);
			String responseBody = HttpUtils.sendGet(ConstantKey.LOGIN_URI, uriParams);
			JSONObject obj = new JSONObject();
			try {
				obj = JSONObject.parseObject(responseBody);
			} catch (JSONException e) {
				obj.put(ConstantKey.INVALID_SPECIAL, ConstantKey.OPENEYE_WARN_TOKEN_461);
			}
			if (ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL))
					|| ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))) {
				cache.remove("accessToken");
				accessToken = getToken();
				sign = getSign(params, accessToken);
				uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
			}
			redirectUri = HttpUtils.getParamConcat(ConstantKey.LOGIN_URI, uriParams);
			logger.info("重定向到天眼查的地址:" + redirectUri);
		} catch (CacheException e) {
			logger.info(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.info(e.getMessage());
		} catch (IOException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return redirectUri;
	}

	@Override
	public void getChangeInfo() {
		List<String> names = gardenService.findAll();
		for (String userpark : names) {
			List<String> cnames = companyService.findCname(userpark);
			try {
				String accessToken = getToken();
				Map<String, String> params = new LinkedHashMap<>();
				params.put("authId", ConstantKey.OAUTH_AUTH_ID);
				params.put("pn", "1");
				params.put("ps", "10");
				for (String name : cnames) {
					params.put("name", name);
					String sign = getSign(params, accessToken);
					Map<String, String> uriParams = new LinkedHashMap<>();
					uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
					uriParams.put("name", URLEncoder.encode(name, ENCODE));
					uriParams.put("pn", "1");
					uriParams.put("ps", "20");
					uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
					String responseBody = HttpUtils.sendGet(ConstantKey.CHANGE_INFO, uriParams);
					JSONObject obj = JSONObject.parseObject(responseBody);
					JSONObject data = new JSONObject();
					JSONArray save = new JSONArray();
					if (ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL))
							|| ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))) {
						cache.remove("accessToken");
						accessToken = getToken();
						sign = getSign(params, accessToken);
						uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
						responseBody = HttpUtils.sendGet(ConstantKey.CHANGE_INFO, uriParams);
						logger.info("responseBody:" + responseBody);
						obj = JSONObject.parseObject(responseBody);
						data = obj.getJSONObject("data");
						save = data.getJSONArray("result");
					}
					data = obj.getJSONObject("data");
					if (data == null) {
						continue;
					}
					save = data.getJSONArray("result");
					List<ChangeInfo> list = new ArrayList<ChangeInfo>();
					save.forEach((change) -> {
						ChangeInfo info = JSON.parseObject(change.toString(), ChangeInfo.class);
						List<Integer> findChangeId = findChangeId(name);
						String id = getGeneratedId(info);
						if (!findChangeId.contains(id)) {
							info.setCompany(name);
							info.setId(id);
							info.setDr(0);
							info.setPark(userpark);
							info.setTag("企业");
							list.add(info);
						}
					});
					saveChangeInfo(list);
				}
			} catch (Exception e) {
				logger.error("获取信息变更预警失败!", e.getMessage());
			}
		}
	}

	@Override
	public String getCompanyDetail(String userName, String name) {
		String redirectUri = null;
		try {
			String accessToken = getToken();
			Map<String, String> params = new LinkedHashMap<>();
			params.put("authId", ConstantKey.OAUTH_AUTH_ID);
			params.put("name", name);
			String sign = getSign(params, accessToken);
			Map<String, String> uriParams = new LinkedHashMap<>();
			uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
			uriParams.put("name", URLEncoder.encode(name, ENCODE));
			uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
			String responseBody = HttpUtils.sendGet(ConstantKey.GID, uriParams);
			JSONObject obj = JSONObject.parseObject(responseBody);
			if (ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL))
					|| ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))) {
				cache.remove("accessToken");
				accessToken = getToken();
				sign = getSign(params, accessToken);
				uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
				responseBody = HttpUtils.sendGet(ConstantKey.GID, uriParams);
				obj = JSONObject.parseObject(responseBody);
			}
			String id = obj.getString("data");
			Map<String, String> paramsLogin = new LinkedHashMap<>();
			paramsLogin.put(ConstantKey.DEFAULT_USERNAME_PARAM, userName);
			paramsLogin.put(ConstantKey.DEFAULT_REDIRECT_PARAM, ConstantKey.OAUTH_CLIENT_REDIRECT_URI);
			paramsLogin.put(ConstantKey.DEFAULT_AUTH_ID_PARAM, ConstantKey.OAUTH_AUTH_ID);
			paramsLogin.put(ConstantKey.DEFAULT_REDIRECT_ID_PARAM,
					ConstantKey.OAUTH_CLIENT_REDIRECT_URI_ID + "company/" + id + "/icinfo");
			String sign2 = getSign(paramsLogin, accessToken);
			Map<String, String> uriParams2 = new LinkedHashMap<>();
			uriParams2.put("username", userName);
			uriParams2.put("sign", URLEncoder.encode(sign2, ENCODE));
			uriParams2.put("authId", ConstantKey.OAUTH_AUTH_ID);
			uriParams2.put("redirect_uri", ConstantKey.OAUTH_CLIENT_REDIRECT_URI);
			uriParams2.put("redirect_uri_id", ConstantKey.OAUTH_CLIENT_REDIRECT_URI_ID + "company/" + id + "/icinfo");
			redirectUri = HttpUtils.getParamConcat(ConstantKey.LOGIN_URI, uriParams2);
		} catch (CacheException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redirectUri;
	}

	@Override
	public JSONObject getAttentionGroup(String userName) {
		JSONObject obj = new JSONObject();
		try {
			String accessToken = getToken();
			Map<String, String> params = new LinkedHashMap<>();
			params.put("authId", ConstantKey.OAUTH_AUTH_ID);
			params.put("username", userName);
			String sign = getSign(params, accessToken);
			Map<String, String> uriParams = new LinkedHashMap<>();
			uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
			uriParams.put("username", userName);
			uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
			String responseBody = HttpUtils.sendGet(ConstantKey.ATTENTION_GROUP, uriParams);
			obj = JSONObject.parseObject(responseBody);
			if (ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL))
					|| ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))) {
				cache.remove("accessToken");
				accessToken = getToken();
				sign = getSign(params, accessToken);
				uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
				responseBody = HttpUtils.sendGet(ConstantKey.ATTENTION_GROUP, uriParams);
				obj = JSONObject.parseObject(responseBody);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			obj.put("data", "请求出错");
		}
		return obj;
	}

	@Override
	public JSONObject getCompanyByGroup(String userName, String tags, String ps, String pn) {
		JSONObject obj = new JSONObject();
		try {
			String accessToken = getToken();
			Map<String, String> params = new LinkedHashMap<>();
			params.put("username", userName);
			params.put("authId", ConstantKey.OAUTH_AUTH_ID);
			params.put("tags", tags);
			params.put("pn", pn);
			params.put("ps", ps);
			String sign = getSign(params, accessToken);
			Map<String, String> uriParams = new LinkedHashMap<>();
			uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
			uriParams.put("username", userName);
			uriParams.put("tags", tags);
			uriParams.put("pn", pn);
			uriParams.put("ps", ps);
			uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
			String responseBody = HttpUtils.sendGet(ConstantKey.GID_COMPANY, uriParams);
			obj = JSONObject.parseObject(responseBody);
			if (ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL))
					|| ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))) {
				cache.remove("accessToken");
				accessToken = getToken();
				sign = getSign(params, accessToken);
				uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
				responseBody = HttpUtils.sendGet(ConstantKey.GID_COMPANY, uriParams);
				obj = JSONObject.parseObject(responseBody);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			obj.put("data", "查询出错");
		}
		return obj;
	}

	@Override
	public JSONObject getSearchTrack(String userName) {
		JSONObject returnData = new JSONObject();
		try {
			String accessToken = getToken();
			Map<String, String> params = new LinkedHashMap<>();
			params.put("username", userName);
			params.put("authId", ConstantKey.OAUTH_AUTH_ID);
			String sign = getSign(params, accessToken);
			Map<String, String> uriParams = new LinkedHashMap<>();
			uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
			uriParams.put("username", userName);
			uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
			String responseBody = HttpUtils.sendGet(ConstantKey.SEARCH_TRACK, uriParams);
			JSONObject obj = JSONObject.parseObject(responseBody);
			if (ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL))
					|| ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))) {
				cache.remove("accessToken");
				accessToken = getToken();
				sign = getSign(params, accessToken);
				uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
				responseBody = HttpUtils.sendGet(ConstantKey.SEARCH_TRACK, uriParams);
				obj = JSONObject.parseObject(responseBody);
			}
			JSONObject data = obj.getJSONObject("data");
			JSONArray items = data.getJSONArray("items");
			List<SearchTrack> list = new ArrayList<SearchTrack>();
			items.forEach((st) -> {
				SearchTrack searchTrack = JSON.parseObject(st.toString(), SearchTrack.class);
				list.add(searchTrack);
			});
			saveSearchTrack(list);
			returnData = findSearchTrack(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnData;
	}

	@Override
	public String getSearchList(String userName, String name) {
		String redirectUri = null;
		try {
			String accessToken = getToken();
			Map<String, String> params = new LinkedHashMap<>();
			params.put("authId", ConstantKey.OAUTH_AUTH_ID);
			params.put("key", name);
			params.put("username", userName);
			params.put("rememberMe", "true");
			String sign = getSign(params, accessToken);
			Map<String, String> uriParams = new LinkedHashMap<>();
			uriParams.put("authId", ConstantKey.OAUTH_AUTH_ID);
			uriParams.put("key", URLEncoder.encode(name, ENCODE));
			uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
			String responseBody = HttpUtils.sendGet(ConstantKey.SEARCH_LIST, uriParams);
			JSONObject obj = new JSONObject();
			try {
				obj = JSONObject.parseObject(responseBody);
			} catch (JSONException e) {
				obj.put(ConstantKey.INVALID_SPECIAL, ConstantKey.OPENEYE_WARN_TOKEN_461);
			}
			if (ConstantKey.OPENEYE_WARN_TOKEN_461.equals(obj.getString(ConstantKey.INVALID_SPECIAL))
					|| ConstantKey.OPENEYE_WARN_TOKEN_460.equals(obj.getString(ConstantKey.INVALID_SPECIAL))) {
				cache.remove("accessToken");
				accessToken = getToken();
				sign = getSign(params, accessToken);
				uriParams.put("sign", URLEncoder.encode(sign, ENCODE));
			}
			redirectUri = HttpUtils.getParamConcat(ConstantKey.SEARCH_LIST, uriParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redirectUri;
	}

	@Override
	public String getToken(String code) throws Exception {
		String accessToken = null;
		try {
			Map<String, String> params = new HashMap<>();
			params.put("code", code);
			params.put("client_id", ConstantKey.OAUTH_CLIENT_ID);
			params.put("client_secret", ConstantKey.OAUTH_CLIENT_SECRET);
			params.put("grant_type", ConstantKey.OAUTH_CLIENT_GRANT_TYPE);
			params.put("redirect_uri", ConstantKey.OAUTH_CLIENT_CALLBACK);
			String responseBody = HttpUtils.sendPost(ConstantKey.OAUTH_CLIENT_ACCESS_TOKEN, params);
			String res = StringEscapeUtils.unescapeJava(responseBody);
			logger.info("responseBody:" + res);
			res = res.substring(1, res.length() - 1);
			JSONObject.parseObject(res);
			JSONObject parse = JSONObject.parseObject(res);
			accessToken = parse.getString("access_token");
			cache.put("accessToken", accessToken);
			logger.info("获取到的token值是:" + accessToken);
		} catch (Exception e) {
			logger.debug("获取天眼查token失败!", e.getMessage());
			throw e;
		}
		return accessToken;
	}

	private String getToken() throws IOException {
		if (cache.get("accessToken") == null) {
			logger.info("缓存中数据被清空,重新获取token");
			Map<String, String> params = new HashMap<>();
			params.put("client_id", ConstantKey.OAUTH_CLIENT_ID);
			params.put("response_type", "code");
			params.put("redirect_uri", ConstantKey.OAUTH_CLIENT_CALLBACK);
			HttpUtils.sendGet(ConstantKey.OAUTH_CLIENT_ACCESS_CODE, params);
		}
		logger.info("缓存中的accessToken:" + cache.get("accessToken"));
		return (String) cache.get("accessToken");
	}

	/**
	 * 签名程序
	 * 
	 * @throws IOException
	 * 
	 * @throws Exception
	 * 
	 */
	private String getSign(Map<String, String> params, String accessToken) throws Exception {
		return SignatureUtils.generate("POST", params, accessToken);
	}

	private String getGeneratedId(ChangeInfo info) {
		byte[] hashPassword = Digests.sha1(info.toString().getBytes(), null, Encodes.HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}

}
