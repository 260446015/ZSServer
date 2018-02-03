package com.huishu.ZSServer.repository.company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.IndusCompany;

/**
 * @author hhy
 * @date 2017年12月25日
 * @Parem
 * @return 
 * 数据测试专用类
 * 
 */
public interface IndusCompanyTestRepository extends CrudRepository<IndusCompany,Long>,JpaSpecificationExecutor<IndusCompany> {
	/**
	 * 根据公司简称查看详细信息
	 * @param companyName
	 * @return
	 */
	List<IndusCompany> findByCompanyName(String companyName);

	/**
	 * 根据公司全名查看公司信息
	 * @param company
	 * @return
	 */
	List<IndusCompany> findByCompany(String company);

	/**
	 * @return
	 */
	@Query(value="select * from t_indus_company where t_company_name is null" ,nativeQuery = true)
	List<IndusCompany> getCompanyInfo();
	
}
