package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.Certificate;

public interface CertificateRepository extends CrudRepository<Certificate, String> {

	List<Certificate> findByCname(String cname);

}
