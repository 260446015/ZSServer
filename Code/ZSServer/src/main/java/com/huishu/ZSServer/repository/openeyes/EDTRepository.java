package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.EDT;

public interface EDTRepository extends CrudRepository<EDT, Long>{

	List<EDT> findByName(String name);

}
