package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.EquityChange;

public interface EquityChangeRepository extends CrudRepository<EquityChange, String> {

	List<EquityChange> findByCname(String cname);

}
