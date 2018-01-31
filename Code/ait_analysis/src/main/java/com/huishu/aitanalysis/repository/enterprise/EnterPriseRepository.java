package com.huishu.aitanalysis.repository.enterprise;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huishu.aitanalysis.entity.Enterprise;




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
