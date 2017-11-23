package com.huishu.ZSServer.repository.company;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.Enterprise;

/**
 * @author hhy
 * @date 2017年8月11日
 * @Parem
 * @return
 * 
 */
public interface EnterPriseRepository extends CrudRepository<Enterprise, Long> , JpaSpecificationExecutor<Enterprise>{
	@Query(value="SELECT company FROM t_enterprise t WHERE t.area = ?1 and t.industry = ?2 ORDER BY t.scoring DESC LIMIT 0,8" , nativeQuery = true)
	List<String> getCompanyNameByIndustryAndArea(String area, String industry);
}
