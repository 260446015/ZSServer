package com.huishu.ait.repository.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
	/**
	 * 通过手机号查找用户信息
	 * @param telphone
	 * @return
	 */
	UserBase findByTelphone(String telphone);
	/**
	 * 通过手机号查找用户信息
	 * @param userEmail
	 * @return
	 */
	UserBase findByUserEmail(String userEmail);
	/**
	 * 修改用户密码
	 * @param id
	 * @param password
	 * @return
	 */
	@Modifying
	@Query("update UserBase ub set ub.password = ?1 where ub.id = ?2")
	Integer modifyPassword(String password,Long id);
}