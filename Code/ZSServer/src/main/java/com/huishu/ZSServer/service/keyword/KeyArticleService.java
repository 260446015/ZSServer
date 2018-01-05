package com.huishu.ZSServer.service.keyword;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ZSServer.entity.KeywordArticle;

/**
 * @author hhy
 * @date 2018年1月4日
 * @Parem
 * @return 
 * 
 */
public interface KeyArticleService {

	/**
	 * 保存所有关键词文章
	 * @param info
	 * @return
	 */
	boolean saveInfo(List<KeywordArticle> info);

	/**
	 * @param time
	 * @param keyWord
	 * @return
	 */
	JSONArray findArticleInfo(String time, String keyWord);

}
