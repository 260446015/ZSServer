package com.huishu.ZSServer.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.huishu.ZSServer.es.entity.AITInfo;



@Repository
public interface BaseElasticsearch extends ElasticsearchRepository<AITInfo, String>,JpaSpecificationExecutor<AITInfo>{
}
