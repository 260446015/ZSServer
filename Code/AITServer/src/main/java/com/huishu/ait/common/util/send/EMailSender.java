package com.huishu.ait.common.util.send;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.huishu.ait.entity.common.AjaxResult;

/**
 * 邮件发送器
 * @author yuwei
 *
 */
@Component("EMailSender")
public class EMailSender implements MessageSender {
	private static final Logger logger = LoggerFactory.getLogger(EMailSender.class);
	
	private static final String URL = "http://zz.253.com/emailcard/api/send.html";
	private static final String ACCOUNT = "E9857084";
	private static final String PASSWORD = "TpNgeaD41n8a7b";
	private static final String FROM = "guanjia@email.junquan.com.cn";
	private static final String FROMNAME = "慧数教育";
	private static final String SUBJECT = "慧数教育预警信息";

	@Override
	public AjaxResult send(String msg, String to) {
		AjaxResult result = new AjaxResult();
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			List<BasicNameValuePair> parameters = new ArrayList<>();
			parameters.add(new BasicNameValuePair("account", ACCOUNT));
			parameters.add(new BasicNameValuePair("password", PASSWORD));
			parameters.add(new BasicNameValuePair("from", FROM));
			parameters.add(new BasicNameValuePair("to", to));
			parameters.add(new BasicNameValuePair("subject", SUBJECT));
			parameters.add(new BasicNameValuePair("html", msg));
			parameters.add(new BasicNameValuePair("fromName", FROMNAME));
			
			HttpPost post = new HttpPost(URL);
			post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
			HttpResponse response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				String responseString = EntityUtils.toString(response.getEntity());
				if (logger.isDebugEnabled()) {
					logger.debug(responseString);
				}
				return result.setSuccess(true);
			}
			logger.warn("发送邮件失败 statusCode {}", statusCode);
			return result.setSuccess(false).setStatus(statusCode);
		} catch (Exception e) {
			logger.error("发送邮件发生异常 :", e);
			return result.setSuccess(false);
		}
	}
	
}
