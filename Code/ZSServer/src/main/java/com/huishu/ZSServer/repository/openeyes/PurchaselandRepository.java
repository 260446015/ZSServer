package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Purchaseland;

public interface PurchaselandRepository extends CrudRepository<Purchaseland, Long> {

	List<Purchaseland> findByCname(String cname);

}
