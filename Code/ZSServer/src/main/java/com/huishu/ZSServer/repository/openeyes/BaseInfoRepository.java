package com.huishu.ZSServer.repository.openeyes;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.BaseInfo;

public interface BaseInfoRepository extends CrudRepository<BaseInfo, Long> {

	BaseInfo findByName(String name);

}
