package com.huishu.ManageServer.service.user;

import org.springframework.data.domain.Page;

import com.huishu.ManageServer.entity.dbFirst.UserBase;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.UserBaseDTO;

/**
 * 用户管理service
 *
 * @author yindq
 * @date 2018/1/4
 */
public interface UserService {
	/**
	 * 分页查看用户列表
	 * @param dto
	 * @return
	 */
	Page<UserBase> listUserBase(AbstractDTO dto);

	/**
	 * 添加/修改用户信息
	 * @param dto
	 * @return
	 */
	Boolean saveUserBase(UserBaseDTO dto);

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	Boolean dropUserBase(Long id);

	/**
	 * 查看用户详情
	 * @param id
	 * @return
	 */
	UserBase findById(Long id);
}
