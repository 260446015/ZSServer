package com.huishu.ait.repository.user;

import java.util.ArrayList;
import java.util.List;

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
	 * 通过园区与用户等级
	 * @param userPark
	 * @param userLevel
	 * @param userType
	 * @return
	 */
	UserBase findByUserParkAndUserLevelAndUserTypeAndIsCheck(String userPark,Integer userLevel,String userType,Integer isCheck);
	/**
	 * 查看人数
	 * @param userType
	 * @return
	 */
	@Query("select count(1) from UserBase where userType=? and expireTime > sysdate()")
	Integer findMemberNum(String userType);
	/**
	 * 查看预到期人数
	 * @param userType
	 * @return
	 */
	@Query("select count(1) from UserBase where userType=? and expireTime between now() and adddate(now(),7)")
	Integer findExpireMemberNum(String userType);
	/**
	 * 查看会员地域分布数量
	 * @param userType
	 * @return
	 */
	@Query(value="select count(area) sum,area name from t_user_base where user_type=? GROUP BY area",nativeQuery = true)
	List<Object[]> findAreaRatio(String userType);
	/**
	 * 查看园区行业数量
	 * @return
	 */
	@Query(value="SELECT count(industry) sum,industry name from t_garden_data GROUP BY industry",nativeQuery = true)
	List<Object[]> findIndustryRatio();
	/**
	 * 查看会员数量
	 * @param userLevel
	 * @param time1
	 * @param time2
	 * @param search
	 * @return
	 */
	@Query("SELECT count(1) from UserBase where userType='user' and userLevel=?1 and concat(real_name, user_park) like ?4 and createTime between ?2 and ?3 and is_check=0")
	Integer findUserListCount(Integer userLevel,String time1,String time2,String search);
	/**
	 * 查看会员列表
	 * @param userLevel
	 * @param pageFrom
	 * @param pageSize
	 * @param time1
	 * @param time2
	 * @param search
	 * @return
	 */
	@Query(value="SELECT * from t_user_base where user_type='user' and user_level=?1 and concat(real_name, user_park) like ?6 and create_time between ?4 and ?5 and is_check=0 limit ?2,?3",nativeQuery = true)
	ArrayList<UserBase> findUserList(Integer userLevel,Integer pageFrom,Integer pageSize,String time1,String time2,String search);
	/**
	 * 查看预到期会员数量
	 * @param userLevel
	 * @param time1
	 * @param time2
	 * @param search
	 * @return
	 */
	@Query("SELECT count(1) from UserBase where userType='user' and userLevel=?1 and concat(real_name, user_park) like ?4 and createTime between ?2 and ?3 and expire_time between now() and adddate(now(),7)")
	Integer findWarningUserListCount(Integer userLevel,String time1,String time2,String search);
	/**
	 * 查看预到期会员列表
	 * @param userLevel
	 * @param pageFrom
	 * @param pageSize
	 * @param time1
	 * @param time2
	 * @param search
	 * @return
	 */
	@Query(value="SELECT * from t_user_base where user_type='user' and user_level=?1 and concat(real_name, user_park) like ?6 and create_time between ?4 and ?5 and expire_time between now() and adddate(now(),7) limit ?2,?3",nativeQuery = true)
	ArrayList<UserBase> findWarningUserList(Integer userLevel,Integer pageFrom,Integer pageSize,String time1,String time2,String search);
	/**
	 * 查看园区数量
	 * @param area
	 * @param search
	 * @return
	 */
	@Query(value="SELECT count(1) from t_user_park where area like ?1 and concat(id,name) like ?2",nativeQuery = true)
	Integer findGardenListCount(String area,String search);
}