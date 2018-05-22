package com.huishu.ManageServer.service.industry.summit;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.es.entity.SummitInfo;

/**
 * @author hhy
 * @date 2018年1月16日
 * @Parem
 * @return 
 * 产业峰会service
 */
public interface IndustrySummitService {

	/**
	 * 根据es获取产业地域数据
	 * @param industry
	 * @return
	 */
	List<String> getAreaLabel(String industry);

	/**
	 * @param json
	 * @return
	 */
	Page<SummitInfo> getIndustryList(JSONObject json);

	/**
	 * 查看峰会信息
	 * @param id
	 * @return
	 */
	SummitInfo findSummitInfoById(String id);

	/**
	 * 删除峰会信息
	 * @param aLong
	 * @return
	 */
	Boolean deleteSummitInfoById(String aLong);

	/**
	 * 
	 * @param enter
	 * @return
	 */
	boolean saveIndudustrySummitInfo(SummitInfo enter);

}
