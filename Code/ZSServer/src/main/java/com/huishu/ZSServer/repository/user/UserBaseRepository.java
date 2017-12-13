package com.huishu.ZSServer.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.user.UserBase;

/**
 * 用户-权限关系权限持久化类
 * 
 * @author yindq
 * @date 2017年12月13日
 */
public interface UserBaseRepository extends CrudRepository<UserBase, Long> {

	/**
	 * 通过账号查找用户信息
	 * 
	 * @param userAccount
	 * @param userType
	 * @return
	 */
	UserBase findByUserAccount(String userAccount);

}