package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.SeniorExecutive;

public interface SeniorExecutiveRepository extends CrudRepository<SeniorExecutive, String> {

	List<SeniorExecutive> findByCname(String cname);

}
