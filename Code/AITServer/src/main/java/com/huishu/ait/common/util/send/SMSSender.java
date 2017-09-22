package com.huishu.ait.common.util.send;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 短信发送器
 * 
 * @author yuwei
 *
 */
@Component("SMSSender")
public class SMSSender implements MessageSender {

	private static final Logger logger = LoggerFactory.getLogger(SMSSender.class);

	/**
	 * 短信接口的签名
	 */
	public static final String SMS_SIGN = "【慧数科技】";
	/**
	 * 应用地址
	 */
	private static final String url = "http://sms.253.com/msg/send";
	/**
	 * 账号
	 */
	private static final String un = "N2892121";
	/**
	 * 密码
	 */
	private static final String pw = "OgGRVbDoe13d9e";
	/**
	 * 是否需要状态报告，需要1，不需要0
	 */
	private static final String rd = "1";
	/**
	 * 扩展码
	 */
	public static final String ex = null;

	/**
	 * 短信息接口的发送结果映射信息
	 */
	private static final Map<String, String> smsResultMap = new HashMap<String, String>() {
		private static final long serialVersionUID = -1415754529430502775L;
		{
			put("0", "短信发送成功");
			put("101", "无此用户");
			put("102", "密码错误");
			put("103", "提交过快（提交速度超过流速限制）");
			put("104", "系统忙（因平台侧原因，暂时无法处理提交的短信）");
			put("105", "敏感短信（短信内容包含敏感词）");
			put("106", "消息长度错（>536或<=0）");
			put("107", "包含错误的手机号码");
			put("108", "手机号码个数错（群发>50000或<=0）");
			put("109", "无发送额度（该用户可用短信数已使用完）");
			put("110", "不在发送时间内");
			put("113", "extno格式错（非数字或者长度不对）");
			put("116", "签名不合法或未带签名（用户必须带签名的前提下）");
			put("117", "IP地址认证错,请求调用的IP地址不是系统登记的IP地址");
			put("118", "用户没有相应的发送权限（账号被禁止发送）");
			put("119", "用户已过期");
			put("120", "违反放盗用策略(日发限制) --自定义添加");
			put("121", "必填参数。是否需要状态报告，取值true或false");
			put("122", "5分钟内相同账号提交相同消息内容过多");
			put("123", "发送类型错误");
			put("124", "白模板匹配错误");
			put("125", "匹配驳回模板，提交失败");
			put("126", "审核通过模板匹配错误");
		}
	};

	public static String getSmsReplyResult(String replyCode) {
		return smsResultMap.get(replyCode);
	}

	@Override
	public AjaxResult send(String msg, String to) {
		AjaxResult result = new AjaxResult();
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			List<BasicNameValuePair> parameters = new ArrayList<>();
			parameters.add(new BasicNameValuePair("un", un));
			parameters.add(new BasicNameValuePair("pw", pw));
			parameters.add(new BasicNameValuePair("phone", to));
			parameters.add(new BasicNameValuePair("rd", rd));
			parameters.add(new BasicNameValuePair("msg", msg));
			parameters.add(new BasicNameValuePair("ex", ex));

			HttpPost post = new HttpPost(url);
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
			logger.warn("发送短信失败 statusCode {}", statusCode);
			return result.setSuccess(false).setStatus(statusCode);
		} catch (Exception e) {
			logger.error("发送短信发生异常 :", e);
			return result.setSuccess(false);
		}
	}

}
