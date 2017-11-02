package com.huishu.ZSServer.service.garden;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.CompanyAnnals;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;

/**
 * 融资快报
 * 
 * @author yindq
 * @date 2017年10月30日
 */
public interface AnalysisService {
	/**
	 * 企业融资分布
	 * @param park
	 * @return
	 */
	List<JSONObject> getFinancingDistribution(String park);
	/**
	 * 获取某轮次融资企业列表
	 * @param dto
	 * @return
	 */
	Page<Company> getCompanyList(CompanySearchDTO dto);
	/**
	 * 获取价值榜分布图    
	 * @param park
	 * @param type（年产值，年税收）
	 * @return
	 */
	List<JSONObject> getValueDistribution(String park,String type);
	/**
	 * 获取TOP企业
	 * @param park
	 * @param industry
	 * @return
	 */
	Page<CompanyAnnals> getTopCompany(String park,String industry);
}
