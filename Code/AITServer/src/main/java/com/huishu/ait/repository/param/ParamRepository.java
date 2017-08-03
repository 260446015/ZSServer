package com.huishu.ait.repository.param;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.Param;

/**
 * @author hhy
 * @date 2017年8月3日
 * @Parem
 * @return 
 * 
 */
public interface ParamRepository extends CrudRepository<Param, Long> {
	
	List<Param> fingParamByUid(Long uid);
}
