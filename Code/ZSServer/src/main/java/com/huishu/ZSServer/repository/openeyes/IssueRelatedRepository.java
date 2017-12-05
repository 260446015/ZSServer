package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.IssueRelated;

public interface IssueRelatedRepository extends CrudRepository<IssueRelated, String> {

	List<IssueRelated> findByCname(String cname);

}
