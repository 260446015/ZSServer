package com.huishu.ZSServer.service.company;

import java.util.List;

import com.huishu.ZSServer.entity.IndusCompany;

/**
 * @author hhy
 * @date 2018年2月3日
 * @Parem
 * @return 
 * 
 */
public interface IndustryCompanyService {

	/**
	 * @return
	 * 获取智推的企业
	 */
	List<IndusCompany> listCompany(Long userId);

}
