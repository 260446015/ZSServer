package com.huishu.ZSServer.service.user;

import com.huishu.ZSServer.entity.dto.UserDTO;
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
	 * @param id
	 * @return
	 */
	UserBase findByUserId(long id);
	
	/**
	 * 修改用户密码
	 * @param id
	 * @param beforPassword
	 * @return
	 */
	String modifyPassword(long id,String beforPassword,String newPassword);
	
	/**
	 * 修改用户信息
	 * @param id
	 * @param dto
	 * @return
	 */
	Boolean modifyInformation(long id,UserDTO dto);
}
