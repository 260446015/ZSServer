package com.huishu.ZSServer.service.user;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.entity.user.UserBase;

/**
 * 用户service
 * 
 * @author yindq
 * @date 2017年12月13日
 */
public interface UserBaseService {
	/**
	 * 通过账号查找用户信息
	 * 
	 * @param userAccount
	 * @return
	 */
	UserBase findUserByUserAccount(String userAccount);

	/**
	 * 通过电话查找用户信息
	 * 
	 * @param telphone
	 * @return
	 */
	UserBase findUserByTelphone(String telphone);

	/**
	 * 通过ID查找用户信息
	 * 
	 * @param id
	 * @return
	 */
	UserBase findUserByUserId(long id);

	/**
	 * 修改用户邮箱
	 * @param userId
	 * @param email
	 * @return
	 */
	AjaxResult modifyEmail(Long userId, String email);

	/**
	 * 删除园区用户
	 * 
	 * @param id
	 * @return
	 */
	void dropParkAccount(Long id);

}
