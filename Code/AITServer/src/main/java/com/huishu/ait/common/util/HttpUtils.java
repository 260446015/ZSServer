package com.huishu.ait.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ssf
 *
 */
public class HttpUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	public static String sendPost(String spec, Map<String, String> params) {
		HttpURLConnection conn = null;
		OutputStream os = null;
		try {
			URL url = new URL(spec);
			conn = (HttpURLConnection) url.openConnection();
			// conn.setRequestProperty("content-type", "text/html");
			// conn.setRequestProperty("Accept-Charset", "utf-8");
			// conn.setRequestProperty("contentType", "utf-8");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			os = conn.getOutputStream();
			os.write(assembling(params).getBytes());
			os.flush();
			os.close();
			return result(conn);
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
		}
		return null;
	}

	public static String sendGet(String spec, Map<String, String> params) throws IOException {
		spec = spec + "?" + assembling(params);
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(spec);
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
			// try {
			// sb.append(name).append("=").append(URLEncoder.encode(value,
			// "UTF-8")).append("&");
			// } catch (UnsupportedEncodingException e) {
			// }
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
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

	public static String getParamConcat(String spec, Map<String, String> params) {
		spec = spec + "?" + assembling(params);
		return spec;
	}

}
