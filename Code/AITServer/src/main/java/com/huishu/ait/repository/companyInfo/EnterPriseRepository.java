package com.huishu.ait.repository.companyInfo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ait.entity.dto.EnterpriseDTO;

/**
 * @author hhy
 * @date 2017年8月11日
 * @Parem
 * @return 
 * 
 */
public interface EnterPriseRepository extends CrudRepository<EnterpriseDTO, Long> {

	/**
	 * @param company
	 * @return
	 */
	EnterpriseDTO findByCompany(String company);

}