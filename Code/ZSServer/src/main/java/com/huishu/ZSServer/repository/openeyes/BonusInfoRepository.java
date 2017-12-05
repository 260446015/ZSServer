package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.BonusInfo;

public interface BonusInfoRepository extends CrudRepository<BonusInfo, String> {

	List<BonusInfo> findByCname(String cname);

}
