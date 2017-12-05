package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Bids;

public interface BidsRepository extends CrudRepository<Bids, String> {

	List<Bids> findByCname(String cname);

}
