package com.huishu.ait.es.repository.garden;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.huishu.ait.es.entity.GardenPolicy;

/**
 * 园区政策
 * @author yindq
 * @date   2017-7-29
 */
public interface GardenPolicyRepository extends ElasticsearchCrudRepository<GardenPolicy, String>{

}
