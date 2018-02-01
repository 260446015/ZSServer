package com.huishu.ManageServer.repository.first;

import java.util.List;

import com.huishu.ManageServer.entity.dbFirst.IndusCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ManageServer.entity.dbFirst.Enterprise;



/**
 * @author hhy
 * @date 2017年8月11日
 * @Parem
 * @return
 * 
 */
@Repository
public interface EnterPriseRepository extends CrudRepository<Enterprise, Long> {
	@Query(value="select * from t_enterprise limit ?,?",nativeQuery=true)
	List<Enterprise> findPage(int pageFrom , int pageSize);
	
	Enterprise findByCompany(String company);
}
