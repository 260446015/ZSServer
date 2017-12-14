package com.huishu.ZSServer.repository.company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.Company;

public interface CompanyRepository extends CrudRepository<Company, Long>, JpaSpecificationExecutor<Company> {


	/**
	 * @param area
	 * @param industry
	 * @return
	 */
	
	@Query(value ="SELECT company_name FROM t_company_data t WHERE t.area like ?1 and t.industry = ?2 ", nativeQuery = true)
	List<String> findByAreaAndIndustry(String area, String industry);
	
	Company findByCompanyName(String cname);

	List<Company> findByPark(String gardenName);
	
	@Query(value="select count(*) from t_company_data where park=?1", nativeQuery = true)
	int findCountByPark(String gardenName);

	@Query(value="select open_industry,count(open_industry) from t_company_data where park=?1 group by open_industry", nativeQuery = true)
	List<Object[]> findEcharts(String gardenName);

	
}
