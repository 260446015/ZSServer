package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Illegalinfo;

public interface IllegalinfoRepository extends CrudRepository<Illegalinfo, String>{

	List<Illegalinfo> findByCompanyName(String cname);

}
