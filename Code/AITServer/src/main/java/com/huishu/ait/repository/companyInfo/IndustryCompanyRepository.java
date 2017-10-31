package com.huishu.ait.repository.companyInfo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.IndusCompany;

/**
 * @author hhy
 * @date 2017年8月11日
 * @Parem
 * @return
 * 
 */
public interface IndustryCompanyRepository extends CrudRepository<IndusCompany, Long> {

	/**
	 * @param industry
	 */
	List<IndusCompany> findByIndustry(String industry);
	

}
