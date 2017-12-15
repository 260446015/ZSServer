package com.huishu.ZSServer.service.user;

import com.huishu.ZSServer.entity.user.UserBase;

/**
 * 用户service
 * 
 * @author yindq
 * @date 2017年12月13日
 */
public interface UserBaseService {
	/**
	 * 通过ID查找用户信息
	 * 
	 * @param userAccount
	 * @return
	 */
	UserBase findByUserId(long id);
}
