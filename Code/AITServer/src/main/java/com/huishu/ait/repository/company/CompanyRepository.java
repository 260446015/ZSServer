package com.huishu.ait.repository.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.huishu.ait.entity.Company;
import com.huishu.ait.entity.dto.CompanyDTO;

public interface CompanyRepository extends CrudRepository<Company, Long> {


//	@Query(value="from Company c where c.industry like CONCAT('%',:keyName,'%') and (c.registerCapital between :start and :end) and c.park = :park")
	Page<Company> findByIndustryAndParkAndRegisterCapitalBetween(String industry, String park,double start,double end,Pageable pageable);


	Page<Company> findByIndustryAndParkAndCidInAndRegisterCapitalBetween(String industry, String park,
			List<Long> companyIds, double start, double end, Pageable pageable);




}
