package com.huishu.ait.security;

import java.security.interfaces.RSAPrivateKey;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.huishu.ait.entity.UserBase;
import com.huishu.ait.repository.user.UserBaseRepository;

/**
 * 自定义校验密码类
 * @author yindq
 * @date 2017年8月8日
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CustomCredentialsMatcher.class);
	
	@Autowired
	private UserBaseRepository userBaseRepository;
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

		CaptchaUsernamePasswordToken utoken = (CaptchaUsernamePasswordToken) token;

		// 获得用户输入的密码
		String inPassword = getInPassword(utoken);
		// 获得数据库中的密码
		String dbPassword = (String) info.getCredentials();
		System.out.println("===============");
		System.out.println(inPassword);
		System.out.println(dbPassword);
		// 进行密码的比对
		return this.equals(inPassword, dbPassword);
	}

	/**
	 * 对token中获取的密码进行特殊处理
	 * @param utoken
	 * @return
	 */
	private String getInPassword(CaptchaUsernamePasswordToken utoken) {
		String pass = String.valueOf(utoken.getPassword());
		Subject currentUser = SecurityUtils.getSubject();
		// 获取当前用户的私钥
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
		UserBase user = userBaseRepository.findByUserAccount(utoken.getUsername());
		if(user==null){
			return null;
		}
		byte[] salt = Encodes.decodeHex(user.getSalt());
		byte[] hashPassword = Digests.sha1(descrypedPwd.getBytes(), salt, Encodes.HASH_INTERATIONS);
		String inPassword = Encodes.encodeHex(hashPassword);
		return inPassword;

	}
}
