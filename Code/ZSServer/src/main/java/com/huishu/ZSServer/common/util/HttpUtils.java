package com.huishu.ZSServer.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年11月1日下午3:14:38
 * @description 发送http请求的工具类
 * @version
 */
public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	public static String sendHttpGet(String spec, Map<String, String> params) throws IOException {
		spec = spec + "?" + assembling(params);
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(spec);
		get.setHeader("Authorization", ConstansKey.OPENEYES_TOKEN);
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

	public static String assembling(Map<String, String> params) {
		StringBuilder sb = new StringBuilder();
		Set<Entry<String, String>> entrySet = params.entrySet();
		for (Iterator<Entry<String, String>> iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<String, String> entry = iterator.next();
			String name = entry.getKey();
			String value = entry.getValue();
			sb.append(name).append("=").append(value).append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
