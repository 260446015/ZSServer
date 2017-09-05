package com.huishu.ait.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.UserBase;
import com.huishu.ait.entity.common.Ratio;

/**
 * 用户-权限关系权限持久化类
 * @author yindq
 * @date 2017年8月8日
 */
public interface UserBaseRepository extends CrudRepository<UserBase,Long>{

	/**
	 * 通过账号查找用户信息
	 * @param userAccount
	 * @param userType
	 * @return
	 */
	UserBase findByUserAccountAndUserType(String userAccount,String userType);
	/**
	 * 通过手机号查找用户信息
	 * @param telphone
	 * @param userType
	 * @return
	 */
	UserBase findByTelphoneAndUserType(String telphone,String userType);
	/**
	 * 通过手机号查找用户信息
	 * @param userEmail
	 * @param userType
	 * @return
	 */
	UserBase findByUserEmailAndUserType(String userEmail,String userType);
	/**
	 * 查看人数
	 * @param userType
	 * @return
	 */
	@Query("select count(1) from t_user_base where user_type=?")
	Integer findMemberNum(String userType);
	/**
	 * 查看预到期人数
	 * @param userType
	 * @return
	 */
	@Query("select count(1) from t_user_base where user_type=? and expire_time > sysdate()")
	Integer findExpireMemberNum(String userType);
	/**
	 * 查看预到期人数
	 * @param userType
	 * @return
	 */
	@Query("select count(area) sum,area name from t_user_base where user_type=? GROUP BY area")
	List<Ratio> findAreaRatio(String userType);
	/**
	 * 查看园区行业数量
	 * @param userEmail
	 * @return
	 */
	@Query("SELECT count(industry) sum,industry name from t_company_data where park=？ GROUP BY industry")
	List<Ratio> findIndustryRatio(String park);
}