package com.huishu.ait.service.article;

import com.huishu.ait.es.entity.AITInfo;

/**
 * @author hhy
 * @date 2017年9月26日
 * @Parem
 * @return 
 * 
 */
public interface ArticleService {

	/**
	 * @param id
	 * @return
	 * 根据文章id,删除文章
	 */
	Boolean delArticleById(String[] ids);

	/**
	 * 根据文章id,更新情感
	 * @param id
	 * @return
	 */
	Boolean updateEmotion(String id);

	/**
	 * @param id
	 * @return
	 */
	AITInfo findArticleInfoById(String id);

	/**
	 * 保存新增的文章
	 * @param ait
	 * @return
	 */
	boolean saveArt(AITInfo ait);


}
