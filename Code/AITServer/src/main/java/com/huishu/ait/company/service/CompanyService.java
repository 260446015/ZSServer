package com.huishu.ait.company.service;



import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.entity.dto.CompanyDTO;

public interface CompanyService {

	JSONArray findCompaniesOder(CompanyDTO dto);
}
