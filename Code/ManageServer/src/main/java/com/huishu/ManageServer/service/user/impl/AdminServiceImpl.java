package com.huishu.ManageServer.service.user.impl;

import com.huishu.ManageServer.entity.dbFirst.AdminBase;
import com.huishu.ManageServer.repository.first.AdminRepository;
import com.huishu.ManageServer.service.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员管理service
 *
 * @author yindq
 * @date 2018/1/4
 */
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;

	/**
	 * 查看用户详情
	 * @param userAccount
	 * @return
	 */
	@Override
	public AdminBase findByUserAccount(String userAccount){
		return adminRepository.findByUserAccount(userAccount);
	}


}
