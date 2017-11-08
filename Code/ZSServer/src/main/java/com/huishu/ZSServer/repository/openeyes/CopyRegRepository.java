package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.CopyReg;

public interface CopyRegRepository extends CrudRepository<CopyReg, Long>{

	List<CopyReg> findByAuthorNationality(String cname);

}
