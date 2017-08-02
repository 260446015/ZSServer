package com.huishu.ait.service;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.DBConstant.Emotion;
import com.huishu.ait.common.util.UtilsHelper;
import com.huishu.ait.echart.Legend;
import com.huishu.ait.echart.Option;
import com.huishu.ait.echart.Tooltip;
import com.huishu.ait.echart.series.Pie;
import com.huishu.ait.echart.series.Serie.SerieData;
import com.huishu.ait.es.entity.dto.HeadlinesArticleListDTO;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;


/**
 * @author hhy
 * @date 2017年7月29日
 * @Parem
 * @return
 * 
 */
public abstract class AbstractService {

	@Autowired
	protected ElasticsearchTemplate template;

	/**
	 * 媒体聚焦--获取载体分布统计环形图
	 * 
	 * @param p
	 * @return
	 */
	protected Option getVectorDistribution(QueryBuilder queryBuilder) {
		
		TermsBuilder vectorBuilder = AggregationBuilders.terms("vector").field("vector");

		TermsBuilder articleBuilder = AggregationBuilders.terms("articleLink").field("articleLink");
		articleBuilder.subAggregation(vectorBuilder).size(1000);
		SearchQuery query = getSearchQueryBuilder().addAggregation(articleBuilder).withQuery(queryBuilder).build();

		Option result = template.query(query, res -> {
			Option opt = new Option().setTooltip(new Tooltip().setTrigger("item"));
			Pie<SerieData<Long>> pie = new Pie<>();
			pie.addRadiu("40%").addRadiu("55%");
			pie.setName("载体分布统计");
			Legend legend = new Legend();
			Terms articleLink = res.getAggregations().get("articleLink");
			String key = null;
			long count = 0;
			SerieData<Long> data = new SerieData<>(key, count);
			Map<String ,Object> map = new HashMap<String, Object>();
			if (articleLink != null) {
				for (Terms.Bucket e1 : articleLink.getBuckets()) {
					Terms vector = e1.getAggregations().get("vector");
					for (Bucket bucket : vector.getBuckets()) {
					    key = bucket.getKeyAsString();
						count = bucket.getDocCount();
						map.put(key, count);
					}
					
				}
				for(Entry<String,Object> entry : map.entrySet()){
					data.setName(entry.getKey());
					data.setValue((Long) entry.getValue());
					legend.addData(entry.getKey());
					pie.addData(data);
				}
			}
			opt.setLegend(legend);
			opt.addSeries(pie);
			return opt;
		});
		return result;
	}
     /**
      *  产业头条--关键词云
      */
	@SuppressWarnings("static-access" )
	protected JSONArray getCloudWordList(QueryBuilder queryBuilder,Integer wordCloudNums){
		List<String> contentList = new ArrayList<String>();
		JSONArray data = new JSONArray();
		SearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).build();
		data =  template.query(query, res ->{
			JSONArray jsonArray = new JSONArray();
			Integer  wordCloudNum = 50;
			if(wordCloudNums.SIZE>0){
				wordCloudNum = wordCloudNums;
			}
			SearchHits hits = res.getHits();
			if( hits != null){
				SearchHit[] hitsList = hits.getHits();
				for(SearchHit  h : hitsList){
				    if(h.getSource()!= null){
						contentList.add(h.getSource().get("content")+"");
					}
				}
			}
			//将从ES获取到的集合为参数，调用词云的方法
			/*JSONObject keywordCloud = ArticleConToKeywordCloud.toKeywordCloud1(contentList, 0, wordCloudNum, null);
			if(keywordCloud != null){
				JSONArray keyWordCloudArray = keywordCloud.getJSONArray("sort");
				for (int i=0,size=keyWordCloudArray.size(); i<size; i++) {
					JSONObject item = keyWordCloudArray.getJSONObject(i);
					JSONObject obj = new JSONObject();
					obj.put("word", item.getString("keyword"));
					int heat = Math.round(item.getDouble("count").floatValue());
					obj.put("heat", heat);
					jsonArray.add(obj);
				}
			}*/
			return  jsonArray;
		});
		return data;
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
	 * 建立查询条件筛选
	 */
	protected BoolQueryBuilder getIndustryContentBuilder(HeadlinesDTO headlinesDTO) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		/** 产业 */
		String industry = headlinesDTO.getIndustry();
		if (StringUtils.isNotEmpty(industry)) {
			bq.must(QueryBuilders.termQuery("industry", industry));
		}
		/** 产业标签 */
		String industryLabel = headlinesDTO.getIndustryLabel();
		if (StringUtils.isNotEmpty(industryLabel)) {
			bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
		}
		/** 载体 */
		String vector = headlinesDTO.getVector();
		if (StringUtils.isNotEmpty(vector)) {
			bq.must(QueryBuilders.termQuery("vector", vector));
		}
		/**关键词*/
		String keyword = headlinesDTO.getKeyword();
		if(StringUtils.isNotEmpty(keyword)){
			bq.must(QueryBuilders.fuzzyQuery("content", keyword));
		}
		/** 时间 */
		String startDate = headlinesDTO.getStartDate();
		String endDate = headlinesDTO.getEndDate();
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			bq.must(QueryBuilders.rangeQuery("publishDate").from(startDate).to(endDate));
		}
		return bq;
	}
	
	/**
	 * 今日头条，排行前十
	 * @param bq
	 * @param object
	 * @param pageable
	 * @return
	 */
	protected Page<HeadlinesArticleListDTO> getArticleRank(BoolQueryBuilder bq, Object object,
			Pageable pageable) {
		// 第一步：按照声量排序取前500个文章
		TermsBuilder articleLinkBuilder = AggregationBuilders.terms("articleLink").field("articleLink")
				.order(Order.count(false)).size(500);
		
		SearchQuery authorQuery = getSearchQueryBuilder().withQuery(bq).addAggregation(articleLinkBuilder)
				.build();

		    JSONArray jsonArray = template.query(authorQuery, res -> {
		    	JSONArray json = new JSONArray();
		    	JSONObject  abj = new JSONObject();
		    	Map<String, Object> Source= null;
		    	SearchHits hits = res.getHits();
		    	for(SearchHit hit :hits){
		    		Source = hit.getSource();
		    		Source.put("_id", hit.getId());
		    	}
		    	for(Entry<String, Object> entry : Source.entrySet()){
		    		abj.put(entry.getKey(), entry.getValue());
		    	}
		    	json.add(abj);
			return json;
		});
		 Iterator<Object> it = jsonArray.iterator();
	// 第二步：根据文章名与查询条件计算每篇文章的声量，总阅读量，热度，情感值
		List<HeadlinesArticleListDTO> contents = new ArrayList<>();
		while(it.hasNext()){
			JSONObject obj = (JSONObject)it.next();
			BoolQueryBuilder bool = QueryBuilders.boolQuery();
			bool.must(bq);
			bool.must(QueryBuilders.termQuery("articleLink", obj.get("articleLink").toString()));
			
			HeadlinesArticleListDTO personage = getArticleRankByQuery(bool, obj);
					contents.add(personage) ;
		
		}	
		// 第三步：对结果进行排序，按照热度排序，分页取十条数据
				int total = contents.size();
				int pageNumber = pageable.getPageNumber();
				int pageSize = pageable.getPageSize();

				pageable.getSort().forEach(order -> {
					String property = order.getProperty();
					Direction direction = order.getDirection();
					contents.sort((o1, o2) -> {
						Double v1 = (Double) UtilsHelper.getValueByFieldName(o1, property);
						Double v2 = (Double) UtilsHelper.getValueByFieldName(o2, property);

						if (Direction.ASC.equals(direction)) {
							return v1.compareTo(v2);
						}
						return v2.compareTo(v1);
					});
				});

				for (int i = 0; i < contents.size(); i++) {
					contents.get(i).setRank(i + 1);
				}

				List<HeadlinesArticleListDTO> newList = new ArrayList<>();
				contents.stream().skip(pageNumber * pageSize).limit(pageSize).forEach(newList::add);

				Page<HeadlinesArticleListDTO> results = new PageImpl<>(newList, pageable, total);
				return results;
			}
	
	/**
	 * @param bool
	 * @param obj
	 * @return
	 */
	private HeadlinesArticleListDTO getArticleRankByQuery(BoolQueryBuilder bool, JSONObject obj) {
		SumBuilder totalHitCount = AggregationBuilders.sum("hitCount").field("hitCount");
		
		TermsBuilder emotionBuilder = AggregationBuilders.terms("emotion").field("emotion");
		
		SearchQuery authorQuery = getSearchQueryBuilder().withQuery(bool).addAggregation(totalHitCount)
				.addAggregation(emotionBuilder).build();
		
		/*if (logger.isDebugEnabled()) {
			loggger.debug("查询条件获取个人排行情况 query : \n {}", queryBuilder);
		}*/
		
		HeadlinesArticleListDTO personage = template.query(authorQuery, res -> {
			HeadlinesArticleListDTO per = new HeadlinesArticleListDTO();
			per.setTitle(obj.get("title").toString());
			per.setId(obj.get("_id").toString());
			per.setArticleLink(obj.get("articleLink").toString());
			per.setContent(obj.get("content").toString());
			per.setPublishDate(obj.get("publishDate").toString());
			per.setSource(obj.get("source").toString());
			per.setSourceLink(obj.get("sourceLink").toString());
			Aggregations aggs = res.getAggregations();
			
			Sum sum = aggs.get("hitCount");
			
			// 获取总阅读量
			double totalCount = sum.getValue();
			
			// 获取声量
			long totalVolume = res.getHits().getTotalHits();
			
			per.setTotalHitCount(totalCount);
			per.setVolume(totalVolume);
			
			// 计算热度值
			per.setHot(UtilsHelper.getRound(totalCount / totalVolume));
			
			Terms t = aggs.get("emotion");
			
			double positive = 0D;
			double negative = 0D;
			
			for (Bucket e : t.getBuckets()) {
				String k = e.getKeyAsString();
				if (Emotion.POSITIVE.equals(k)) {
					positive = e.getDocCount();
				} else if (Emotion.NEGATIVE.equals(k)) {
					negative = e.getDocCount();
				}
			}
			;
			
			// 分子
			double numerator = (positive - negative);
			// 分母
			double denominator = (positive + negative);
			
			if (denominator > 0D) {
				// 获取情感值
				per.setEmotionVal(UtilsHelper.getRound(numerator / denominator));
			}
			return per;
		});
		return personage;
	}
		
	
}
