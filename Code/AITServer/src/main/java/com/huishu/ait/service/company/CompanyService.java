package com.huishu.ait.service.company;



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

	/**
	 * 根据企业排行榜id查询详细
	 * @param coid 传递企业排行榜的id
	 * @return
	 */
	JSONObject findCompanieOrderById(String coid);
}
