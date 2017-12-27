package com.huishu.ManageServer.service.company;

import java.util.List;

import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;

/**
 * @author hhy
 * @date 2017年12月12日
 * @Parem
 * @return 
 * 公司信息
 */
public interface CompanyService {

	/**
	 * @return
	 */
	List<CompanyEntity> findAllCompany();

}
