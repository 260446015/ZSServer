package com.huishu.ZSServer.service.user;

import com.huishu.ZSServer.entity.user.UserBase;

/**
 * 用户日志service
 * 
 * @author yindq
 * @date 2018年1月10日
 */
public interface UserLogoService {
	
	/**
	 * 添加用户登录日志
	 * @param userId
	 * @return
	 */
	Boolean addLoginLogo(long userId);
	/**
	 * 添加用户操作日志
	 * @param userId
	 * @return
	 */
	Boolean addOperationLogo(long userId,String search);

}

