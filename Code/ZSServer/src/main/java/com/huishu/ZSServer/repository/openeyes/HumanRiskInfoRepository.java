package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.HumanRiskInfo;

public interface HumanRiskInfoRepository extends CrudRepository<HumanRiskInfo, Long> {

	List<HumanRiskInfo> findByCompanyNameAndHumanName(String cname, String humanName);

}
