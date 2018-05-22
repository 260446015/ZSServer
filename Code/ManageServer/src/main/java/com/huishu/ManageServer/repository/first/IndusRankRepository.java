package com.huishu.ManageServer.repository.first;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huishu.ManageServer.entity.dbFirst.IndustryRank;


/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 
 */
public interface IndusRankRepository extends CrudRepository<IndustryRank, Long> , JpaSpecificationExecutor<IndustryRank>{

	/**
	 * 获取当前所有产业数据
	 * @param industry
	 * @return
	 */
	List<IndustryRank> findByIndustry(String industry);
	
	/**
	 * 获取当前产业下排名前十的地域
	 * @param industry
	 * @return
	 */
	@Query(value = "select * from t_indus_rank t where t.industry = ?1   order by t.count DESC limit 0, 10",nativeQuery = true)
	List<IndustryRank>  getTop10ByIndustry(String industry);
}
