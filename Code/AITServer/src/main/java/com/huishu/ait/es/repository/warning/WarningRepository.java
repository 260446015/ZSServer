package com.huishu.ait.es.repository.warning;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.huishu.ait.es.entity.GardenInformation;

/**
 * 企业疑似外流
 * @author yindq
 * @date 2017年8月3日
 */
public interface WarningRepository extends ElasticsearchCrudRepository<GardenInformation, String>{
	//暂时使用和园区动态一样的
}
