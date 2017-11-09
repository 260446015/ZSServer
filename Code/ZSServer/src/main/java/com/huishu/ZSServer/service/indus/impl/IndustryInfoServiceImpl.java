package com.huishu.ZSServer.service.indus.impl;

import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.TYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.es.repository.BaseElasticsearch;
import com.huishu.ZSServer.service.indus.IndustryInfoService;
import com.merchantKey.articleToKeywordCloud.ArticleConToKeywordCloud;
import com.merchantKey.itemModel.KeywordModel;

/**
 * @author hhy
 * @date 2017年10月31日
 * @Parem
 * @return 产业动态的实现方法
 */
@Service
public class IndustryInfoServiceImpl implements IndustryInfoService {

	private static Logger LOGGER = Logger.getLogger(IndustryInfoServiceImpl.class);

	@Autowired
	protected ElasticsearchTemplate template;
	
	@Autowired
	protected Client client;
	@Autowired
	protected BaseElasticsearch rep;
	/**
	 * 获取关键词云
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getKeyWordList(JSONObject json) {
		BoolQueryBuilder bq = getQueryBoolBuilder(json);
		TermsBuilder articleBuilder = AggregationBuilders.terms("articleLink").field("articleLink").size(1000);
		SearchQuery query = getSearchQueryBuilder().addAggregation(articleBuilder).withQuery(bq).build();
		LOGGER.info("");
		List<String> contentList = new ArrayList<String>();
		JSONArray data = template.query(query, res -> {
			JSONArray jsonArray = new JSONArray();
			SearchHits hits = res.getHits();

			if (hits != null) {
				SearchHit[] hitsList = hits.getHits();
				for (SearchHit h : hitsList) {
					if (h.getSource() != null) {
						contentList.add(h.getSource().get("content") + "");
					}
				}
			}

			JSONObject keywordCloud = ArticleConToKeywordCloud.toKeywordCloud(contentList, 0, 10);
			if (keywordCloud.getBooleanValue("status")) {

				List<KeywordModel> list = (List<KeywordModel>) keywordCloud.get("result");
				list.forEach(KeywordModel -> {
					String name = KeywordModel.getName();
					if (name.equals("/n ")) {
						list.remove(KeywordModel);
					}
				});
				jsonArray.add((List<KeywordModel>) keywordCloud.get("result"));
			}

			return jsonArray;
		});
		return data;
	}

	/**
	 * @param json
	 * @return
	 */
	private BoolQueryBuilder getQueryBoolBuilder(JSONObject json) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		if (StringUtil.isNotEmpty(json.getString("industry"))) {
			String industry = json.getString("industry");
			bq.must(QueryBuilders.termQuery("industry", industry));
		}
		if (StringUtil.isNotEmpty(json.getString("industryLabel"))) {
			String industryLabel = json.getString("industryLabel");
			bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
		}
		if (StringUtil.isNotEmpty(json.getString("area"))) {
			String area = json.getString("area");
			bq.must(QueryBuilders.wildcardQuery("area", "*" + area + "*"));
		}
		if (StringUtil.isNotEmpty(json.getString("startTime")) && StringUtil.isNotEmpty(json.getString("endTime"))) {
			String startTime = json.getString("startTime");
			String endTime = json.getString("endTime");
			bq.must(QueryBuilders.rangeQuery("publishTime").from(startTime).to(endTime));
		}
		if (StringUtil.isNotEmpty(json.getString("dimension"))) {
			String dimension = json.getString("dimension");
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		if (StringUtil.isNotEmpty(json.getString("keyWord"))) {
			String keyWord = json.getString("keyWord");
			bq.must(QueryBuilders.wildcardQuery("content", "*" + keyWord + "*"));
		}
		return bq;
	}

	/**
	 * 查询es库，获取更多条件查询
	 * 
	 * @return
	 */
	protected NativeSearchQueryBuilder getSearchQueryBuilder() {
		return new NativeSearchQueryBuilder().withIndices(INDEX).withTypes(TYPE);
	}

	/**
	 * 根据关键词查询文章列表
	 */
	@Override
	public JSONArray getArticleListByKeyWord(JSONObject obj) {
		BoolQueryBuilder bq = getQueryBoolBuilder(obj);
		TermsBuilder articleLinkBuilder = AggregationBuilders.terms("articleLink").field("articleLink")
				.order(Order.count(false)).size(500);

		SearchQuery authorQuery = getSearchQueryBuilder().withQuery(bq).addAggregation(articleLinkBuilder).build();

		JSONArray array = template.query(authorQuery, res -> {
			JSONArray jsonArray = new JSONArray();
			SearchHits hits = res.getHits();
			for (SearchHit hit : hits) {
				JSONObject jsonObject = new JSONObject();
				Map<String, Object> map = hit.getSource();
				jsonObject.put("id", hit.getId());
				jsonObject.put("industryLabel", map.get("industryLabel").toString());
				jsonObject.put("title", map.get("title").toString());
				jsonArray.add(jsonObject);
			}
			return jsonArray;
		});
		return array;
	}

	/**
	 * 
	 */
	
	@Override
	public Page<AITInfo> getIndustryInfoByPage(JSONObject json) {
		BoolQueryBuilder bq = getQueryBoolBuilder(json);
		String type = json.getString("type");
		Pageable pageable = null;
		Page<AITInfo> page = null;
		if(type=="1"){
			//按时间排序
			pageable = new PageRequest(0, 10, new Sort(Direction.DESC, "publishTime"));
			
//			 page = template.queryForPage(getSearchQueryBuilder().withQuery(bq).build(), AITInfo.class);
			page = rep.search(bq, pageable);
			
		}else{
//			pageable = new PageRequest(0, 10,new Sort(Direction.DESC, "hitCount"));
			pageable = new PageRequest(0, 10,new Sort(Direction.ASC, "hitCount"));
			
			 page = rep.search(bq, pageable);
			
		}
		
		return page;
	}

	
	@Override
	public Page<AITInfo> findResearchResultList(JSONObject json) {
		BoolQueryBuilder bq = getQueryBoolBuilder(json);
		Pageable pageable = new PageRequest(0, 10,new Sort(Direction.ASC, "hitCount"));
		Page<AITInfo> search = rep.search(bq, pageable);
		 return search;
	}

	
}
