package com.huishu.ManageServer.repository.first;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.entity.dbFirst.UserLogo;

/**
 * 用户-日志
 * 
 * @author yindq
 * @date 2018年1月11日
 */
@Repository
public interface UserLogoRepository extends CrudRepository<UserLogo, Long> {

	@Query(value="select b.id,b.user_account,b.real_name,b.telphone,b.user_email,l.login_time,l.operation_time,l.search_company,l.search_count from t_user_logo l LEFT JOIN t_user_base b on l.user_id = b.id where logo_type = ?1 order by login_time DESC limit ?2,?3",nativeQuery=true)
	List<Object[]> findPage(Integer logoType,Integer pageFrom , Integer pageSize);

	Integer countByLogoType(Integer logoType);
	@Query(value="select * from t_user_logo t where t.user_id= ?1 and t.search_company is not null   ORDER BY  t.search_count desc limit 0,10",nativeQuery = true)
	List <UserLogo> findListUserLog(Long userId);
}