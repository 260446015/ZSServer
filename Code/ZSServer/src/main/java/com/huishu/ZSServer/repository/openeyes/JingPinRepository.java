package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.JingPin;

public interface JingPinRepository extends CrudRepository<JingPin, Long> {

	List<JingPin> findByCname(String cname);

}
