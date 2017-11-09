package com.huishu.ZSServer.repository.company;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.IndusCompany;

/**
 * @author hhy
 * @date 2017年11月9日
 * @Parem
 * @return 
 * 
 */
public interface IndusCompanyRepository extends CrudRepository<IndusCompany,Long> {
	
	IndusCompany findByCompanyName(String companyName);

}
