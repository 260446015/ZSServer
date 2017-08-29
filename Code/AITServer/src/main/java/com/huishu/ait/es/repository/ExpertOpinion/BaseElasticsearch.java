package com.huishu.ait.es.repository.ExpertOpinion;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ait.es.entity.AITInfo;


@Repository
public interface BaseElasticsearch extends ElasticsearchRepository<AITInfo, String>{
	
	
}
