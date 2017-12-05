package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.ShareHolder;

public interface ShareHolderRepository extends CrudRepository<ShareHolder, String> {

	List<ShareHolder> findByCname(String cname);

}
