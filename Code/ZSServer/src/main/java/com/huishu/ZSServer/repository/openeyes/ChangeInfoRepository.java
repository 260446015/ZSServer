package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.ChangeInfo;

public interface ChangeInfoRepository extends CrudRepository<ChangeInfo, String> {

	List<ChangeInfo> findByCname(String cname);

}
