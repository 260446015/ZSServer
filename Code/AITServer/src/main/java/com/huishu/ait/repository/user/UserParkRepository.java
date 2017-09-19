package com.huishu.ait.repository.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.UserPark;

/**
 * 用户园区权限持久化类
 * 
 * @author yindq
 * @create 2017年9月8日
 */
public interface UserParkRepository extends CrudRepository<UserPark,Long>{
	/**
	 * 查看园区数量
	 * @param area
	 * @param userLevel
	 * @param time1
	 * @param time2
	 * @param search
	 * @return
	 */
	@Query(value="SELECT count(1) from t_user_park where area like ?1 and concat(id,name) like ?2",nativeQuery = true)
	Integer findGardenListCount(String area,String search);
	/**
	 * 查看园区列表
	 * @param area
	 * @param userLevel
	 * @param pageFrom
	 * @param pageSize
	 * @param time1
	 * @param time2
	 * @param search
	 * @return
	 */
	@Query(value="SELECT id,name,area from t_user_park where area like ?1 and concat(id,name) like ?4 limit ?2,?3",nativeQuery = true)
	ArrayList<Object[]> findGardenList(String area,Integer pageFrom,Integer pageSize,String search);
	/**
	 * 查看园区总账号数
	 * @param park
	 * @param userLevel
	 * @param time1
	 * @param time2
	 * @return
	 */
	@Query(value="SELECT count(1) from t_user_base where user_park =?1 and user_level like ?2 and create_time between ?3 and ?4",nativeQuery = true)
	Integer findAccountCount(String park,String userLevel,String time1,String time2);
	/**
	 * 查看园区未审核账号数
	 * @param park
	 * @param userLevel
	 * @param time1
	 * @param time2
	 * @return
	 */
	@Query(value="SELECT count(1) from t_user_base where user_park =?1 and user_level like ?2 and create_time between ?3 and ?4 and is_check=0",nativeQuery = true)
	Integer findCheckAccountCount(String park,String userLevel,String time1,String time2);
	/**
	 * 查看园区已到期账号数
	 * @param park
	 * @param userLevel
	 * @param time1
	 * @param time2
	 * @return
	 */
	@Query(value="SELECT count(1) from t_user_base where user_park =?1 and user_level like ?2 and create_time between ?3 and ?4 and expire_time<now()",nativeQuery = true)
	Integer findExpireAccountCount(String park,String userLevel,String time1,String time2);
	/**
	 * 查看园区未到期账号数
	 * @param park
	 * @param userLevel   
	 * @param time1
	 * @param time2
	 * @return
	 */
	@Query(value="SELECT count(1) from t_user_base where user_park =?1 and user_level like ?2 and create_time between ?3 and ?4 and expire_time>now()",nativeQuery = true)
	Integer findNormalAccountCount(String park,String userLevel,String time1,String time2);
	/**
	 * 查看园区即将到期账号数
	 * @param park
	 * @param string
	 * @param time1
	 * @param time2
	 * @return
	 */
	@Query(value="SELECT count(1) from t_user_base where user_park =?1 and user_level like ?2 and create_time between ?3 and ?4 and expire_time between now() and adddate(now(),7)",nativeQuery = true)
	Integer findDueAccountCount(String park,String string,String time1,String time2);
	/**
	 * 分页查看园区账号
	 * @param park
	 * @param pageFrom
	 * @param pageSize
	 * @return
	 */
	@Query(value="SELECT id,user_account,real_name,start_time from t_user_base where user_park =?1 and user_type='user' and is_check=1 limit ?2,?3",nativeQuery = true)
	List<Object[]> findParkAccount(String park,Integer pageFrom,Integer pageSize);
	/**
	 * 查看园区账号数量
	 * @param park
	 * @return
	 */
	@Query(value="SELECT count(1) from t_user_base where user_park =? and user_type='user' and is_check=1",nativeQuery = true)
	Integer findParkAccountCount(String park);

}