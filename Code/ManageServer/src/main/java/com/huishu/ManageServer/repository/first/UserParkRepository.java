package com.huishu.ManageServer.repository.first;


import com.huishu.ManageServer.entity.dbFirst.UserBase;
import com.huishu.ManageServer.entity.dbFirst.UserPark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 用户园区权限持久化类
 * 
 * @author yindq
 * @create 2018/1/15
 */
public interface UserParkRepository extends CrudRepository<UserPark, Long> {

	/**
	 * 查看园区列表
	 * @param pageFrom
	 * @param pageSize
	 * @return
	 */
	@Query(value="select * from t_user_park limit ?,?",nativeQuery=true)
	List<UserPark> findGardenList(int pageFrom , int pageSize);

	/**
	 * 查看园区总账号数
	 * 
	 * @param park
	 * @return
	 */
	@Query(value = "SELECT count(1) from t_user_base where user_park =?", nativeQuery = true)
	Integer findAccountCount(String park);

	/**
	 * 查看园区未审核账号数
	 * 
	 * @param park
	 * @return
	 */
	@Query(value = "SELECT count(1) from t_user_base where user_park =? and is_check=0", nativeQuery = true)
	Integer findCheckAccountCount(String park);

	/**
	 * 查看园区已到期账号数
	 * 
	 * @param park
	 * @return
	 */
	@Query(value = "SELECT count(1) from t_user_base where user_park =? and expire_time<now()", nativeQuery = true)
	Integer findExpireAccountCount(String park);

	/**
	 * 查看园区未到期账号数
	 * 
	 * @param park
	 * @return
	 */
	@Query(value = "SELECT count(1) from t_user_base where user_park =? and expire_time>now()", nativeQuery = true)
	Integer findNormalAccountCount(String park);

	/**
	 * 查看园区即将到期账号数
	 * 
	 * @param park
	 * @return
	 */
	@Query(value = "SELECT count(1) from t_user_base where user_park =? and expire_time between now() and adddate(now(),7)", nativeQuery = true)
	Integer findDueAccountCount(String park);

	/**
	 * 分页查看园区账号
	 * @param park
	 * @return
	 */
	@Query(value = "SELECT * from t_user_base where user_park =? and is_check=1", nativeQuery = true)
	List<UserBase> findParkAccount(String park);


}