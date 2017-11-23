package com.huishu.ZSServer.service.indus;

import java.util.List;

import com.huishu.ZSServer.common.Data;
import com.huishu.ZSServer.entity.IndustryRank;

/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 
 */
public interface IndustryRankService {
	/**
	 * 获取排行前十的产业数据
	 * @param industry
	 * @return
	 */
	List<IndustryRank> findIndustryRank(String industry);

	/**
	 * 获取地域所有数据
	 * @param industry
	 * @return
	 */
	List<Data> findMapInfo(String industry);
}
