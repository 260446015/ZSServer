package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.OwnTax;

public interface OwnTaxRepository extends CrudRepository<OwnTax, String>{

	List<OwnTax> findByName(String cname);

}
