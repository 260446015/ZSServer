package com.huishu.ait.security;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huishu.ait.common.util.send.SMSSender;
import com.huishu.ait.entity.common.AjaxResult;

/**
 * 短信验证码管理
 * @author yuwei
 */
@Component
public class CaptchaManager {

	@Autowired
	private SMSSender smsSender;

	private static final Logger log = LoggerFactory.getLogger(CaptchaManager.class);

	private static final String MESSAGE = "您的短信验证码为 %s, 请在30分钟内填写";

	private static final Map<String, MyCaptcha> container = new ConcurrentHashMap<>();

	private final Runnable cleanupRunnable = new Runnable() {
		@Override
		public void run() {
			while (true) {
				if (container.isEmpty()) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
					}
					continue;
				}
				Iterator<Entry<String, MyCaptcha>> it = container.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, MyCaptcha> entry = it.next();
					if (isExpired(entry.getValue())) {
						it.remove();
					}
				}
			}
		}
	};

	public CaptchaManager() {
		new Thread(cleanupRunnable).start();
	}
	
	public boolean send(String phoneNumber) {
		MyCaptcha myCaptcha = container.get(phoneNumber);
		String captcha = CheckCodeUtil.getRandomPhoneCaptcha();
		if (myCaptcha == null) {
			AjaxResult result = smsSender.send(String.format(MESSAGE, captcha), phoneNumber);
			if (!result.isSuccess()) {
				if (log.isDebugEnabled()) {
					log.debug("手机 {} 验证码发送失败", phoneNumber);
				}
				return false;
			}
			if (log.isDebugEnabled()) {
				log.debug("手机 {} 验证码发送成功", phoneNumber);
			}
			myCaptcha = new MyCaptcha();
			myCaptcha.setCaptcha(captcha);
			myCaptcha.setSendMills(System.currentTimeMillis());
			container.put(phoneNumber, myCaptcha);
			return true;
		}

		if (checkFrequency(myCaptcha)) {
			log.warn("手机号 {} 获取短信验证码频率过快", phoneNumber);
			return false;
		}

		AjaxResult result = smsSender.send(String.format(MESSAGE, captcha), phoneNumber);
		if (!result.isSuccess()) {
			if (log.isDebugEnabled()) {
				log.debug("手机 {} 验证码发送失败", phoneNumber);
			}
			return false;
		}

		myCaptcha = new MyCaptcha();
		myCaptcha.setCaptcha(captcha);
		myCaptcha.setSendMills(System.currentTimeMillis());
		container.put(phoneNumber, myCaptcha);
		return true;
	}

	/**
	 * 校验验证码是否正确
	 */
	public boolean checkCaptcha(String phoneNumber, String code) {
		MyCaptcha myCaptcha = container.get(phoneNumber);
		if (myCaptcha == null) {
			return false;
		}
		if (isExpired(myCaptcha)) {
			return false;
		}
		return code.equals(myCaptcha.getCaptcha());
	}

	/**
	 * 校验发送频率，一个手机号一分钟只能发送一次
	 */
	private boolean checkFrequency(MyCaptcha captcha) {
		Long mills = captcha.getSendMills();
		return (System.currentTimeMillis() - mills) <= 60000L;
	}

	/**
	 * 校验验证码是否失效，30分钟失效
	 */
	private boolean isExpired(MyCaptcha captcha) {
		Long mills = captcha.getSendMills();
		return (System.currentTimeMillis() - mills) > 60000 * 30;
	}

	private static class MyCaptcha {
		/** 验证码 */
		private String captcha;
		/** 上次发送时间 */
		private long sendMills;

		public String getCaptcha() {
			return captcha;
		}

		public void setCaptcha(String captcha) {
			this.captcha = captcha;
		}

		public long getSendMills() {
			return sendMills;
		}

		public void setSendMills(long sendMills) {
			this.sendMills = sendMills;
		}
	}
}
