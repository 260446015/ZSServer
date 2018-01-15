package com.huishu.ManageServer.repository.first;

import com.huishu.ManageServer.entity.dbFirst.UserBase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表dao
 *
 * @author yindq
 * @date 2018/1/4
 */
@Repository
public interface UserRepository extends CrudRepository<UserBase, Long> {
	@Query(value="select * from t_user_base limit ?,?",nativeQuery=true)
	List<UserBase> findPage(int pageFrom , int pageSize);

	@Query(value="select * from t_user_base where user_park = ? limit ?,?",nativeQuery=true)
	List<UserBase> findParkPage(String park,int pageFrom , int pageSize);

	List<UserBase> findByUserPark(String park);

	Long countByUserPark(String userPark);
	
	@Query(value="select id from t_user_base where user_park=?",nativeQuery=true)
	List<Long> findUserIds(String park);
	
	@Query(value="select * from t_user_base where user_type=?1 and create_time between ?2 and ?3 and is_check=0 limit ?4,?5",nativeQuery=true)
	List<UserBase> findCheckPage(String userType, String time1, String time2,int pageFrom , int pageSize);
	
	@Query(value="select count(1) from t_user_base where user_type=?1 and create_time between ?2 and ?3 and is_check=0",nativeQuery=true)
	Integer countCheckPage(String userType, String time1, String time2);
}
