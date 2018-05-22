package com.huishu.ManageServer.repository.first;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ManageServer.entity.dbFirst.Company;

public interface GardenCompanyRepository extends CrudRepository<Company, Long>, JpaSpecificationExecutor<Company> {

	Company findByCompanyName(String cname);

	List<Company> findByPark(String gardenName);

	@Query(value = "select count(*) from t_company_data where park=?1", nativeQuery = true)
	int findCountByPark(String gardenName);

	@Query(value = "select open_industry,count(open_industry) from t_company_data where park=?1 group by open_industry", nativeQuery = true)
	List<Object[]> findEcharts(String gardenName);

	@Query(value = "select company_name from t_company_data where park = ?", nativeQuery = true)
	List<String> findCompanyNameByPark(String park);

}
