package com.huishu.ZSServer.service.keyword.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.KeyWordEntity;
import com.huishu.ZSServer.entity.KeywordArticle;
import com.huishu.ZSServer.repository.keyword.KeyArticleRepository;
import com.huishu.ZSServer.repository.keyword.KeyWordRepository;
import com.huishu.ZSServer.service.keyword.KeyArticleService;

/**
 * @author hhy
 * @date 2018年1月4日
 * @Parem
 * @return 
 * 关键词查文章相关service
 */
@Service
public class KeyArticleServiceImpl implements KeyArticleService {
	
	@Autowired
	private KeyArticleRepository rep;
	@Autowired
	private KeyWordRepository krep;
	/**
	 * 保存所有的文章信息
	 */
	@Override
	public boolean saveInfo(List<KeywordArticle> info) {
		if(info.size() != 0 ){
			Iterable<KeywordArticle> save = rep.save(info);
			if(save != null){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	/**
	 * 获取文章的相关信息
	 */
	@Override
	public JSONArray findArticleInfo(String time, String keyWord) {
		JSONArray arr = new JSONArray();
		KeyWordEntity entry = 	krep.findByTimeAndKey(time,keyWord);
		 if( entry != null){
			List<KeywordArticle> list = rep.findBykid( entry.getId());
			for(int i =0; i<10;i++){
				JSONObject jsonObject = new JSONObject();
				KeywordArticle ent = list.get(i);
				jsonObject.put("id", ent.getAid());
				jsonObject.put("industryLabel", ent.getIndustryLabel());
				jsonObject.put("title", ent.getTitle());
				jsonObject.put("articleLink", ent.getArticleLink());
				arr.add(jsonObject);
			}
			return arr;
		 }else{
			 return null;
		 }
	}

	/**
	 * 根据关键词id 删除数据
	 */
	@Override
	public boolean deleteByKid(Long id) {
		 List<KeywordArticle> list = rep.findBykid(id);
		 try {
			 if(list.size()>0){
				 rep.delete(list);
				 return true;
			 }else{
				 return false;
			 }
		} catch (Exception e) {
			list.forEach( act ->{
				rep.delete(act);
			});
			return true;
		}
	}
	
}
