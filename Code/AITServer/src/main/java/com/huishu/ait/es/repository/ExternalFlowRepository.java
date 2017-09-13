package com.huishu.ait.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.huishu.ait.es.entity.ExternalFlow;

/**
 * 
 * @author yindawei 
 * @date 2017年9月12日下午8:52:10
 * @description 
 * @version
 */
public interface ExternalFlowRepository extends ElasticsearchRepository<ExternalFlow, String>{

}
