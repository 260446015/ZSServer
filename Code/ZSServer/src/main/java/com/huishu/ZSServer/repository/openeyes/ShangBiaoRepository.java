package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.ShangBiao;

public interface ShangBiaoRepository extends CrudRepository<ShangBiao, Long>{

	List<ShangBiao> findByApplicantCn(String cname);

}
