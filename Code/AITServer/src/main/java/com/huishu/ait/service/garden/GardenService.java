package com.huishu.ait.service.garden;

import com.alibaba.fastjson.JSONObject;

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
	JSONObject getGardenPolicyList(String park);
	/**
	 * 根据政策ID获取政策详情
	 * @param id     政策ID
	 * @return
	 */
	JSONObject getGardenPolicyById(String id);
	/**
	 * 获取园区的动态列表
	 * @param park    园区名称
	 * @return
	 */
	JSONObject getGardenInformationList(String park);
	/**
	 * 根据动态ID获取动态详情
	 * @param id     动态ID
	 * @return
	 */
	JSONObject getGardenInformationById(String id);
}
