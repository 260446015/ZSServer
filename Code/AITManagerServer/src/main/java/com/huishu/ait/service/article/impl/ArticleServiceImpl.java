package com.huishu.ait.service.article.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.repository.ExpertOpinion.BaseElasticsearch;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.article.ArticleService;

/**
 * @author hhy
 * @date 2017年9月26日
 * @Parem
 * @return 
 * 关于文章的相关操作
 */
@Service
public class ArticleServiceImpl extends AbstractService implements ArticleService {
	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	@Autowired
	private BaseElasticsearch  search;
	/**
	 * 删除文章通过id
	 */
	@Override
	public Boolean delArticleById(String id) {
		AITInfo findOne = search.findOne(id);
		if(findOne!= null){
			search.delete(id);
			logger.info("执行删除操作");
			
			return true;
		}else{
			return false;
		}

	}
	/**
	 * 更新文章的情感通过id
	 * negative消极  neutral中立   positive 积极的
	 */
	@Override
	public Boolean updateEmotion(String id) {
		AITInfo findOne = search.findOne(id);
		String emotion = findOne.getEmotion();
		if(emotion.equals("negative")){
			findOne.setEmotion("positive");
		}else if(emotion.equals("positive")){
			findOne.setEmotion("negative");
		}
		AITInfo save = search.save(findOne);
		if(save!=null){
			return true;
		}
		return false;
	}
	
	
	
	
	@Override
	public AITInfo findArticleInfoById(String id) {
		AITInfo one = search.findOne(id);
		List<String> list = getBusiness(one.getTitle(),one.getContent());
		one.setBus(list);
		return one;
	}
	@Override
	public boolean saveArt(AITInfo ait) {
		boolean flag = false;
		try{
			search.save(ait);
			flag = true;
		}catch(Exception e){
			logger.error("新增专家观点文章出错",e.getMessage());
		}
		return flag;
		
	}

}
