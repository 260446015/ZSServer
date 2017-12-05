package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.ShareStructure;

public interface ShareStructureRepository extends CrudRepository<ShareStructure, String> {

	List<ShareStructure> findByCname(String cname);

}
