package com.huishu.ait.service.user;

import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.RegisterDTO;

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
	UserBase findUserByUserAccount(String userAccount);
	/**
	 * 通过账号查找用户信息
	 * @param userAccount
	 * @return
	 */
	UserBase findUserByTelphone(String telphone);
	/**
	 * 通过账号查找用户信息
	 * @param userAccount
	 * @return
	 */
	UserBase findUserByUserId(long id);
	/**
	 * 添加注册用户
	 * @param dto
	 * @return
	 */
	AjaxResult addRegisterUser(RegisterDTO dto);
}
