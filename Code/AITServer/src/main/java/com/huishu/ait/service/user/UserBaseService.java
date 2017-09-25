package com.huishu.ait.service.user;

import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.AddAccountDTO;
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
	UserBase findUserByUserAccount(String userAccount,String type);

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
	 * 修改用户邮箱
	 * 
	 * @param param
	 * @return
	 */
	AjaxResult modifyEmail(Long userId, String email);

	/**
	 * 重置用户密码
	 * 
	 * @param param
	 * @return
	 */
	AjaxResult findPassword(FindPasswordDTO param);

	/**
	 * 添加园区正式用户
	 * 
	 * @param userBase
	 * @return
	 */
	AjaxResult addParkAccount(AddAccountDTO param);

	/**
	 * 删除园区用户
	 * 
	 * @param id
	 * @return
	 */
	void dropParkAccount(Long id);

}
