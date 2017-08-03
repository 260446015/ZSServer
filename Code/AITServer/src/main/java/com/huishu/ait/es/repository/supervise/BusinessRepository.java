package com.huishu.ait.es.repository.supervise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.huishu.ait.es.entity.AITInfo;

/**
 * 园区监管 —— 企业 
 * @author jdz
 * @createDate 2017-8-3
 */
public interface BusinessRepository extends ElasticsearchRepository<AITInfo, String>{

    /**
     * 根据相应参数查询ES,获取企业动态列表
     * @param business 企业名
     * @param emotion 情感  
     * @param pageable 分页
     * @return
     */
    public Page<AITInfo> findByParkAndEmotionAndBusiness(String park, String emotion, String business, Pageable pageable);
    
}
