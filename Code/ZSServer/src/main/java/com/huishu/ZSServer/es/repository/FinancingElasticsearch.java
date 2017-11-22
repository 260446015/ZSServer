package com.huishu.ZSServer.es.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ZSServer.es.entity.FinancingInfo;

@Repository
public interface FinancingElasticsearch extends ElasticsearchRepository<FinancingInfo, String> {
	Page<FinancingInfo> findByAreaLikeAndIndustryLikeAndInvestLike(String area,String industry,String invest,Pageable page);
}
