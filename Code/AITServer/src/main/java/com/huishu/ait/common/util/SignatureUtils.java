package com.huishu.ait.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
/**
 * <p>类: SignatureUtils </p>
 * <p>描述:  </p>
 * @author wangxw
 * @date 创建时间：2016年8月5日 下午6:37:48
 * @version 1.0
 */
public class SignatureUtils {

    private final static String CHARSET_UTF8 = "utf8";
    private final static String ALGORITHM = "UTF-8";
    private final static String SEPARATOR = "&";

    public static Map<String, String> splitQueryString(String url)
            throws URISyntaxException, UnsupportedEncodingException {
        URI uri = new URI(url);
        String query = uri.getQuery();
        final String[] pairs = query.split("&");
        TreeMap<String, String> queryMap = new TreeMap<String, String>();
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? pair.substring(0, idx) : pair;
            if (!queryMap.containsKey(key)) {
                queryMap.put(key, URLDecoder.decode(pair.substring(idx + 1), CHARSET_UTF8));
            }
        }
        return queryMap;
    }

    public static String generate(String method, Map<String, String> parameter,
            String accessKeySecret) throws Exception {
        String signString = generateSignString(method, parameter);
        byte[] signBytes = hmacSHA1Signature(accessKeySecret + "&", signString);
        String signature = newStringByBase64(signBytes);
        System.out.println("signature---"+signature);
        if ("POST".equals(method))
            return signature;
        return URLEncoder.encode(signature, "UTF-8");

    }

    public static String generateSignString(String httpMethod, Map<String, String> parameter)
            throws IOException {
        TreeMap<String, String> sortParameter = new TreeMap<String, String>();
        sortParameter.putAll(parameter);

        String canonicalizedQueryString = UrlUtil.generateQueryString(sortParameter, true);
        if (null == httpMethod) {
            throw new RuntimeException("httpMethod can not be empty");
        }

        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(httpMethod).append(SEPARATOR);
        stringToSign.append(percentEncode("/")).append(SEPARATOR);
        stringToSign.append(percentEncode(canonicalizedQueryString));

        return stringToSign.toString();
    }

    public static String percentEncode(String value) {
        try {
            return value == null ? null : URLEncoder.encode(value, CHARSET_UTF8)
                    .replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (Exception e) {
        }
        return "";
    }

    public static byte[] hmacSHA1Signature(String secret, String baseString)
            throws Exception {
        if (StringUtils.isEmpty(secret)) {
            throw new IOException("secret can not be empty");
        }
        if (StringUtils.isEmpty(baseString)) {
            return null;
        }
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), ALGORITHM);
        mac.init(keySpec);
        return mac.doFinal(baseString.getBytes(CHARSET_UTF8));
    }

    public static String newStringByBase64(byte[] bytes)
            throws UnsupportedEncodingException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        return new String(Base64.encodeBase64(bytes, false), CHARSET_UTF8);
    }

    public static void main(String[] args) {
    	Map<String,String> params = new HashMap<String, String>();
		String random = System.currentTimeMillis()+""+new Random().nextInt(9999);
    	params.put("Version", "v2");
    	params.put("AccessKeyId", "aS2nIkFRhZnFj9Hy");
    	params.put("SignatureMethod", "HMAC－SHA1");
    	params.put("SignatureVersion", "1.0");
    	params.put("SignatureNonce", random);
    	params.put("Timestamp", new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss'Z'").format(new Date()));
    	params.put("query", "config=format:json,start:0,hit:20&&query=default:'的'&&filteourcer=s=\"toutiao\"");
    	params.put("index_name", "company_public_msg");
    	params.put("format", "fulljson");
    	params.put("fetch_fields", "id;title;abstracts;cat;source;url");
    	params.put("scroll", "1d");
    	params.put("search_type", "scan");
        byte[] signBytes;
        try {
        	String requestParamsStr = UrlUtil.generateQueryString(params, true);
            signBytes = SignatureUtils.hmacSHA1Signature("8dYxP72CnYfzJySgnO79VwwKq0Aeqk" + "&", requestParamsStr);
            String signature = SignatureUtils.newStringByBase64(signBytes);
            System.out.println("http://opensearch-cn-beijing.aliyuncs.com/search?"+requestParamsStr+"&Signature="+signature);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
