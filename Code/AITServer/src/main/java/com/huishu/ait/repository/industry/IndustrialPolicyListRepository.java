package com.huishu.ait.repository.industry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.IndustrialPolicy;

/**
 * 产业政策相关Repository
 * 与ES下Repository不同
 * @author jdz
 * @createDate 2017-7-28
 * @version 1.0
 */
public interface IndustrialPolicyListRepository extends CrudRepository<IndustrialPolicy, Long>{
    
    /**
     * 根据产业，产业标签，地区和（时间）获取产业政策列表
     */
    //Page<IndustrialPolicy> findByIndustryAndIndustryLabelAndAreaAndPublishdate(String industry,String industryLabel,String area,Pageable pageable);
}
