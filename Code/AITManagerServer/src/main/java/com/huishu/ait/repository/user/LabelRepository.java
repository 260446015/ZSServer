package com.huishu.ait.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.Label;

/**
 * 企业标签
 * 
 * @author yindq
 * @create 2017年9月22日
 */
public interface LabelRepository extends CrudRepository<Label, Long> {
	
	@Query(value = "from Label where park=?")
	List<Label> findByPark(String park);

	@Query(value = "from Label where userId=?")
	List<Label> findByUserId(Long userId);

	@Query(value = "delete from Label where id=?")
	boolean removeByUid(Long id);
}
