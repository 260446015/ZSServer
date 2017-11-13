package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Abnormal;

public interface AbnormalRepository extends CrudRepository<Abnormal, String>{

	List<Abnormal> findByCompanyName(String cname);

}
