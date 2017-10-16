package com.huishu.ait.repository.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ait.entity.Company;

@Transactional
public interface CompanyRepository extends CrudRepository<Company, Long> {

	Page<Company> findByIndustryLikeAndParkAndRegisterCapitalBetween(String industry, String park, double start,
			double end, Pageable pageable);

	Page<Company> findByIndustryLikeAndParkAndCidInAndRegisterCapitalBetween(String industry, String park,
			List<Long> companyIds, double start, double end, Pageable pageable);

	List<Company> findByPark(String park);
	
	void deleteByCompanyName(String companyName);
			
}