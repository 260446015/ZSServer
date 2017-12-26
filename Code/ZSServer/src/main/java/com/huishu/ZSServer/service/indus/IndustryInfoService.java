package com.huishu.ZSServer.service.indus;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.merchantKey.itemModel.KeywordModel;

/**
 * @author hhy
 * @date 2017年10月31日
 * @Parem
 * @return 
 * 关于产业动态接口文档
 */
public interface IndustryInfoService {
	
	/**  查询关键词云	 */
	JSONArray  getKeyWordList(JSONObject jsonArray);
	
	/**  查询关键词云	 */
	List<KeywordModel>  fiindKeyWordList(JSONObject jsonArray);
	/**
	 * 根据关键词查询文章列表
	 * @param obj
	 * @return
	 */
	JSONArray getArticleListByKeyWord(JSONObject obj);

	/**
	 * 分页查询产业资讯信息
	 * @param json
	 * @return
	 */
	Page<AITInfo> getIndustryInfoByPage(JSONObject json);

	/**
	 * 获取科研成果列表
	 * @param json
	 * @return
	 */
	Page<AITInfo> findResearchResultList(JSONObject json);

	/**
	 * @param json
	 * @return
	 * 获取科研成果列表
	 * 
	 */
	List<AITInfo> findResearchList(JSONObject json);
	
	
	
}
