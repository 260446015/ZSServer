package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.PunishmentInfo;

public interface PunishmentInfoRepository extends CrudRepository<PunishmentInfo, String>{

	List<PunishmentInfo> findByName(String cname);

}
