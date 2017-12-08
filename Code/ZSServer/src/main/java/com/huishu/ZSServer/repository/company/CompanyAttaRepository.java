package com.huishu.ZSServer.repository.company;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.CompanyAttation;

public interface CompanyAttaRepository extends CrudRepository<CompanyAttation, Long> {

	CompanyAttation findByCompanyIdAndUserId(Long companyId, Long userId);

}
