package com.huishu.ait.service.industrialPolicy.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.huishu.ait.entity.IndustrialPolicy;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.repository.industria.IndustriaPolicyRepository;
import com.huishu.ait.repository.industry.IndustrialPolicyListRepository;
import com.huishu.ait.service.industrialPolicy.IndustrialPolicyService;

/**
 * 慧数招商--产业--产业政策
 * @author jdz
 * @version 1.0
 * @createDate 2017-7-28
 */
@Service
public class IndustrialPolicyServiceImpl implements IndustrialPolicyService {

    @Autowired
    private IndustriaPolicyRepository industriaPolicyRepository;
    
    @Autowired
    private IndustrialPolicyListRepository industrialPolicyListRepository;

    /**
     * 获取产业政策列表
     * （1）使用springboot
     */
    @Override
    public Page<IndustrialPolicy> findByIndustryAndIndustryLabelAndAreaAndPublishdate(String industry,
            String industryLabel, String area, Pageable pageable) {
            return industrialPolicyListRepository.findByIndustryAndIndustryLabelAndAreaAndPublishdate(industry, industryLabel, area, pageable);
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
        return industriaPolicyRepository.findOne(id);
    }
}
