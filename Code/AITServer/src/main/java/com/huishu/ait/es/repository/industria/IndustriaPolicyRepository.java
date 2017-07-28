package com.huishu.ait.es.repository.industria;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.huishu.ait.es.entity.AITInfo;


/**
 * 产业政策
 * @author jdz
 * @createDate 2017-7-28
 * @version 1.0
 */
public interface IndustriaPolicyRepository extends ElasticsearchRepository<AITInfo, String>{

}
