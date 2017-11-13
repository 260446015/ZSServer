package com.huishu.ZSServer.repository.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.Company;

public interface CompanyRepository extends CrudRepository<Company, Long>, JpaSpecificationExecutor<Company> {

	@Query(value = "from Company c where c.industry like ?1 and c.industryLabel like ?2 and c.scale between ?3 and ?4 and c.registerDate between ?5 and ?6 and c.registerCapital between ?7 and ?8 and c.invest like ?9")
	Page<Company> findCompanyList(String industry, String industryLable, Integer scale, Integer ecale, String startTime,
			String endTime, Double sregist, Double eregist, String invest, Pageable page);

	/**
	 * @param area
	 * @param industry
	 * @return
	 */
	
	@Query(value ="SELECT company_name FROM t_company_data t WHERE t.area like ?1 and t.industry = ?2 ", nativeQuery = true)
	List<String> findByAreaAndIndustry(String area, String industry);
	
	Company findByCompanyName(String cname);

	
}
