package com.huishu.ZSServer.service.article.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.es.repository.BaseElasticsearch;
import com.huishu.ZSServer.service.article.getArticleInfoService;

/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 * 
 */
@Service
public class getArticleInfoServiceImpl  implements getArticleInfoService{

	@Autowired
	private BaseElasticsearch  search;
	@Override
	public AITInfo findArticleById(String id) {
		
		return search.findOne(id);
	}

}
