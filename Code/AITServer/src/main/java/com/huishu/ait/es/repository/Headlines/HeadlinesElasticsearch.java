package com.huishu.ait.es.repository.Headlines;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.huishu.ait.es.entity.AITInfo;

/**
 * @author hhy
 * @date 2017年7月27日
 * @Parem
 * @return 
 * 
 */
public interface HeadlinesElasticsearch extends ElasticsearchCrudRepository<AITInfo, String> {

}
