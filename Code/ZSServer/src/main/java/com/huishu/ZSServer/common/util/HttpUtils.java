package com.huishu.ZSServer.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;

/**
 * 针对天眼查HTTP请求工具类
 * 
 * @author yindq
 * @date 2017年11月2日
 */
public class HttpUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	public static JSONObject sendGet(String spec, List<NameValuePair> params){
        String url = spec + "?" + URLEncodedUtils.format(params,"UTF-8");  
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		get.setHeader("Authorization", KeyConstan.OPENEYES_TOKEN);
		InputStream in = null;
		try {
			HttpResponse response = client.execute(get);
			if(response.getStatusLine().getStatusCode() == 200){  
				in = response.getEntity().getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String str = "";
				StringBuffer sb = new StringBuffer();
				while ((str = reader.readLine()) != null) {
					sb.append(str);
				}
				return (JSONObject)JSONObject.parse(sb.toString());
			}else{ 
            	get.abort();  
            	return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;  
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}
	
	public static void sendGet(String spec) throws IOException {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(spec);
		get.setHeader("Authorization", KeyConstan.OPENEYES_TOKEN);
		InputStream in = null;
		try {
			client.execute(get);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	public static String sendHttpGet(String spec, Map<String, Object> params) throws IOException {
		spec = spec + "?" + assembling(params);
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(spec);
		get.setHeader("Authorization", KeyConstan.OPENEYES_TOKEN);
		InputStream in = null;
		try {
			HttpResponse response = client.execute(get);
			in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String str = "";
			StringBuffer sb = new StringBuffer();
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}
			return sb.toString();
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return null;
	}

	public static String assembling(Map<String, Object> params) {
		StringBuilder sb = new StringBuilder();
		Set<Entry<String, Object>> entrySet = params.entrySet();
		for (Iterator<Entry<String, Object>> iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<String, Object> entry = iterator.next();
			String name = entry.getKey();
			Object value = entry.getValue();
			sb.append(name).append("=").append(value.toString()).append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

}
