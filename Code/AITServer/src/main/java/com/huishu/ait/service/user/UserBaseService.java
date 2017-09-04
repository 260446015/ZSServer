package com.huishu.ait.service.user;

import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.FindPasswordDTO;
import com.huishu.ait.entity.dto.RegisterDTO;
import com.huishu.ait.entity.dto.UserPasswordDTO;

/**
 * 用户service
 * 
 * @author yindq
 * @date 2017年8月8日
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
	 * @param userAccount
	 * @return
	 */
	UserBase findUserByTelphone(String telphone);

	/**
	 * 通过ID查找用户信息
	 * 
	 * @param userAccount
	 * @return
	 */
	UserBase findUserByUserId(long id);

	/**
	 * 添加注册用户
	 * 
	 * @param dto
	 * @return
	 */
	AjaxResult addRegisterUser(RegisterDTO dto);

	/**
	 * 修改用户密码
	 * 
	 * @param param
	 * @return
	 */
	AjaxResult modifyPassword(UserPasswordDTO param);

	/**
	 * 重置用户密码
	 * 
	 * @param param
	 * @return
	 */
	AjaxResult findPassword(FindPasswordDTO param);
	
	/**
	 * 账号审核
	 * @param id
	 * @return
	 */
	AjaxResult auditAccount(long id);
}
