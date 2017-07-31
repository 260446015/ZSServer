package com.huishu.ait.service.ExpertOpinion.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.rangetree.SortedSetRangeTreeQuery;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.InternalTopHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.entity.Specialist;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.es.repository.ExpertOpinion.ExpertOpinionElasticsearch;
import com.huishu.ait.service.ExpertOpinion.ExpertOpinionService;


/**
 * @author yxq
 *	专家观点的实现类
 */
@Service
public class ExpertOpinionServiceImpl implements ExpertOpinionService {
	
	@Resource
	private Client client;
	@Autowired
	private ExpertOpinionElasticsearch expertOpinionElasticsearch;
	
	//根据条件获取专家观点信息
	public JSONArray getExertOpinionList(ExpertOpinionDTO requestParam){
		JSONArray data = new JSONArray();
		
		String industry = requestParam.getIndustry();
		String industryLabel = requestParam.getIndustryLabel();
		String publishDate = requestParam.getPublishDate();
		String hot = String.valueOf(requestParam.getHitCount());
		String publishDateTime = requestParam.getPublishDateTime();
		SearchRequestBuilder searchBuilder = ESUtils.getSearchRequestBuilder(client);
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.matchAllQuery());
		
		SearchRequestBuilder reqeustBuilder = searchBuilder.setQuery(bq).setSize(500);
		if (null != hot) {
			reqeustBuilder.addSort("hitCount", SortOrder.DESC);
		}
		if (null != publishDateTime) {
			reqeustBuilder.addSort("publishDateTime", SortOrder.DESC);
		}
			TermsBuilder inalityBuilder = AggregationBuilders.terms("industry").field("industry");
			TermsBuilder industryLabelBuilder = AggregationBuilders.terms("industryLabel").field("industryLabel");
			inalityBuilder.subAggregation(industryLabelBuilder);
			reqeustBuilder.addAggregation(inalityBuilder);
		SearchResponse actionGet = reqeustBuilder.execute().actionGet();
		System.out.println(reqeustBuilder);
		//bq.must(QueryBuilders.termQuery(name, value));
		/*SearchResponse actionGet = searchBuilder.setQuery(bq).execute().actionGet();*/
		
		Terms agg = actionGet.getAggregations().get("industry");
		/*for (Terms.Bucket entry : agg.getBuckets()) {
			InternalTopHits terms = (InternalTopHits)entry.getAggregations().get("industryLabel");
			SearchHits hits = terms.getHits();
			long totalHits = hits.getTotalHits();
			for (SearchHit searchHit : hits) {
				Map<String, Object> source = searchHit.getSource();
				data.add(source);
			}
			
		}*/
		SearchHits hits = actionGet.getHits();
		if (null !=hits ) {
			for (SearchHit hit : hits) {
				Map<String, Object> source = hit.getSource();
					if (null != source && source.size() > 0 ) {
						JSONObject jsonObject = new JSONObject();
						source.put("_id", hit.getId());
						Set<Entry<String, Object>> entrySet = source.entrySet();
							for (Entry<String, Object> entry : entrySet) {
								jsonObject.put(entry.getKey(), entry.getValue());
							}
							data.add(jsonObject);
				}
			}
		}
		return data;
	}
	
	@Override
	public JSONArray findExpertOpinionByAuthor(ExpertOpinionDTO requestParam) {
		
		JSONArray data = new JSONArray();
		String author = requestParam.getAuthor();
		
		SearchRequestBuilder requestBuilder = ESUtils.getSearchRequestBuilder(client);
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.matchAllQuery());
		if (StringUtils.isNotBlank(author)) {
			bq.must(QueryBuilders.termQuery("author", author));
		}
		SearchResponse actionGet = requestBuilder.setQuery(bq).addSort("publishDateTime", SortOrder.DESC).setSize(50).execute().actionGet();
		SearchHits hits = actionGet.getHits();
		if (null != hits) {
			for (SearchHit hit : hits) {
				Map<String, Object> source = hit.getSource();
				if (null!= source && source.size() > 0) {
					source.put("_id", hit.getId());
					JSONObject jsonObject = new JSONObject();
					Set<Entry<String, Object>> entrySet = source.entrySet();
					for (Entry<String, Object> entry : entrySet) {
						jsonObject.put(entry.getKey(), entry.getValue());
					}
					data.add(jsonObject);
				}
			
		}
			
			
		}
		
		return data;
	}

	@Override
	public AITInfo findExpertOpinionById(String id) {
		AITInfo aitinfo = expertOpinionElasticsearch.findOne(id);
		return aitinfo;
	}
}
