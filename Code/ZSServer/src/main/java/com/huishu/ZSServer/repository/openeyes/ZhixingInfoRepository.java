package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.ZhixingInfo;

public interface ZhixingInfoRepository extends CrudRepository<ZhixingInfo, String> {

	List<ZhixingInfo> findByCname(String cname);

}
