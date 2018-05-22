package com.huishu.ManageServer.service.user;

import com.huishu.ManageServer.entity.dbFirst.AdminBase;

/**
 * 管理员管理service
 *
 * @author yindq
 * @date 2018/1/4
 */
public interface AdminService {

	/**
	 * 查看用户详情
	 * @param userAccount
	 * @return
	 */
	AdminBase findByUserAccount(String userAccount);


}
