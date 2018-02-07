package com.huishu.ZSServer.service.company;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.IndusCompany;
import com.huishu.ZSServer.entity.dto.IndusCompanyDTO;

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

	/**
	 * @param industry
	 * @param money
	 * @param time
	 * @param area
	 * @return
	 */
//	List<IndusCompany> getCompanyList(String industry, String[] moneys, String[] times, String area);
	List<IndusCompanyDTO> getCompanyList(String industry, String[] moneys, String[] times, String area);

	/**
	 * 获取产业标签数据
	 * @param industry
	 * @param area
	 * @param money
	 * @param time
	 * @return
	 */
//	List<IndusCompany> findCompanyList(String industry, String area, String[] money, String[] time);
	List<IndusCompanyDTO> findCompanyList(String industry, String area, String[] money, String[] time);

	/**
	 * @param industry
	 * @return
	 * 根据产业获取地域信息
	 */
	List<String> getAareaByIndustry(String industry);

}
