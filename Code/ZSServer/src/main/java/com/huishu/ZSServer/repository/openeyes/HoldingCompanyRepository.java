package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.HoldingCompany;

public interface HoldingCompanyRepository extends CrudRepository<HoldingCompany, Long> {

	List<HoldingCompany> findByCname(String cname);

}
