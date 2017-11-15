package com.huishu.ait.repository.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.Label;

/**
 * 企业标签
 * 
 * @author yindq
 * @create 2017年9月22日
 */
public interface LabelRepository extends CrudRepository<Label, Long> {
	
	List<Label> findByPark(String park);
	
	List<Label> findByLabel(String label);

	List<Label> findByUserId(Long userId);
	
	void deleteByLabel(String label);

}
