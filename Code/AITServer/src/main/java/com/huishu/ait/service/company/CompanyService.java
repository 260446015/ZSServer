package com.huishu.ait.service.company;



import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.entity.dto.CompanyDTO;

public interface CompanyService {

	JSONArray findCompaniesOder(CompanyDTO dto);
}
