package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Dishonest;

public interface DishonestRepository extends CrudRepository<Dishonest, Long> {

	List<Dishonest> findByIname(String cname);

}
