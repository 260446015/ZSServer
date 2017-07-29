package com.huishu.ait.es.repository.ExpertOpinion;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ait.es.entity.AITInfo;


@Repository
public interface ExpertOpinionElasticsearch extends ElasticsearchCrudRepository<AITInfo, String>{
	
	
}
