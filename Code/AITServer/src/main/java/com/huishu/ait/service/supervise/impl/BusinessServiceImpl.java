package com.huishu.ait.service.supervise.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
        PageRequest pageable = dto.builderPageRequest();
        try{
            Page<AITInfo> page = businessRepository.findByParkAndEmotionAndBusiness(park, emotion, business, pageable);
            return page;
        }catch(Exception e){
            logger.error("获取企业动态数据失败",e);
            return null;
        }
    }

    /** 经过关键字搜索企业动态列表 */
    @Override
    public JSONArray searchBusinessBehaviours(BusinessSuperviseDTO dto) {
        JSONArray array = new JSONArray();
        try{
            if(dto == null){
                return null;
            }
            
            Page<AITInfo> page = null;
            return null;
            
        }catch(Exception e){
            logger.error("查询企业动态列表失败",e);
            return null;
        }
    }
}
