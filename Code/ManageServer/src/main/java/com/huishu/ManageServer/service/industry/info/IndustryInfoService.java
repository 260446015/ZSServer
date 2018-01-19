package com.huishu.ManageServer.service.industry.info;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ManageServer.es.entity.AITInfo;

/**
 * @author hhy
 * @date 2018年1月18日
 * @Parem
 * @return 
 * 产业动态service接口
 */
public interface IndustryInfoService {

	/**
	 * 获取科研成果的相关数据
	 * @param json
	 * @return
	 */
	List<AITInfo> findResearchList(JSONObject json);

	/**
	 * @param json
	 * @return
	 */
	Page<AITInfo> getIndustryInfoByPage(JSONObject json);

	/**
	 * @param obj1
	 * @return
	 */
	JSONArray getKeyWordList(JSONObject obj1);

	/**
	 * @param time
	 * @param keyWord
	 * @return
	 */
	JSONArray findArticleInfo(String time, String keyWord);

	

}
