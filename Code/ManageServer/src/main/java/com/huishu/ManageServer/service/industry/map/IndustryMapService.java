package com.huishu.ManageServer.service.industry.map;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.entity.dbFirst.IndustryRank;

/**
 * @author hhy
 * @date 2018年1月15日
 * @Parem
 * @return 
 * 
 */
public interface IndustryMapService {

	
	/**
	 * 获取产业地图数据
	 * @param industry
	 * @return
	 */
	JSONObject findMapInfoByIndustry(String industry);

	/**
	 * @param rank
	 * @return
	 */
	boolean saveOrUpdateRank(IndustryRank rank);

	/**
	 * @param id
	 * @return
	 */
	Boolean deleteRankInfoById(String id);

}
