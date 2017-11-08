package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.TeamMember;

public interface TeamMemberRepository extends CrudRepository<TeamMember, Long>{

	List<TeamMember> findByCompanyName(String cname);

}
