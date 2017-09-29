package com.huishu.ait.service.industrialPolicy;

import java.util.List;

import org.springframework.data.domain.Page;

//import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.huishu.ait.entity.IndustrialPolicy;
import com.huishu.ait.entity.dto.IndustrialPolicyDTO;
import com.huishu.ait.es.entity.dto.ArticleListDTO;

/**
 * 产业政策相关service
 * 
 * @author jdz
 * @version 1.0
 * @createDate 2017-7-28
 */
public interface IndustrialPolicyService {

	/**
	 * 根据参数，获取产业政策列表
	 * 
	 * @param dto
	 * @return
	 */
	JSONArray getIndustrialPolicyList(IndustrialPolicyDTO dto);

	/**
	 * 
	 */
	JSONArray getIndustrialList(IndustrialPolicyDTO dto);

	/**
	 * 根据产业政策id，获取其详细信息
	 * 
	 * @param id
	 */
	JSONObject getIndustrialPolicyDetail(String id, Long userId);
	

	/**
	 * @param param
	 * @return
	 */
	Page<ArticleListDTO> findArticleList(JSONObject param);
}

