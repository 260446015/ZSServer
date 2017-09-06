package com.huishu.ait.repository.industry;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.es.entity.IndustrialPolicy;

/**
 * 产业政策相关Repository
 * 与ES下Repository不同
 * @author jdz
 * @createDate 2017-7-28
 * @version 1.0
 */
public interface IndustrialPolicyListRepository extends CrudRepository<IndustrialPolicy, Long>{
    
}
