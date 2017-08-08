package com.huishu.ait.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.UserBase;

/**
 * 用户-权限关系权限持久化类
 * @author yindq
 * @date 2017年8月8日
 */
public interface UserBaseRepository extends CrudRepository<UserBase,Long>{

	/**
	 * 通过账号查找用户信息
	 * @param userAccount
	 * @return
	 */
	UserBase findByUserAccount(String userAccount);
}