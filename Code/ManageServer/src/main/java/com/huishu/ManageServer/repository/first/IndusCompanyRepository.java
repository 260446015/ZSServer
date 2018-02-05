package com.huishu.ManageServer.repository.first;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.entity.dbFirst.IndusCompany;


/**
 * @author hhy
 * @date 2017年11月9日
 * @Parem
 * @return 
 * 
 */
@Repository
public interface IndusCompanyRepository extends CrudRepository<IndusCompany, Long> {
	
	@Query(value="select * from t_indus_company limit ?,?",nativeQuery=true)
	List<IndusCompany> findIndus(int currentCount ,int pageSize);
	
	@Query(value = "select COUNT(*) FROM t_indus_company ", nativeQuery = true)
	int getCount();
	
	List<IndusCompany> findByIndustry(String indus);
	
	List<IndusCompany> findByIndustryIsNull();
	
	IndusCompany findByCompany(String name);
	
	
}
