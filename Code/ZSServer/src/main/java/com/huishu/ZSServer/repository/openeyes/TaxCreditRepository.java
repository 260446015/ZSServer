package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.TaxCredit;

public interface TaxCreditRepository extends CrudRepository<TaxCredit, String>{

	List<TaxCredit> findByName(String cname);

}
