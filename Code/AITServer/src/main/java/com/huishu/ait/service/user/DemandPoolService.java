package com.huishu.ait.service.user;

import com.huishu.ait.entity.PoolCompany;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanySearchDTO;

import java.util.List;

/**
 * 需求池相关
 *
 * @author yindq
 * @date 2017/9/21
 */
public interface DemandPoolService {

    /**
     * 获取我的需求池列表
     * @param searchModel
     * @return
     */
    List<PoolCompany> getMyCompanyList(CompanySearchDTO searchModel);

    /**
     * 获取所有需求池列表（园区）
     * @param searchModel
     * @return
     */
    List<PoolCompany> getCompanyList(CompanySearchDTO searchModel);

    /**
     * 添加需求
     * @param company
     * @return
     */
    AjaxResult addPoolCompany(PoolCompany company);
}
