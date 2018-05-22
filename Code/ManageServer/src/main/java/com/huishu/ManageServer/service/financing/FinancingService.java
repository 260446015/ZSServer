package com.huishu.ManageServer.service.financing;

import com.huishu.ManageServer.entity.dto.CompanySearchDTO;
import com.huishu.ManageServer.entity.dto.FinancingDTO;
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

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Boolean dropCompany(String id);

	/**
	 * 详情
	 * @param id
	 * @return
	 */
	FinancingInfo getCompanyById(String id);

	/**
	 * 修改
	 * @param dto
	 * @return
	 */
	Boolean saveCompany(FinancingDTO dto);

}
