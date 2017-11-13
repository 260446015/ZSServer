package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Branch;

public interface BranchRepository extends CrudRepository<Branch, Long>{

	List<Branch> findByCompanyName(String cname);

}
