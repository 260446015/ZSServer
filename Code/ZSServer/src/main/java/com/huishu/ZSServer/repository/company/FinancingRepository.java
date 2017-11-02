package com.huishu.ZSServer.repository.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ZSServer.entity.Company;

/**
 * 融资企业
 * 
 * @author yindq
 * @date 2017年11月2日
 */
@Repository
public interface FinancingRepository extends CrudRepository<Company, Long>, JpaSpecificationExecutor<Company>{
	Page<Company> findByInvestAndPark(String invest,String park,Pageable request);
	
	@Query(value = "select * from t_company_data where industry in ?1 order by financing_amount desc limit 1,10", nativeQuery = true) 
	List<Company> findFinancingCompany(List<String> industry);
	
	/**
	 * 按周
	 * @param industry
	 * @return
	 */
	@Query(value = "select sum(financing_amount),week(financing_date) from t_company_data "
			+ "where industry=? and year(financing_date) = year(now()) GROUP BY week(financing_date);", nativeQuery = true)
	List<Object[]> countByWeek(String industry);
	
	/**
	 * 按月
	 * @param industry
	 * @return
	 */
	@Query(value = "select sum(financing_amount),month(financing_date) from t_company_data "
			+ "where industry=? and year(financing_date) = year(now()) GROUP BY month(financing_date);", nativeQuery = true)
	List<Object[]> countByMonth(String industry);
	
	/**
	 * 按季度
	 * @param industry
	 * @return
	 */
	@Query(value = "select sum(financing_amount),FLOOR((date_format(financing_date, '%m')+2)/3) from t_company_data "
			+ "where industry=? and year(financing_date) = year(now()) GROUP BY FLOOR((date_format(financing_date, '%m')+2)/3);", nativeQuery = true)
	List<Object[]> countBySeason(String industry);
	
	/**
	 * 按年
	 * @param industry
	 * @return
	 */
	@Query(value = "select sum(financing_amount),YEAR(financing_date) from t_company_data "
			+ "where industry=? GROUP BY YEAR(financing_date);", nativeQuery = true)
	List<Object[]> countByYear(String industry);
	/**
	 * 园区企业融资分布
	 * @param park
	 * @return
	 */
	@Query(value = "select count(1),invest from t_company_data where park=? and invest is not null GROUP BY invest;", nativeQuery = true)
	List<Object[]> countAboutfinancingRepository(String park);
}
