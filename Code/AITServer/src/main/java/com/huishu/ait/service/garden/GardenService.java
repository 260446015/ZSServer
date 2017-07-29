package com.huishu.ait.service.garden;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.es.entity.GardenInformation;
import com.huishu.ait.es.entity.GardenPolicy;

/**
 * 全景辖区概览service
 * @author yindq
 * @date   2017-7-28
 */
public interface GardenService {
	/**
	 * 获取园区的政策列表
	 * @param park    园区名称
	 * @return
	 */
	JSONObject getGardenPolicyList(SearchModel searchModel);
	/**
	 * 根据政策ID获取政策详情
	 * @param id     政策ID
	 * @return
	 */
	GardenPolicy getGardenPolicyById(String id);
	/**
	 * 获取园区的动态列表
	 * @param park    园区名称
	 * @return
	 */
	JSONObject getGardenInformationList(SearchModel searchModel);
	/**
	 * 根据动态ID获取动态详情
	 * @param id     动态ID
	 * @return
	 */
	GardenInformation getGardenInformationById(String id);
}
