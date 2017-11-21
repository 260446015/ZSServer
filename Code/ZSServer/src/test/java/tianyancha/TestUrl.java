package tianyancha;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.HttpUtils;
import com.huishu.ZSServer.service.AbstractService;

public class TestUrl {
	private final String url = "https://open.api.tianyancha.com/services/v3/newopen/findHistoryRongzi.json?name=北京百度网讯科技有限公司";

	@Test
	public void test1() {
		HttpGet get = new HttpGet(url);
		get.setHeader("Authorization", KeyConstan.OPENEYES_TOKEN);
		String result = "";
		try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(get);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
				result.replaceAll("\r", "");// 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
			} else
				get.abort();
		} catch (ClientProtocolException e) {
			result = e.getMessage().toString();
		} catch (IOException e) {
			result = e.getMessage().toString();
		}
		System.out.println(result);
	}

	@Test
	public void test3() {
		String s = "17亿美元19亿融资";
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|\\d+");
		Matcher m = p.matcher(s);
		while (m.find()) {
			System.out.println(m.group());
		}
	}

	@Test
	public void test4() {
		String s = "卧槽年地方荆防颗粒淡饭黄齑丁海峰卡梅伦的烦恼都看见啊发哈看到老师发货的时间开发哈三联地方监考老师";
		System.out.println(s.length() + "===" + s);
		String by = "";
		byte[] midbytes = null;
		try {
			midbytes = s.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (byte b : midbytes) {
			by = by + b;
		}
		System.out.println(midbytes.length + "===" + by);
	}

	@Test
	public void test2() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		NameValuePair pair = new BasicNameValuePair("word", "永达理保独赏芬芳险经纪有限 ");
		params.add(pair);
		JSONObject parse = HttpUtils.sendGet(KeyConstan.URL.SOUSUO,
				params);
		System.out.println(parse);
	}

	@Test
	public void test5() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			/*HttpPost post = new HttpPost(KeyConstan.DISTINGUISH);
			List<NameValuePair> data = new ArrayList<NameValuePair>();
			data.add(new BasicNameValuePair("Image_Data", "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAASABIAAD"));
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(data);
			post.setEntity(new FileEntity(new File("C:/Users/ersan/Pictures/IMG.jpg")));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entitys = response.getEntity();
			if (entitys != null) {
				System.out.println(EntityUtils.toString(entitys));
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testList(){
		List list = new ArrayList<>();
		list.add("a");
		list = (List) list.stream().skip(20).limit(20).collect(Collectors.toList());
		System.out.println(list.size());
	}
}
