package com.huishu.ZSServer.repository.company;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.CompanyAttation;

public interface CompanyAttaRepository extends CrudRepository<CompanyAttation, Long> {

	CompanyAttation findByCompanyIdAndUserId(Long companyId, Long userId);

	@Query(value="select industry from t_company_attation a LEFT JOIN t_baseinfo c on a.company_id=c.company_id where a.user_id=? GROUP BY industry" , nativeQuery = true)
	List<String> getGardenIndustry(Long userId);
	
	@Query(value="select base from t_company_attation a LEFT JOIN t_baseinfo c on a.company_id=c.company_id where a.user_id=? GROUP BY base" , nativeQuery = true)
	List<String> getGardenArea(Long userId);
}
