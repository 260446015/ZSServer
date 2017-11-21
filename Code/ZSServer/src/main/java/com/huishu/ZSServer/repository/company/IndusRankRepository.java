package com.huishu.ZSServer.repository.company;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.IndustryRank;

/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 
 */
public interface IndusRankRepository extends CrudRepository<IndustryRank, Long> {

	/**
	 * @param industry
	 * @return
	 */
	List<IndustryRank> findByIndustry(String industry);

}
