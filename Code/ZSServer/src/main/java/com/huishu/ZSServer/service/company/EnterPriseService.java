package com.huishu.ZSServer.service.company;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 
 */
public interface EnterPriseService {

	/**
	 * @param area
	 * @param industry
	 * @return
	 * 根据地域和产业查看公司详情
	 */
	List<String> findCompanyName(String area, String industry);

	/**
	 * @param company
	 * @return
	 * 获取公司信息
	 */
	JSONObject getCompanyInfoByCompany(String company);

}
