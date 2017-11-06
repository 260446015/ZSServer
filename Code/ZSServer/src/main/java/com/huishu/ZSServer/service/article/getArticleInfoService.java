package com.huishu.ZSServer.service.article;

import com.huishu.ZSServer.es.entity.AITInfo;

/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 * 
 */
public interface getArticleInfoService {
	AITInfo findArticleById(String id);
}
