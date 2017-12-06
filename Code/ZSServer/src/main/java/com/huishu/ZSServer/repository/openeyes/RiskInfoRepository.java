package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.RiskInfo;

public interface RiskInfoRepository extends CrudRepository<RiskInfo, Long>{

	List<RiskInfo> findByCname(String cname);

}
