package com.huishu.ManageServer.service.user;

import com.huishu.ManageServer.entity.dbFirst.Role;
import com.huishu.ManageServer.entity.dbFirst.UserBase;
import com.huishu.ManageServer.entity.dbFirst.UserPermission;
import com.huishu.ManageServer.entity.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

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
	 * 分页查看用户列表
	 * @param dto
	 * @return
	 */
	Page<UserBase> listParkUserBase(AccountDTO dto);
	
	/**
	 * 获取待审核账号列表
	 * 
	 * @param dto
	 * @return
	 */
	Page<UserBase> getAccountList(AccountSearchDTO dto);

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

	/**
	 * 查询用户使用天眼查查询金额
	 * @param dto
	 * @return
	 */
	List<AccountDTO> getAccountByUser(AccountDTO dto);


	/**
	 * 修改用户能否单点登录
	 * @param id
	 * @return
	 */
	Boolean modifyIsSingle(Long id,Integer isSingle);

	/**
	 * 账号审核
	 * @param id
	 * @return
	 */
	Boolean modifyIsCheck(Long id);

	/**
	 *账号查询
	 * @param id
	 * @return
	 */

    List<UserBase> getAccountByName(String id);

    List<Role> findRole();

    List<UserPermission> findPermission();
	/**
	 *添加/修改用户权限信息
	 * @param
	 * @return
	 */
	Boolean saveUserRolePermission(List<RolePermissionDto> rolePermission);


	boolean updateUserRole(List<RolePermissionDto> dto);
}
