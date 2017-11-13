package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.RiskDetail;

public interface RiskDetailRepository extends CrudRepository<RiskDetail, String>{

	List<RiskDetail> findByCompanyName(String cname);

}
