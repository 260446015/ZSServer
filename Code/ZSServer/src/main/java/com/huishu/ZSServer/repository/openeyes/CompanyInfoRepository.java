package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.CompanyInfo;

public interface CompanyInfoRepository extends CrudRepository<CompanyInfo, String> {

	List<CompanyInfo> findByCompanyName(String cname);

}
