package com.huishu.ait.security;

import java.security.interfaces.RSAPrivateKey;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.huishu.ait.entity.UserBase;
import com.huishu.ait.repository.user.UserBaseRepository;

/**
 * 自定义校验密码类
 * 
 * @author yindq
 * @date 2017年8月8日
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	private static Logger LOGGER = LoggerFactory.getLogger(CustomCredentialsMatcher.class);
	private String retryLimitCacheName;
	private final static String DEFAULT_CHACHE_NAME = "retryLimitCache";

	private Cache<String, AtomicInteger> passwordRetryCache;

	public CustomCredentialsMatcher(CacheManager cacheManager) {
		this.retryLimitCacheName = DEFAULT_CHACHE_NAME;
		this.passwordRetryCache = cacheManager.getCache(retryLimitCacheName);
	}

	public String getRetryLimitCacheName() {
		return retryLimitCacheName;
	}

	public void setRetryLimitCacheName(String retryLimitCacheName) {
		this.retryLimitCacheName = retryLimitCacheName;
	}

	@Autowired
	private UserBaseRepository userBaseRepository;

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		CaptchaUsernamePasswordToken utoken = (CaptchaUsernamePasswordToken) token;
		String username = utoken.getUsername();
		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
			LOGGER.warn("username: " + username + " tried to login more than 5 times in period");
			throw new ExcessiveAttemptsException();
		} else {
			passwordRetryCache.put(username, retryCount);
		}
		String inPassword = getInPassword(utoken);
		String dbPassword = (String) info.getCredentials();
		boolean matches = this.equals(inPassword, dbPassword);
		if (matches) {
			// clear retry data
			passwordRetryCache.remove(username);
		}
		return matches;
	}

	/**
	 * 对token中获取的密码进行特殊处理
	 * 
	 * @param utoken
	 * @return
	 */
	private String getInPassword(CaptchaUsernamePasswordToken utoken) {
		String pass = String.valueOf(utoken.getPassword());
		Subject currentUser = SecurityUtils.getSubject();
		Object priKey = currentUser.getSession().getAttribute("privateKey");
		if (priKey == null) {
			return null;
		}
		String descrypedPwd = null;
		try {
			String decode = RSAUtils.decryptByPrivateKey(pass, (RSAPrivateKey) priKey);
			descrypedPwd = new StringBuilder(decode).reverse().toString();
		} catch (Exception e) {
			LOGGER.error("私钥解密失败", e);
			return null;
		}
		UserBase user = userBaseRepository.findByUserAccountAndUserType(utoken.getUsername(), utoken.getType());
		if (user == null) {
			return null;
		}
		byte[] salt = Encodes.decodeHex(user.getSalt());
		byte[] hashPassword = Digests.sha1(descrypedPwd.getBytes(), salt, Encodes.HASH_INTERATIONS);
		String inPassword = Encodes.encodeHex(hashPassword);
		return inPassword;

	}
}
