package com.huishu.ait.service.article.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.UserCollection;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.repository.ExpertOpinion.BaseElasticsearch;
import com.huishu.ait.repository.expertOpinionDetail.UserCollectionRepository;
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
	
	@Autowired
	private UserCollectionRepository userCollectionRepository;
	/**
	 * 删除文章通过id
	 */
	@Override
	public Boolean delArticleById(String[] ids) {
		try{
			for (String id : ids) {
				AITInfo findOne = search.findOne(id);
				if(findOne!= null){
					search.delete(id);
					logger.info("执行删除操作");
				}
			}
		}catch(Exception e){
			logger.error("删除文章内容失败!", e.getMessage());
			return false;
		}
		return true;

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
	public JSONObject findArticleInfoById(String id, Long userId) {
		JSONObject obj = new JSONObject();
		AITInfo one = search.findOne(id);
		List<String> list = getBusiness(one.getTitle(),one.getContent());
//		one.setBus(list);
		obj = (JSONObject) JSONObject.toJSON(one);
		obj.put("bus", list.toString());
		if (null == userCollectionRepository.findByArticleIdAndUserId(id, userId)) {
			obj.put("isCollect", "收藏");
		} else {
			obj.put("isCollect", "取消收藏");
		}
		return obj;
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
	@Override
	public JSONObject expertOpinionCollect(String articleId, Long userId) {
		JSONObject json = new JSONObject();
		try {
			AITInfo param = search.findOne(articleId);
			UserCollection findOne = userCollectionRepository.findByArticleIdAndUserId(articleId, userId);
			// 如果不为空先删除
			if (null != findOne) {
				userCollectionRepository.delete(findOne);
			}
			// 保存
			UserCollection uc = new UserCollection();
			uc.setArticleId(param.getId());
			uc.setAuthor(param.getAuthor());
			uc.setCollectTime(new Date().toString());
			uc.setPublishTime(param.getPublishTime());
			uc.setTitle(param.getTitle());
			uc.setContent(param.getContent());
			uc.setSource(param.getSource());
			uc.setSourceLink(param.getSourceLink());
			uc.setIndustry(param.getIndustry());
			uc.setLanmu(param.getDimension());
			uc.setUserId(userId);
			userCollectionRepository.save(uc);
			json.put("state", "success");
			return json;
		} catch (Exception e) {
			json.put("state", "failure");
			logger.error("收藏失败：", e.getMessage());
			return json;
		}
	}
	@Override
	public JSONObject cancelExpertOpinionCollect(String articleId, Long userId) {
		JSONObject json = new JSONObject();
		try {
			UserCollection findOne = userCollectionRepository.findByArticleIdAndUserId(articleId, userId);
			if (null == findOne) {
				json.put("state", "failure");
			}
			userCollectionRepository.delete(findOne);
			json.put("state", "success");
			return json;
		} catch (Exception e) {
			json.put("state", "failure");
			logger.error("取消收藏失败：", e.getMessage());
			return json;
		}
		
	}

}
