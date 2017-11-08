package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.ProductInfo;

public interface ProductInfoRepository extends CrudRepository<ProductInfo, Long> {

	List<ProductInfo> findByCompanyName(String cname);

}
