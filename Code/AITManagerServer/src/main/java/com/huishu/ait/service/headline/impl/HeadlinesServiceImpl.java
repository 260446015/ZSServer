package com.huishu.ait.service.headline.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.DateCheck;
import com.huishu.ait.es.entity.dto.ArticleListDTO;
import com.huishu.ait.es.entity.dto.HeadlinesArticleListDTO;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.headline.HeadlinesService;
import com.merchantKey.itemModel.KeywordModel;

/**
 * @author hhy
 * @date 2017年7月27日
 * @Parem
 * @return
 * 
 */
@Service
public class HeadlinesServiceImpl extends AbstractService implements HeadlinesService {

	private static Logger logger = LoggerFactory.getLogger(HeadlinesServiceImpl.class);

	
	/**
	 * 产业头条--根据载体查询文章
	 */
	@Override
	public Page<HeadlinesArticleListDTO> findArticleByVector(HeadlinesDTO headlinesDTO) {
		try {
			BoolQueryBuilder bq = getIndustryContentBuilder(headlinesDTO);
			Pageable pageable = new PageRequest(0, 8, new Sort(Direction.DESC, "hot"));
			Page<HeadlinesArticleListDTO> page = getArticleRank(bq, null, pageable);

			return page;
		} catch (Exception e) {
			logger.error("根据载体查询文章列表失败：", e);
			return null;
		}
	}

	/**
	 * 产业头条--根据关键词查询文章
	 */
	@Override
	public Page<HeadlinesArticleListDTO> findArticleByKeyWord(HeadlinesDTO headlinesDTO) {
		try {
			BoolQueryBuilder bq = getIndustryContentBuilder(headlinesDTO);
			Pageable pageable = new PageRequest(0, 10, new Sort(Direction.DESC, "hot"));
			Page<HeadlinesArticleListDTO> page = getArticleRank(bq, null, pageable);
			return page;
		} catch (Exception e) {
			logger.error("根据关键词查询文章失败：", e);
			return null;
		}
	}
	
	/**
	 * 根据请求参数获取文章列表
	 */
	@Override
	public Page<ArticleListDTO> findArticleList(JSONObject param) {
		String type = param.getString("type");
		if(type.equals("产业头条")||type.equals("产业云图")){
			param.put("dimension", "产业头条");
		}
		param.remove("type");
		String time = param.getString("time");
		param = DateCheck.dateCheck(time, param);
		param.remove("time");
		BoolQueryBuilder bq = getIndustryBuilder(param);
		Page<ArticleListDTO> list = getArtivleList(bq);
		list.forEach(action->{
		action.setContent(action.getContent().substring(0,300));
		});
		return list;
	}

	

	/**
	 * 根据条件获得关键词
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<KeywordModel> findArticleKeyword(JSONObject param) {
		String type = param.getString("type");
		if(type.equals("产业头条")||type.equals("产业云图")){
			param.put("dimension", "产业头条");
		}
		param.remove("type");
		String time = param.getString("time");
		param = DateCheck.dateCheck(time, param);
		param.remove("time");
		BoolQueryBuilder bq = getIndustryBuilder(param);
		JSONArray json = getCloudWordList(bq);
		List<KeywordModel> list = new ArrayList<KeywordModel>();
		Iterator<Object> iterator = json.iterator();
		while(iterator.hasNext()){
			List<KeywordModel> next = (List<KeywordModel>)iterator.next();
			Iterator<KeywordModel> iterator2 = next.iterator();
			while(iterator2.hasNext()){
				KeywordModel next2 = iterator2.next();
				
				list.add(next2);
			}
		}
		return list;
	}

	

	
}
