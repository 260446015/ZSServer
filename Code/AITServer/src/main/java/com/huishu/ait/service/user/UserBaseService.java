package com.huishu.ait.service.user;

import com.huishu.ait.entity.UserBase;

/**
 * 用户service
 * @author yindq
 * @date 2017年8月8日
 */
public interface UserBaseService {
	/**
	 * 通过账号查找用户信息
	 * @param userAccount
	 * @return
	 */
	UserBase getUserByUserAccount(String userAccount);
}
