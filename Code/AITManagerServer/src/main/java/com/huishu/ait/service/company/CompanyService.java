package com.huishu.ait.service.company;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.entity.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
	/**
	 * 查询企业排行
	 * 
	 * @param dto
	 * @return
	 */
	JSONArray findCompaniesOder(CompanyDTO dto);

	List<String> findCname(String park);
}
