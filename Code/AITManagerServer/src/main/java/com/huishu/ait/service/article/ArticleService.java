package com.huishu.ait.service.article;

import com.alibaba.fastjson.JSONObject;
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
	JSONObject findArticleInfoById(String id, Long userId);

	/**
	 * 根据id修改文章发布时间
	 * @param id
	 * @param time
	 * @return
	 */
	Boolean modifyInfo(String id,String time);

	/**
	 * 保存新增的文章
	 * @param ait
	 * @return
	 */
	boolean saveArt(AITInfo ait);

	
	/**
	 * 收藏文章
	 * @param id
	 * @param userId
	 * @return
	 */
	JSONObject expertOpinionCollect(String id, Long userId);

	/**
	 * 取消收藏
	 * @param id
	 * @param userId
	 * @return
	 */
	JSONObject cancelExpertOpinionCollect(String id, Long userId);
	
	/**
	 * 置顶文章
	 * @param id
	 * @return
	 */
	boolean toTop(String id);
}
