package com.huishu.ait.service.headline;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.echart.Option;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.merchantKey.itemModel.KeywordModel;
import com.huishu.ait.es.entity.dto.ArticleListDTO;
import com.huishu.ait.es.entity.dto.HeadlinesArticleListDTO;

/**
 * @author hhy
 * @date 2017年7月27日
 * @Parem
 * @return
 * 
 */
public interface HeadlinesService {
	

	/**
	 * 产业头条--根据载体查询文章
	 * 
	 * @param id
	 * @return
	 */
	Page<HeadlinesArticleListDTO> findArticleByVector(HeadlinesDTO headlinesDTO);

	/**
	 * 根据关键词来查询文章列表
	 * 
	 * @param headlinesDTO
	 * @return
	 */
	Page<HeadlinesArticleListDTO> findArticleByKeyWord(HeadlinesDTO headlinesDTO);

	/**
	 * @param param
	 * @return
	 */
	Page<ArticleListDTO> findArticleList(JSONObject param);

	/**
	 * @param param
	 * @return
	 */
	List<KeywordModel> findArticleKeyword(JSONObject param);
}
