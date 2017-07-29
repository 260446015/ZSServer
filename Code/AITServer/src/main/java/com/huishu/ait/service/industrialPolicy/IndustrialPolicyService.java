package com.huishu.ait.service.industrialPolicy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.huishu.ait.entity.IndustrialPolicy;
import com.huishu.ait.es.entity.AITInfo;

/**
 * 产业政策相关service
 * @author jdz
 * @version 1.0
 * @createDate 2017-7-28
 */
public interface IndustrialPolicyService {

    /**
     * 
     * @param industry 产业
     * @param industryLabel 产业标签
     * @param area 地区
     * @param pageable 分页对象
     * @return
     */
    Page<IndustrialPolicy> findByIndustryAndIndustryLabelAndAreaAndPublishdate(String industry,String industryLabel,String area,Pageable pageable);
   
   /**
    * 根据产业政策id，获取其详细信息
    * @param id
    */
   AITInfo getIndustrialPolicyDetailById(String id);
   
    
    
}
