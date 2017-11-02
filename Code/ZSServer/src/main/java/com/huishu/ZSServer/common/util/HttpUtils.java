package com.huishu.ZSServer.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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

	/**
	 * 当 http状态码
	 * <ol>
	 * <li>等于200：返回接受的到的数据</li>
	 * <li>不等于200：返回当前http状态码</li>
	 * <li>其它情形：返回 null</li>
	 * </ol>
	 * 
	 * @param conn
	 *            HttpURLConnection 对象
	 * @return 调用结果
	 */
	public static String result(HttpURLConnection conn) {
		StringBuilder sb = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			if (200 == conn.getResponseCode()) {
				is = conn.getInputStream();
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				sb = new StringBuilder();
				String line = br.readLine();
				while (line != null) {
					sb.append(line);
					line = br.readLine();
				}
				return sb.toString();
			} else {
				return conn.getResponseCode() + "";
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}

		return null;
	}

	/**
	 * 
	 * 禁止 SSL校验
	 */
	public static void disableSslVerification() {
		try {
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (KeyManagementException e) {
			logger.error(e.getMessage());
		}
	}

}
