package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.CheckInfo;

public interface CheckInfoRepository extends CrudRepository<CheckInfo, String> {

	List<CheckInfo> findByCname(String cname);

}
