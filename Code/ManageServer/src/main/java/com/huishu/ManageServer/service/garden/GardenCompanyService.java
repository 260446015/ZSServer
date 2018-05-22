package com.huishu.ManageServer.service.garden;

import org.springframework.data.domain.Page;

import com.huishu.ManageServer.entity.dbFirst.Company;
import com.huishu.ManageServer.entity.dto.CompanySearchDTO;


/**
 * 
 * @author yindawei
 * @date 2017年10月31日下午4:20:29
 * @description 企业接口service
 * @version
 */
public interface GardenCompanyService {

	Page<Company> findCompanyList(CompanySearchDTO dto);

	boolean saveGardenCompany(Company company);


}
