package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Holder;

public interface HolderRepository extends CrudRepository<Holder, Long>{

	List<Holder> findByCname(String cname);

}
