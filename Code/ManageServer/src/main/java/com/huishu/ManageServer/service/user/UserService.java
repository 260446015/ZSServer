package com.huishu.ManageServer.service.user;

import java.util.List;

import org.springframework.data.domain.Page;

import com.huishu.ManageServer.entity.dbFirst.UserBase;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.AccountDTO;
import com.huishu.ManageServer.entity.dto.AccountSearchDTO;
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
}
