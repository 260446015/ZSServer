package com.huishu.aitanalysis.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.huishu.aitanalysis.es.entity.Index2;

/**
 * @author hhy
 * @date 2017年8月30日
 * @Parem
 * @return 
 * 高峰论坛相关接口
 */
public interface Index2Elasticsearch extends ElasticsearchRepository<Index2,String> {
	
}
