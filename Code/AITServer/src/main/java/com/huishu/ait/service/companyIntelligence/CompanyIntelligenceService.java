package com.huishu.ait.service.companyIntelligence;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.es.entity.dto.CompanyIntelligenceDTO;

/**
 * @author yxq
 * @date 2017年8月4日
 * @功能描述：企业情报画像
 */
public interface CompanyIntelligenceService {
	/**
	 * @return
	 * 获取企业情报列表信息
	 */
	public JSONArray getCompanyIntelligenceList(CompanyIntelligenceDTO dto);
}
