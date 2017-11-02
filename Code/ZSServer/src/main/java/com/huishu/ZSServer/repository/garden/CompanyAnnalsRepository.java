package com.huishu.ZSServer.repository.garden;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.ZSServer.entity.CompanyAnnals;

/**
 * 企业年报
 * 
 * @author yindq
 * @date 2017年11月2日
 */
@Repository
public interface CompanyAnnalsRepository extends CrudRepository<CompanyAnnals, Long>, JpaSpecificationExecutor<CompanyAnnals>{
	
	@Query(value = "select sum(?1),year from t_company_annals where park=?2 group by year", nativeQuery = true)
	List<Object[]> countByYear(String park, String type);
}
