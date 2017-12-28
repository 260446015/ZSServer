package com.huishu.ManageServer.repository.datajpa.first;

import java.util.List;

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
public interface EnterPriseRepository extends CrudRepository<Enterprise, Long> , JpaSpecificationExecutor<Enterprise>{
	@Modifying
	@Query(value="SELECT company FROM t_enterprise t WHERE t.area = ?1 and t.industry = ?2 ORDER BY t.scoring DESC LIMIT ?3,7" , nativeQuery = true)
	List<String> getCompanyNameByIndustryAndArea(String area, String industry,int i);
	
	@Modifying
	@Query(value="SELECT count(*) FROM t_enterprise t WHERE t.area = ?1 and t.industry = ?2 ORDER BY t.scoring DESC " , nativeQuery = true)
	int  getCount(String area, String industry);

	/**
	 * @param company
	 * @return
	 * 根据全称查看公司数据
	 */
	Enterprise findByCompany(String company);

	/**
	 * @param industry
	 * @param area
	 * @param object
	 * @param object2
	 * @return
	 */
	List<Enterprise> findByIndustryLikeAndAreaLikeAndRegisterTimeBetween(String industry, String area, String startTime,
			String endTime);
}