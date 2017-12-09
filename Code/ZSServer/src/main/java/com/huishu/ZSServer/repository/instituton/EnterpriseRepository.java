package com.huishu.ZSServer.repository.instituton;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.Enterprise;

/**
 * @author hhy
 * @date 2017年11月15日
 * @Parem
 * @return 
 * 
 */
public interface EnterpriseRepository extends CrudRepository<Enterprise, Long>{

	/**
	 * @param company
	 * @return
	 * 根据公司名称查看公司信息
	 */
	Enterprise findByCompany(String company);
	
}
