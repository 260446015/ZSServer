package com.huishu.ait.service.supervise.impl;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.BusinessSuperviseDTO;
import com.huishu.ait.es.repository.supervise.BusinessRepository;
import com.huishu.ait.service.supervise.BusinessService;

/**
 * 园区监管-企业 service实现类
 * @author jdz
 * @version 1.0
 * @createDate 2017-8-3
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    private static Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);
    
    @Autowired
    private BusinessRepository businessRepository;
    
    @Override
    public JSONArray getBusinessBehaviourDetail(String id) {
        try{
            JSONArray array = new JSONArray();
            AITInfo info = businessRepository.findOne(id);
            /*if(null != info){
                JSONObject obj = new JSONObject();
            }*/
            array.add(info);
            return array;
        }catch(Exception e){
            logger.error("获取企业动态详情失败",e);
            return null;
        }
    }

    /** 获取企业动态列表 */
    @Override
    public Page<AITInfo> getBusinessBehaviours(BusinessSuperviseDTO dto) {
        String park = dto.getPark();
        String business = dto.getBusiness();
        String emotion = dto.getEmotion();
        String dimension = dto.getDimension();
        PageRequest pageable = dto.builderPageRequest();
        try{
            Page<AITInfo> page = businessRepository.findByParkAndEmotionAndBusinessAndDimension(park, emotion, business, dimension, pageable);
            return page;
        }catch(Exception e){
            logger.error("获取企业动态数据失败",e);
            return null;
        }
    }

    /** 经过关键字搜索企业动态列表 */
    @Override
    public Page<AITInfo> searchBusinessBehaviours(BusinessSuperviseDTO dto) {
//        JSONArray array = new JSONArray();
        String park = dto.getPark();
        String dimension = dto.getDimension();
        PageRequest pageable = dto.builderPageRequest();
        try{
            Page<AITInfo> page = null;
            //如果关键词为空值
            if (StringUtil.isEmpty(dto.getKeyword())){
                page = businessRepository.findByParkAndDimension(park, dimension, pageable);
            }else{
                //先把关键词取出来
                String s = dto.getKeyword();
                dto.setKeyword(null);
                BoolQueryBuilder bq = dto.builderQuery();
                bq.must(QueryBuilders.queryStringQuery(s));//全局匹配
                page = businessRepository.search(bq, pageable);
            }
            return page;
        }catch(Exception e){
            logger.error("查询企业动态列表失败",e);
            return null;
        }
    }

    @Override
    public JSONArray searchBusiness() {
        return null;
    }
}
