package com.huishu.ManageServer.service.financing;

import com.huishu.ManageServer.entity.dto.CompanySearchDTO;
import com.huishu.ManageServer.es.entity.FinancingInfo;
import org.springframework.data.domain.Page;

/**
 * 融资数据
 *
 * @author yindq
 * @date 2018/1/31
 */
public interface FinancingService {
	/**
	 * 获取融资企业列表
	 * @param dto
	 * @return
	 */
	Page<FinancingInfo> getCompanyList(CompanySearchDTO dto);
}
