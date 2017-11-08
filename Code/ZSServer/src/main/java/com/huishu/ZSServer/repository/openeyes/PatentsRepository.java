package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Patents;

public interface PatentsRepository extends CrudRepository<Patents, Long> {

	List<Patents> findByApplicantName(String cname);

}
