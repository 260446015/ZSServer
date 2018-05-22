package com.huishu.ManageServer.es.repository;

import com.huishu.ManageServer.es.entity.FinancingInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 融资数据
 *
 * @author yindq
 * @date 2018/2/1
 */
@Repository
public interface FinancingElasticsearch extends ElasticsearchRepository<FinancingInfo, String>{
}
