package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Allotmen;

public interface AllotmenRepository extends CrudRepository<Allotmen, String> {

	List<Allotmen> findByCname(String cname);

}
