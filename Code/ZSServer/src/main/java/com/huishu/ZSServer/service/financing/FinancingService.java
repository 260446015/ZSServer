package com.huishu.ZSServer.service.financing;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.es.entity.FinancingInfo;

/**
 * 融资快报
 * 
 * @author yindq
 * @date 2017年11月1日
 */
public interface FinancingService {
	/**
	 * 获取融资企业列表
	 * @param dto
	 * @return
	 */
	Page<FinancingInfo> getCompanyList(CompanySearchDTO dto);
	/**
	 * 获取融资动态数据
	 * @return
	 */
	Page<FinancingInfo> getFinancingDynamic();
	/**
	 * 获取某产业融资企业推荐列表
	 * @param industry
	 * @return
	 */
	List<JSONObject> getFinancingCompany(List<String> industry);
	/**
	 * 获取融资柱状图   (周，月，季，年)
	 * @param type（week, month, season, year）
	 * @return
	 */
	List<JSONObject> getHistogram(String type);
}
