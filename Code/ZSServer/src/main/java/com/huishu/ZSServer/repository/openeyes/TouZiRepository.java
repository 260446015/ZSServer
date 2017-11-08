package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.TouZi;

public interface TouZiRepository extends CrudRepository<TouZi, Long> {

	List<TouZi> findByCompany(String cname);

}
