package com.huishu.aitanalysis.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.huishu.aitanalysis.es.entity.Index;

/**
 * @author hhy
 * @date 2017年8月30日
 * @Parem
 * @return 
 * 
 */
public interface IndexElasticsearch extends ElasticsearchRepository<Index,String> {
	
}
