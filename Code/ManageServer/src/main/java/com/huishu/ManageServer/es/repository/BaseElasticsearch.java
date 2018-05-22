package com.huishu.ManageServer.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.es.entity.AITInfo;


@Repository
public interface BaseElasticsearch extends ElasticsearchRepository<AITInfo, String> {
}
