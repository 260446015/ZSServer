package com.huishu.ait.es.repository.garden;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.huishu.ait.es.entity.GardenInformation;

/**
 * 园区情报
 * @author yindq
 * @date   2017-7-29
 */
public interface GardenInformationRepository extends ElasticsearchCrudRepository<GardenInformation, String>{

}
