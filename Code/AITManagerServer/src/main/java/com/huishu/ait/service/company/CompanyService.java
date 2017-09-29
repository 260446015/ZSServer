package com.huishu.ait.service.company;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.dto.CompanyDTO;
import com.huishu.ait.es.entity.dto.ArticleListDTO;

public interface CompanyService {
	/**
	 * 查询企业排行
	 * 
	 * @param dto
	 * @return
	 */
	JSONArray findCompaniesOder(CompanyDTO dto);

	List<String> findCname(String park);

	/**
	 * @param param
	 * @return
	 */
	Page<ArticleListDTO> findArticleList(JSONObject param);
}
