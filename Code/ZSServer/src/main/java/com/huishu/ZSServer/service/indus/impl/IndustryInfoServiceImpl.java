package com.huishu.ZSServer.service.indus.impl;

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
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.es.repository.BaseElasticsearch;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.indus.IndustryInfoService;
import com.merchantKey.articleToKeywordCloud.ArticleConToKeywordCloud;
import com.merchantKey.itemModel.KeywordModel;

/**
 * @author hhy
 * @date 2017年10月31日
 * @Parem
 * @return 产业动态的实现方法
 */
@SuppressWarnings("rawtypes")
@Service
public class IndustryInfoServiceImpl extends AbstractService implements IndustryInfoService {

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
		BoolQueryBuilder bq = new BoolQueryBuilder();
		if (StringUtil.isNotEmpty(json.getString("dimension"))) {
			String dimension = json.getString("dimension");
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		if (StringUtil.isNotEmpty(json.getString("startTime")) && StringUtil.isNotEmpty(json.getString("endTime"))) {
			String startTime = json.getString("startTime");
			String endTime = json.getString("endTime");
			bq.must(QueryBuilders.rangeQuery("publishTime").from(startTime).to(endTime));
		}
		BoolQueryBuilder or = new BoolQueryBuilder();
		JSONArray arr = json.getJSONArray("industryLabel");
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				or.should(QueryBuilders.termQuery("industryLabel",str ));
			}
		}
		bq.must(or);
		PageRequest pageRequest = new PageRequest(0,500);
		SearchQuery query = getSearchQueryBuilder().withQuery(bq).withPageable(pageRequest).build();
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

			JSONObject keywordCloud = ArticleConToKeywordCloud.toKeywordCloud(contentList, 0, 12);
			if (keywordCloud.getBooleanValue("status")) {

				List<KeywordModel> list = (List<KeywordModel>) keywordCloud.get("result");
				list.forEach(KeywordModel -> {
					String name = KeywordModel.getName();
					if (name.equals("/n ")) {
						list.remove(KeywordModel);
					}
					jsonArray.add(KeywordModel);
				});
			}

			return jsonArray;
		});
		return data;
	}

	


	/**
	 * 根据关键词查询文章列表
	 */
	@Override
	public JSONArray getArticleListByKeyWord(JSONObject obj) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		if (StringUtil.isNotEmpty(obj.getString("dimension"))) {
			String dimension = obj.getString("dimension");
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		BoolQueryBuilder or = new BoolQueryBuilder();
		JSONArray arr = obj.getJSONArray("industryLabel");
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				or.should(QueryBuilders.termQuery("industryLabel",str ));
			}
		}
		bq.must(or);
		if (StringUtil.isNotEmpty(obj.getString("keyWord"))) {
			String keyWord = obj.getString("keyWord");
			bq.must(QueryBuilders.wildcardQuery("content", "*" + keyWord + "*"));
		}
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
				jsonObject.put("articleLink", map.get("articleLink").toString());
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
		BoolQueryBuilder bq = new BoolQueryBuilder();
		BoolQueryBuilder or = new BoolQueryBuilder();
		JSONArray arr = json.getJSONArray("industry");
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				or.should(QueryBuilders.termQuery("industryLabel",str ));
			}
		}
		bq.must(or);
		String area = json.getString("area");
		if(StringUtil.isNotEmpty(area)){
			bq.must(QueryBuilders.wildcardQuery("area", "*"+area+"*"));
		}
		String type = json.getString("type");
		Pageable pageable = null;
		Page<AITInfo> page = null;
		if(type.equals("按时间")){
			//按时间排序
			pageable = new PageRequest(json.getIntValue("pageNumber"), json.getIntValue("pageSize"), new Sort(Direction.DESC, "publishTime"));
			page = rep.search(bq, pageable);
		}else{
			pageable = new PageRequest(json.getIntValue("pageNumber"), json.getIntValue("pageSize"),new Sort(Direction.ASC, "hitCount"));
			 page = rep.search(bq, pageable);
		}
		return page;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Page<AITInfo> findResearchResultList(JSONObject json) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		if (StringUtil.isNotEmpty(json.getString("dimension"))) {
			String dimension = json.getString("dimension");
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		BoolQueryBuilder or = new BoolQueryBuilder();
		JSONArray arr = json.getJSONArray("industryLabel");
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				or.should(QueryBuilders.termQuery("industryLabel", jso.getString("value")));
			}
		}
		bq.must(or);
		Pageable pageable = new PageRequest(0, 6,new Sort(Direction.DESC, "publishTime"));
		SearchQuery query = getSearchQueryBuilder().withQuery(bq).withPageable(pageable).withSort(SortBuilders.fieldSort("hitCount")).build();
		Page<AITInfo> search = template.queryForPage(query, AITInfo.class);
//		Page<AITInfo> search = rep.search(bq, pageable);
		search.getContent().forEach(action->{
			List<String> list = null;
			try {
				list = action.getBus();
				if(action.getBus().size()==0){
					List business = getBusiness(action.getTitle(),action.getContent());
					action.setBus(business);
				}
			} catch (Exception e) {
				list =  getBusiness(action.getTitle(),action.getContent());
				action.setBus(list);
			}
			if(action.getIndustryLabel().equals("生物医药")){
				action.setIndustryLabel("生物技术");
			}
			
				String content = action.getContent();
				if(content.length()>300){
					String replaceHtml = StringUtil.replaceHtml(content.substring(0, 300));
					
					action.setContent(replaceHtml);
				}else{
					String replaceHtml = StringUtil.replaceHtml(content.substring(0, content.length()));
					
					action.setContent(replaceHtml);
			}
			
		});
		 return search;
	}

	
}
