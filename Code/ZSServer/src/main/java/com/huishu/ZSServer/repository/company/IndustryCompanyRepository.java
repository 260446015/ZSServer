package com.huishu.ZSServer.repository.company;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.IndusCompany;

import java.util.List;


/**
 * @author hhy
 * @date 2018年2月3日
 * @Parem
 * @return 
 * 
 */
public interface IndustryCompanyRepository extends CrudRepository<IndusCompany,Long>,JpaSpecificationExecutor<IndusCompany> {

    List<IndusCompany> findByUserId(Long userId);
}
