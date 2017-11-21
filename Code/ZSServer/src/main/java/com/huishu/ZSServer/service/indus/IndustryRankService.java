package com.huishu.ZSServer.service.indus;

import java.util.List;

import com.huishu.ZSServer.entity.IndustryRank;

/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 
 */
public interface IndustryRankService {
	List<IndustryRank> findIndustryRank(String industry);
}
