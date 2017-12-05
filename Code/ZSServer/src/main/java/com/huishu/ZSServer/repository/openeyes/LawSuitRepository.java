package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.LawSuit;

public interface LawSuitRepository extends CrudRepository<LawSuit, String> {

	List<LawSuit> findByCname(String cname);

}
