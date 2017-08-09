package com.huishu.ait.service.industrialPolicy.impl;


import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
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
    
    @SuppressWarnings("unused")
    @Autowired
    private Client client;
    
    /**
     * 获取产业政策列表
     * 使用springboot
     */
    @Override
    public JSONArray getIndustrialPolicyList(IndustrialPolicyDTO dto) {
        JSONArray array = new JSONArray();
        if (dto.getIndustryLabel().equals("不限")){
            dto.setIndustryLabel(null);
        }
        if (dto.getArea().equals("全部") || dto.getArea().equals("不限")){
            dto.setArea(null);
        }
        try{
            Map<String, Object> map = new HashMap<String, Object>();
            //按文章类型按照维度获取数据 1,政策解读 2, 高峰论坛  3,科学研究
            dto.setDimension("政策解读");
            //获取查询对象
            BoolQueryBuilder bq = dto.builderQuery();
            Page<AITInfo> pagedate1 = industrialPolicyRepository.search(bq,dto.builderPageRequest());
            dto.setDimension("高峰论坛");
            bq = dto.builderQuery();
            Page<AITInfo> pagedate2 = industrialPolicyRepository.search(bq,dto.builderPageRequest());
            dto.setDimension("科学研究");
            bq = dto.builderQuery();
            Page<AITInfo> pagedate3 = industrialPolicyRepository.search(bq,dto.builderPageRequest());
            
            map.put("政策解读", pagedate1);
            map.put("高峰论坛", pagedate2);
            map.put("科学研究", pagedate3);
            
            array.add(map);
            
            //获取查询结果
//            Page<AITInfo> pagedata  = industrialPolicyRepository.search(bq,dto.builderPageRequest());
            return array;
        }
        catch(Exception e){
            log.error("查询产业政策列表失败",e);
            return array;
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
