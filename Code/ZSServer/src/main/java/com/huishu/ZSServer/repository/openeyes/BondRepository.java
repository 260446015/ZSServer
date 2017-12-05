package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Bond;

public interface BondRepository extends CrudRepository<Bond, Long> {

	List<Bond> findByCname(String cname);

}
