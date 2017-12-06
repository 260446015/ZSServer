package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Employment;

public interface EmploymentRepository extends CrudRepository<Employment, Long> {

	List<Employment> findByCompanyName(String cname);

}
