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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.controller.SpecialistController;
import com.huishu.ait.entity.ExpertOpinionDetail;
import com.huishu.ait.entity.Specialist;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.es.repository.ExpertOpinion.ExpertOpinionElasticsearch;
import com.huishu.ait.repository.expertOpinionDetail.ExpertOpinionDetailRepository;
import com.huishu.ait.service.ExpertOpinion.ExpertOpinionService;

import scala.annotation.meta.setter;


/**
 * @author yxq
 *	专家观点的实现类
 */
@Service
public class ExpertOpinionServiceImpl implements ExpertOpinionService {
	
	private static Logger log = LoggerFactory.getLogger(ExpertOpinionServiceImpl.class);
	
	@Resource
	private Client client;
	@Autowired
	private ExpertOpinionElasticsearch expertOpinionElasticsearch;
	@Resource
	private ExpertOpinionDetailRepository expertOpinionDetailRepository;
	
	//根据条件获取专家观点信息
	public JSONArray getExertOpinionList(ExpertOpinionDTO requestParam){
		try {
			JSONArray data = new JSONArray();
			//获取参数
			String industry = requestParam.getIndustry();
			String industryLabel = requestParam.getIndustryLabel();
			String publishDate = requestParam.getPublishDate();
			String sortByHotFlag = requestParam.getSortByHotFlag();
			String sortByTimeFlag = requestParam.getSortByTimeFlag();
			String publishDateTime = requestParam.getPublishDateTime();
			String startDate = requestParam.getStartDate();
			String endDate = requestParam.getEndDate();
			
			SearchRequestBuilder searchBuilder = ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = QueryBuilders.boolQuery();
			bq.must(QueryBuilders.matchAllQuery());
			//根据条件查询
			SearchRequestBuilder requestBuilder = searchBuilder.setQuery(bq).setSize(500);
			if (StringUtils.isNotEmpty(industry)) {
				bq.must(QueryBuilders.termQuery("industry", industry));
			}
			if (StringUtils.isNotBlank(industryLabel)) {
				bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
			}
			if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
				bq.must(QueryBuilders.rangeQuery("publishDate").from(startDate).to(endDate));
			}
			
			//点击根据热度或者发布时间排序
			if (null != sortByHotFlag) {
				requestBuilder.addSort("hitCount", SortOrder.DESC);
			}
			if (null != sortByTimeFlag) {
				requestBuilder.addSort("publishDateTime", SortOrder.DESC);
			}
			SearchResponse actionGet = requestBuilder.execute().actionGet();
			//bq.must(QueryBuilders.termQuery(name, value));
			/*SearchResponse actionGet = searchBuilder.setQuery(bq).execute().actionGet();*/
			
			/*Terms agg = actionGet.getAggregations().get("industry");
			for (Terms.Bucket entry : agg.getBuckets()) {
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
		} catch (Exception e) {
			log.error("查询失败：",e);
			return null;
		}
	}
	
	/* 
	 * 根据姓名查询专家观点列表并根据时间进行排序
	 */
	@Override
	public JSONArray findExpertOpinionByAuthor(ExpertOpinionDTO requestParam) {
		
		try {
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
		} catch (Exception e) {
			log.error("查询失败：",e);
			return null;
		}
	}

	@Override
	public JSONObject findExpertOpinionById(String id) {
		try {
			JSONObject jsonObject = new JSONObject();
			AITInfo aitInfo = expertOpinionElasticsearch.findOne(id);
			jsonObject = (JSONObject) JSONObject.toJSON(aitInfo);
			return jsonObject;
		} catch (Exception e) {
			log.error("查询失败：",e);
			return null;
		}
	}
	
	/**
	 * 收藏专家观点
	 */
	public Boolean expertOpinionCollect(ExpertOpinionDTO param){
		Boolean flag= true;
		try {
			ExpertOpinionDetail expertOpinionDetail = new ExpertOpinionDetail();
			expertOpinionDetail.setId(param.getId());
			expertOpinionDetail.setAuthor(param.getAuthor());
			expertOpinionDetail.setPublishTime(param.getPublishDateTime());
			expertOpinionDetail.setTitle(param.getTitle());
			expertOpinionDetail.setContent(param.getContent());
			expertOpinionDetail.setSource(param.getSource());
			expertOpinionDetail.setSourceLink(param.getSourceLink());
			expertOpinionDetail.setIndustry(param.getIndustry());
			expertOpinionDetail.setLanmu(param.getLanmu());
			expertOpinionDetailRepository.save(expertOpinionDetail);
			return flag;
		} catch (Exception e) {
			flag=false;
			log.error("收藏失败：",e);
			return flag;
		}
	}
}
