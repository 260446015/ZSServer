package com.huishu.ait.service.industrialPolicy.impl;


import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.entity.dto.IndustrialPolicyDTO;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.repository.industria.IndustriaPolicyRepository;
import com.huishu.ait.service.industrialPolicy.IndustrialPolicyService;

/**
 * 慧数招商--产业--产业政策
 * @author jdz
 * @version 1.0
 * @createDate 2017-7-28
 */
@Service
public class IndustrialPolicyServiceImpl implements IndustrialPolicyService {
    
    private static Logger log = LoggerFactory.getLogger(IndustrialPolicyServiceImpl.class);

    @Autowired
    private IndustriaPolicyRepository industrialPolicyRepository;
    
    @Autowired
    private Client client;
    
    /**
     * 获取产业政策列表
     * 使用springboot
     */
    @Override
    public Page<AITInfo> getIndustrialPolicyList(IndustrialPolicyDTO dto) {
        
        /**
         * 获取ES查询对象bq
         */
        try{
            //获取查询对象
            BoolQueryBuilder bq = dto.builderQuery();
            //获取查询结果
            Page<AITInfo> pagedata  = industrialPolicyRepository.search(bq,dto.builderPageRequest());
            return pagedata;
        }
        catch(Exception e){
            log.error("查询产业政策列表失败",e);
            return null;
        }
    }
    
    /**
     * 通过ES，根据id获取政策详情
     * (1),使用ES中ElasticSearchRepository的findOne方法
     */
    @Override
    public AITInfo getIndustrialPolicyDetailById(String id) {
        /**
         * 直接调用ElasticsearchRepository 中的 findOne方法
         */
        return industrialPolicyRepository.findOne(id);
    }
    
    /**
     * 查询es库，获取更多条件查询
     * 
     * @return
     */
    private NativeSearchQueryBuilder getSearchQueryBuilder() {
        return new NativeSearchQueryBuilder().withIndices(INDEX).withTypes(TYPE);
    }
}
