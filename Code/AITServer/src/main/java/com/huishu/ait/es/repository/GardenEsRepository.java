package com.huishu.ait.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.huishu.ait.es.entity.AITInfo;

public interface GardenEsRepository extends ElasticsearchRepository<AITInfo, String> {

}
