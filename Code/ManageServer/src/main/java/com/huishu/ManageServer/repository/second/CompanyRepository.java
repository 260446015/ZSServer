package com.huishu.ManageServer.repository.second;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;


/**
 * @author hhy
 * @date 2017年12月12日
 * @Parem
 * @return 
 * 
 */
public interface CompanyRepository  extends CrudRepository<CompanyEntity, Long>{
	
	@Query(value="select * from li_keywords ",nativeQuery=true)
	List<CompanyEntity> getInfo();
}
