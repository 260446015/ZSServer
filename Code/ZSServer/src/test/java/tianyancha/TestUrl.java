package tianyancha;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.HttpUtils;

public class TestUrl {
	private final String url="https://open.api.tianyancha.com/services/v3/newopen/findHistoryRongzi.json?name=北京百度网讯科技有限公司";
	
	@Test
	public void test1(){
		HttpGet get = new HttpGet(url); 
		get.setHeader("Authorization", KeyConstan.OPENEYES_TOKEN);
		String result="";
		try {  
			HttpResponse httpResponse = new DefaultHttpClient().execute(get);
	            if(httpResponse.getStatusLine().getStatusCode() == 200){  
	                HttpEntity httpEntity = httpResponse.getEntity();  
	                result = EntityUtils.toString(httpEntity);
	                result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格    
	            }else   
	            	get.abort();  
	           } catch (ClientProtocolException e) {  
	        	   result = e.getMessage().toString();  
	        } catch (IOException e) {  
	            result = e.getMessage().toString();  
	        }  
	        System.out.println(result);  
	}
	
	@Test
	public void test3(){
		String s="17亿美元19亿融资";
		Pattern p=Pattern.compile("[\\u4e00-\\u9fa5]+|\\d+");
		Matcher m=p.matcher(s);
		while(m.find()){
		    System.out.println(m.group());
		  }
	}
	@Test
	public void test2(){
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		NameValuePair pair = new BasicNameValuePair("name", "北京百度网讯科技有限公司 ");
		params.add(pair);
		String url="https://open.api.tianyancha.com/services/v3/newopen/getProductInfo.json";
//		JSONObject parse = HttpUtils.sendGet(KeyConstan.URL.NIANBAO, params);
		JSONObject parse = HttpUtils.sendGet(url, params);
		System.out.println(parse);
	}
}
