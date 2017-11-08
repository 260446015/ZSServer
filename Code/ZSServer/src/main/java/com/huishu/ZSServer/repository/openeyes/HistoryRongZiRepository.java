package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.HistoryRongZi;

public interface HistoryRongZiRepository extends CrudRepository<HistoryRongZi, Long>{

	List<HistoryRongZi> findByCompanyName(String companyName);

}
