package com.huishu.ManageServer.repository.datajpa.first;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.huishu.ManageServer.entity.dbFirst.IndusCompany;


/**
 * @author hhy
 * @date 2017年11月9日
 * @Parem
 * @return 
 * 
 */
public interface IndusCompanyRepository extends PagingAndSortingRepository<IndusCompany, Long> {
	/**
	 * 根据公司简称查看详细信息
	 * @param companyName
	 * @return
	 */
	IndusCompany findByCompanyName(String companyName);
	
	/**
	 * 根据公司全名查看公司信息
	 * @param company
	 * @return
	 */
	IndusCompany findByCompany(String company);
	
	
	
	
	@Query(value = "select COUNT(*) FROM t_indus_company ", nativeQuery = true)
	int getCount();
	/**
	 * @param i
	 * @return
	 */
	@Query(value = "SELECT * FROM t_indus_company LIMIT ?1,10", nativeQuery = true)
	List<IndusCompany> getCompanyInfo(int i);
}
