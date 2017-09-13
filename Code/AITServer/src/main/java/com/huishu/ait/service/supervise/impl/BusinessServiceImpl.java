package com.huishu.ait.service.supervise.impl;

import java.util.Arrays;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.CompanyCount;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.BusinessSuperviseDTO;
import com.huishu.ait.es.repository.supervise.BusinessRepository;
import com.huishu.ait.repository.companyCount.CompanyCountRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.supervise.BusinessService;

/**
 * 园区监管-企业 service实现类
 * @author jdz
 * @version 1.0
 * @createDate 2017-8-3
 */
@Service
public class BusinessServiceImpl extends AbstractService implements BusinessService {

    private static Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);
    
    @Autowired
    private BusinessRepository businessRepository;
    
    @Autowired
    private CompanyCountRepository companyCountRepository;
    
    /** 获取企业动态列表 */
    @Override
    public Page<AITInfo> getBusinessBehaviours(BusinessSuperviseDTO dto) {
        final List<String> EMOTIONS = Arrays.asList("neutral", "negative", "positive");
        String park = dto.getPark();
        String emotion = dto.getEmotion();
        String dimension = dto.getDimension();
        PageRequest pageable = dto.builderPageRequest();
        try{
            Page<AITInfo> page = null;
            if (EMOTIONS.contains(emotion)) {
                page = businessRepository.findByParkAndEmotionAndDimension(park, emotion, dimension, pageable);
            } else {
                page = businessRepository.findByParkAndDimension(park, dimension, pageable);
            }
            Page<AITInfo> pageBusiness = setPageBusiness(page);
            return pageBusiness;
        }catch(Exception e){
            logger.error("获取企业动态数据失败",e);
            return null;
        }
    }

    /** 经过关键字搜索企业动态列表 */
    @Override
    public JSONArray searchBusinessBehaviours(BusinessSuperviseDTO dto) {
//        JSONArray array = new JSONArray();
        String business = dto.getBusiness();
        String dimension = null;
        JSONArray array = new JSONArray();
        if (null != dto.getDimension()) {
            dimension = dto.getDimension();
        }
        PageRequest pageable = dto.builderPageRequest();
        try{
            Page<AITInfo> page = null;
            //如果关键词为空值
            if (StringUtil.isEmpty(dto.getKeyword())){
                page = businessRepository.findByBusinessAndDimension(business, dimension, pageable);
            }else{
                //先把关键词取出来
                String s = dto.getKeyword();
                dto.setKeyword(null);
                BoolQueryBuilder bq = dto.builderQuery();
                bq.must(QueryBuilders.queryStringQuery(s));//全局匹配
                page = businessRepository.search(bq, pageable);
            }
            for (AITInfo p : page) {
                JSONObject map = new JSONObject();
                map.put("id", p.getId());
                map.put("business", p.getBusiness());
                map.put("emotion", p.getEmotion());
                map.put("publishDate", p.getPublishDate());
                map.put("title", p.getTitle());
                map.put("content", p.getContent());
                array.add(map);
             }
            return array;
        }catch(Exception e){
            logger.error("查询企业动态列表失败",e);
            return null;
        }
    }

    @Override
    public JSONArray searchBusiness() {
        return null;
    }
    
    @Override
    public CompanyCount addBusinessSearchCount(String business) {
        CompanyCount count1 = companyCountRepository.findByCompanyName(business);
        CompanyCount count2 = new CompanyCount();
        if (count1 == null) {
            count2.setCompanyName(business);
            count2.setSearchCount(1);
            count2 = companyCountRepository.save(count2);
        } else {
            count1.setSearchCount(count1.getSearchCount()+1);
            count2 = companyCountRepository.save(count1);
        }
        return count2;
    }

    @Override
    public Page<CompanyCount> getBusinessList() {
        PageRequest request = new PageRequest(0, 10, new Sort(new Order(Direction.DESC, "searchCount")));
        Page<CompanyCount> page = companyCountRepository.findAll(request);
        return page;
    }
}
