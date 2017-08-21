package com.huishu.ait.repository.company_group_middle;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ait.entity.CompanyGroupMiddle;

@Transactional
public interface CompanyGroupMiddleRepository extends CrudRepository<CompanyGroupMiddle, Long>{

	void deleteByCompanyId(Long companyId);
	
	List<CompanyGroupMiddle> findByGroupId(Long groupId);
	

}
