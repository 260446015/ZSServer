package com.huishu.ait.controller;


import java.security.interfaces.RSAPrivateKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.poifs.property.Child;
import org.apache.shiro.SecurityUtils;

import com.alibaba.fastjson.JSONObject;

/*import org.apache.shiro.SecurityUtils;*/
//import org.springframework.beans.factory.annotation.Autowired;

import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.AbstractDTO;
import com.huishu.ait.security.Digests;
import com.huishu.ait.security.Encodes;
import com.huishu.ait.security.RSAUtils;
import com.huishu.ait.security.ShiroDbRealm.ShiroUser;

public abstract class BaseController {

	public AjaxResult success(Object data) {
		return new AjaxResult().setData(data).setSuccess(true).setStatus(0);
	}

	public AjaxResult error(String message) {
		return new AjaxResult().setMessage(message).setSuccess(false).setStatus(1);
	}

	public ShiroUser getCurrentShiroUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}

	public Long getUserId() {
		return getCurrentShiroUser().getId();
	}

	public String getUserAccount() {
		return getCurrentShiroUser().getLoginName();
	}

	public String getUserPark() {
		return getCurrentShiroUser().getPark();
	}

	/**
	 * 对token中获取的密码进行特殊处理
	 * 
	 * @param utoken
	 * @return
	 */
	protected String getInPassword(String pass, String salts, RSAPrivateKey priKey) {
		String descrypedPwd = null;
		try {
			String decode = RSAUtils.decryptByPrivateKey(pass, (RSAPrivateKey) priKey);
			descrypedPwd = new StringBuilder(decode).reverse().toString();
		} catch (Exception e) {
			return null;
		}
		byte[] salt = Encodes.decodeHex(salts);
		byte[] hashPassword = Digests.sha1(descrypedPwd.getBytes(), salt, Encodes.HASH_INTERATIONS);
		String inPassword = Encodes.encodeHex(hashPassword);
		return inPassword;

	}

}
