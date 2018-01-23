package com.huishu.aitanalysis.repository.industry;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.aitanalysis.entity.IndustryInfo;


@Repository
public interface IndustryInfoRepository  extends CrudRepository<IndustryInfo, String>,JpaSpecificationExecutor<IndustryInfo>{
	@Query(value="select * from t_us_indus t where t.t_indus_three=?1", nativeQuery = true)
	IndustryInfo  findByIndustryThree(String industryThree);
}
