package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Inverst;

public interface InverstRepository extends CrudRepository<Inverst, Long> {

	List<Inverst> findByCname(String cname);

}
