package com.huishu.ait.service.indusCompany;

import com.alibaba.fastjson.JSONArray;

/**
 * @author hhy
 * @date 2017年8月11日
 * @Parem
 * @return 
 * 智能企业service方法
 */
public interface IndusCompanyService {
	
	/**
	 * 根据产业信息查看企业信息
	 * @param industry
	 * @return
	 */
	JSONArray findIndusInfoByIndustry(String industry);

	/**
	 * 根据公司全名查看公司信息
	 * @param company
	 */
	JSONArray findInfo(String company);
}
