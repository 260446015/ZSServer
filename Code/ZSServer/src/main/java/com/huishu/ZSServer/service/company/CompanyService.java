package com.huishu.ZSServer.service.company;

import java.util.List;

import org.springframework.data.domain.Page;

import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;

/**
 * 
 * @author yindawei 
 * @date 2017年10月31日下午4:20:29
 * @description 企业接口service
 * @version
 */
public interface CompanyService {

	Page<Company> findCompanyList(CompanySearchDTO dto);

	/**
	 * @param area
	 * @param industry
	 * @return
	 * 查找公司名称
	 */
	List<String> findCompanyName(String area, String industry);

	boolean attationCompany(Long companyId,boolean flag,Long userId);

	
}
