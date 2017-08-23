package com.huishu.ait.service.Headlines.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.echart.Option;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.es.entity.dto.HeadlinesArticleListDTO;
import com.huishu.ait.es.repository.Headlines.HeadlinesElasticsearch;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.Headlines.HeadlinesService;
import com.huishu.ait.common.conf.DBConstant.Emotion;
import com.huishu.ait.common.util.UtilsHelper;

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
	@Autowired
	private HeadlinesElasticsearch   essearch;
	/**
	 *  产业头条--关键词云
	 */
	@Override
	public JSONArray getWordCloud(HeadlinesDTO headlinesDTO) {
	try {
		
		BoolQueryBuilder bq = getIndustryContentBuilder(headlinesDTO);
		
		return getCloudWordList(bq);
	} catch (Exception e) {
		logger.error("获取词云失败：",e);
		return null;
	}
	
	}

	/**
	 *  产业头条---云图
	 */
	@Override
	public JSONArray getCarClondChartList(HeadlinesDTO headlinesDTO) {
		try {
			BoolQueryBuilder bq = getIndustryContentBuilder(headlinesDTO);
			return getVectorDistribution(bq);
		} catch (Exception e) {
			logger.error("获取媒体云图失败：",e);
			return null;
		}
	}

	/**
	 * 产业头条--根据载体查询文章
	 */
	@Override
	public Page<HeadlinesArticleListDTO> findArticleByVector(HeadlinesDTO headlinesDTO) {
		try{
			BoolQueryBuilder bq = getIndustryContentBuilder(headlinesDTO);
			 Pageable pageable = new PageRequest(0, 10,new Sort(Direction.DESC, "hot"));
		    Page<HeadlinesArticleListDTO> page = getArticleRank(bq, null, pageable);
		       
			return page;
		}catch(Exception e ){
			logger.error("根据载体查询文章列表失败：",e);
			return null;
		}
	}

	/**
	 * 产业头条--根据id查看文章详情
	 */
	@Override
	public AITInfo findArticleById(String id) {
		try {
			 AITInfo findOne = essearch.findOne(id);
			return findOne;
		} catch (Exception e) {
			logger.error("查询文章详情失败：",e);
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
			 Pageable pageable = new PageRequest(0, 10,new Sort(Direction.DESC, "hot"));
		    Page<HeadlinesArticleListDTO> page = getArticleRank(bq, null, pageable);
			return page;
		} catch (Exception e) {
			logger.error("根据关键词查询文章失败：",e);
			return null;
		}
	}
	
	
}
