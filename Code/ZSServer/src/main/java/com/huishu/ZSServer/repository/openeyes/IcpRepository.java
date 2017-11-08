package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Icp;

public interface IcpRepository extends CrudRepository<Icp, Long>{

	List<Icp> findByCompanyName(String cname);

}
