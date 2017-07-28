package com.huishu.ait.service.industrialPolicy;

import com.huishu.ait.es.entity.AITInfo;

/**
 * 产业政策相关service
 * @author jdz
 * @version 1.0
 * @createDate 2017-7-28
 */
public interface IndustrialPolicyService {

   
   /**
    * 根据产业政策id，获取其详细信息
    * @param id
    */
   AITInfo getIndustrialPolicyDetailById(String id);
   
    
    
}
