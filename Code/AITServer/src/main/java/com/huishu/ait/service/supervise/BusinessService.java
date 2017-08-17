package com.huishu.ait.service.supervise;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.entity.CompanyCount;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.BusinessSuperviseDTO;

/**
 * 园区监管——企业 service
 * @author jdz
 * @version 1.0
 * @createDate 2017-8-3
 */
public interface BusinessService {

    /**
     * 获取园区企业动态列表
     * @param dto (包含参数：park 园区名称 ; business 企业名称 ; emotion 情感; pageable 分页)
     * @return
     */
    public Page<AITInfo> getBusinessBehaviours(BusinessSuperviseDTO dto);
    
    /**
     * 获取园区企业动态详情
     * @param id 园区动态id
     * @return 园区动态详情
     */
    public JSONArray getBusinessBehaviourDetail(String id);
    
    /**
     * 根据关键字查询企业动态列表
     * @param dto
     * @return
     */
    public JSONArray searchBusinessBehaviours(BusinessSuperviseDTO dto);
    
    /**
     * 根据关键字查询企业列表
     * @return
     */
    public JSONArray searchBusiness();
    
    /**
     * 添加企业被点击数目
     * @return
     */
    public CompanyCount addBusinessSearchCount(String business);
    
    /**
     * 搜索界面，获取企业名称和被点击数量
     * @return
     */
    public Page<CompanyCount> getBusinessList();
}
