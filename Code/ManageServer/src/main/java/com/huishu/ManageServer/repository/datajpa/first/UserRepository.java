package com.huishu.ManageServer.repository.datajpa.first;

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
}
