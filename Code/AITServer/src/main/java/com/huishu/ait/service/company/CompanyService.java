package com.huishu.ait.service.company;



import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.dto.CompanyDTO;

public interface CompanyService {
	/**
	 * 查询企业排行
	 * @param dto
	 * @return
	 */
	JSONArray findCompaniesOder(CompanyDTO dto);

	List<String> findCname(String park);
}
