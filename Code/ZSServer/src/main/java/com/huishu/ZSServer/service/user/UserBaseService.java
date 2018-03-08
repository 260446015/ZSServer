package com.huishu.ZSServer.service.user;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.entity.UserLabel;
import com.huishu.ZSServer.entity.dto.RegisterDTO;
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

	/**
	 * @param uid
	 * @return
	 */
	UserLabel findLabelByUserId(Long uid);

	/**
	 * @param user
	 * @return
	 */
	boolean updateLabel(UserLabel user);

	/**
	 * @param telphone
	 * @return
	 */
	UserBase findUserByTelphoneAndRealName(String telphone,String realName);

	/**
	 * @param dto
	 * @return
	 */
	AjaxResult addRegisterUser(RegisterDTO dto);
	
}
