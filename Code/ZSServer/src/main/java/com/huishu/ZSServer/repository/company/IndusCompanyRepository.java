package com.huishu.ZSServer.repository.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.IndusCompany;

/**
 * @author hhy
 * @date 2017年11月9日
 * @Parem
 * @return 
 * 
 */
public interface IndusCompanyRepository extends CrudRepository<IndusCompany,Long>,JpaSpecificationExecutor<IndusCompany> {
	
	IndusCompany findByCompanyName(String companyName);
	
	@Query(value = "select COUNT(*) FROM t_indus_company ", nativeQuery = true)
	int getCount();
	
	@Query(value = "SELECT MIN(id) FROM t_indus_company ", nativeQuery = true)
	int getMINId();
	
	@Query(value = "SELECT MAX(id) FROM t_indus_company ", nativeQuery = true)
	int getMAXId();
}
