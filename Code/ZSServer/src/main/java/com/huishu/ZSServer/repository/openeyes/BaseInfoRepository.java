package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.BaseInfo;

public interface BaseInfoRepository extends CrudRepository<BaseInfo, Long> {

	List<BaseInfo> findByName(String name);

}
