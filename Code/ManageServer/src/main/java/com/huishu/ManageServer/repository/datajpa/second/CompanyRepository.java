package com.huishu.ManageServer.repository.datajpa.second;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;


/**
 * @author hhy
 * @date 2017年12月12日
 * @Parem
 * @return 
 * 
 */
@Repository

public interface CompanyRepository  extends CrudRepository<CompanyEntity, Long>{
	/*@Modifying
	@Query(value="select * from CompanyEntity limit 0,10 ")
	List<CompanyEntity> getInfo();*/
}
