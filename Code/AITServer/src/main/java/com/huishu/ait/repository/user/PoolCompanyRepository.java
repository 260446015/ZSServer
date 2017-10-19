package com.huishu.ait.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.PoolCompany;

/**
 * 用户-权限关系权限持久化类
 * 
 * @author yindq
 * @date 2017/9/21
 */
public interface PoolCompanyRepository extends CrudRepository<PoolCompany, Long> {
	/**
	 * 查看我的需求池列表
	 * 
	 * @param userId
	 * @param label
	 * @param investmentStatus
	 * @param time1
	 * @param time2
	 * @param pageFrom
	 * @param pageSize
	 * @return
	 */
	@Query(value = "select * from t_pool_company where user_id=?1 and label like ?2 and investment_status like ?3 and create_time between ?4 and ?5 limit ?6,?7", nativeQuery = true)
	List<PoolCompany> findMyCompanyList(Long userId, String label, String investmentStatus, String time1, String time2,
			Integer pageFrom, Integer pageSize);

	/**
	 * 查看我的需求池数量
	 * 
	 * @param userId
	 * @param label
	 * @param investmentStatus
	 * @param time1
	 * @param time2
	 * @return
	 */
	@Query(value = "select count(1) from t_pool_company where user_id=?1 and label like ?2 and investment_status like ?3 and create_time between ?4 and ?5", nativeQuery = true)
	Integer findMyCompanyCount(Long userId, String label, String investmentStatus, String time1, String time2);

	/**
	 * 查看我的需求池列表===未入驻
	 * 
	 * @param userId
	 * @param label
	 * @param notStatus
	 * @param time1
	 * @param time2
	 * @param pageFrom
	 * @param pageSize
	 * @return
	 */
	@Query(value = "select * from t_pool_company where user_id=?1 and label like ?2 and investment_status not like ?3 and create_time between ?4 and ?5 limit ?6,?7", nativeQuery = true)
	List<PoolCompany> findMyNotCompanyList(Long userId, String label, String notStatus, String time1, String time2,
			Integer pageFrom, Integer pageSize);

	/**
	 * 查看我的需求池数量===未入驻
	 * 
	 * @param userId
	 * @param label
	 * @param notStatus
	 * @param time1
	 * @param time2
	 * @return
	 */
	@Query(value = "select count(1) from t_pool_company where user_id=?1 and label like ?2 and investment_status not like ?3 and create_time between ?4 and ?5", nativeQuery = true)
	Integer findMyNotCompanyCount(Long userId, String label, String notStatus, String time1, String time2);

	/**
	 * 查看需求池列表
	 * 
	 * @param userPark
	 * @param label
	 * @param investmentStatus
	 * @param time1
	 * @param time2
	 * @param pageFrom
	 * @param pageSize
	 * @param search
	 * @return
	 */
	@Query(value = "select * from t_pool_company where user_park=?1 and label like ?2 and investment_status like ?3 and concat(name, father_name) like ?8 and create_time between ?4 and ?5 limit ?6,?7", nativeQuery = true)
	List<PoolCompany> findCompanyList(String userPark, String label, String investmentStatus, String time1,
			String time2, Integer pageFrom, Integer pageSize, String search);

	/**
	 * 查看需求池数量
	 * 
	 * @param userPark
	 * @param label
	 * @param investmentStatus
	 * @param time1
	 * @param time2
	 * @param search
	 * @return
	 */
	@Query(value = "select count(1) from t_pool_company where user_park=?1 and label like ?2 and investment_status like ?3 and concat(name, father_name) like ?6 and create_time between ?4 and ?5", nativeQuery = true)
	Integer findCompanyCount(String userPark, String label, String investmentStatus, String time1, String time2,
			String search);

	/**
	 * 查看需求池列表===未入驻
	 * 
	 * @param userPark
	 * @param label
	 * @param notStatus
	 * @param time1
	 * @param time2
	 * @param pageFrom
	 * @param pageSize
	 * @param search
	 * @return
	 */
	@Query(value = "select * from t_pool_company where user_park=?1 and label like ?2 and investment_status not like ?3 and concat(name, father_name) like ?8 and create_time between ?4 and ?5 limit ?6,?7", nativeQuery = true)
	List<PoolCompany> findNotCompanyList(String userPark, String label, String notStatus, String time1, String time2,
			Integer pageFrom, Integer pageSize, String search);

	/**
	 * 查看需求池数量===未入驻
	 * 
	 * @param userPark
	 * @param label
	 * @param notStatus
	 * @param time1
	 * @param time2
	 * @param search
	 * @return
	 */
	@Query(value = "select count(1) from t_pool_company where user_park=?1 and label like ?2 and investment_status not like ?3 and concat(name, father_name) like ?6 and create_time between ?4 and ?5", nativeQuery = true)
	Integer findNotCompanyCount(String userPark, String label, String notStatus, String time1, String time2,
			String search);
	
	List<PoolCompany> findByName(String name);
}