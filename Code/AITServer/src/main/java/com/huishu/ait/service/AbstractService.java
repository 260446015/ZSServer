package com.huishu.ait.service;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
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
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
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
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.common.util.UtilsHelper;
import com.huishu.ait.echart.Legend;
import com.huishu.ait.echart.Option;
import com.huishu.ait.echart.Tooltip;
import com.huishu.ait.echart.series.Pie;
import com.huishu.ait.echart.series.Serie.SerieData;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.entity.dto.AreaSearchDTO;
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
	private Client client;
	
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
		ESUtils.getMoreQueryBuilder("industryLabel",industryLabel);
		
		/** 载体 */
		String vector = headlinesDTO.getVector();
		if (StringUtils.isNotEmpty(vector)) {
			bq.must(QueryBuilders.termQuery("vector", vector));
		}
		/**关键词*/
		String keyword = headlinesDTO.getKeyWord();
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

		List<HeadlinesArticleListDTO> jsonArray = template.query(authorQuery, res -> {
			List<HeadlinesArticleListDTO> list = new ArrayList<HeadlinesArticleListDTO>();
			
		    	Map<String, Object> Source= null;
		    	SearchHits hits = res.getHits();
		    	for(SearchHit hit :hits){
		    		Source = hit.getSource();
		    		Source.put("_id", hit.getId());
		    		 getDtoInfo(list,Source);
		    	}
		    	
			return list;
		});

		// 第二步：对结果进行排序，按照热度排序，分页取十条数据
		                    
				int total = jsonArray.size();
				int pageNumber = pageable.getPageNumber();
				int pageSize = pageable.getPageSize();

				pageable.getSort().forEach(order -> {
					String property = order.getProperty();
					Direction direction = order.getDirection();
					
					jsonArray.sort((o1, o2) -> {
						Double v1 = (Double) UtilsHelper.getValueByFieldName(o1, property);
						Double v2 = (Double) UtilsHelper.getValueByFieldName(o2, property);

						if (Direction.ASC.equals(direction)) {
							return v1.compareTo(v2);
						}
						return v2.compareTo(v1);
					});
				});

				for (int i = 0; i < jsonArray.size(); i++) {
					jsonArray.get(i).setRank(i + 1);
				}

				List<HeadlinesArticleListDTO> newList = new ArrayList<>();
				jsonArray.stream().skip(pageNumber * pageSize).limit(pageSize).forEach(newList::add);

				Page<HeadlinesArticleListDTO> results = new PageImpl<>(newList, pageable, total);
				return results;
			}
	/**
	 * @param source
	 * @param dto
	 * @return
	 */
	private void  getDtoInfo( List<HeadlinesArticleListDTO> list,Map<String, Object> source) {
		HeadlinesArticleListDTO dto = new HeadlinesArticleListDTO();
		dto.setId(source.get("_id").toString());
		dto.setArticleLink(source.get("articleLink").toString());
		dto.setContent(source.get("content").toString());
		dto.setPublishDate(source.get("publishDate").toString());
		dto.setSource(source.get("source").toString());
		dto.setSourceLink(source.get("sourceLink").toString());
		dto.setTitle(source.get("title").toString());
		Integer hitCount  = (Integer) source.get("hitCount");
		Integer supportCount  = (Integer) source.get("supportCount");
		
		dto.setHitCount( (int) source.get("hitCount"));
		dto.setReplyCount( (int) source.get("replyCount"));
		dto.setSupportCount( (int) source.get("supportCount"));
		dto.setHot(UtilsHelper.getRound(supportCount/hitCount*1.0));
		list.add(dto);
	}
	
	/**
	 * ES的分页查询数据方法
	 * @param searchModel  查询Model
	 * @param termField    查询条件集合
	 * @param notTermField    不包含条件集合
	 * @param orderField   需要排序的字段集合。  PS：请注意先后顺序
	 * @param dataField    返回的数据存在的字段集合。  PS：ID属性默认有
	 * @param isPage    是否分页
	 * @return
	 */
	protected JSONArray getEsData(SearchModel searchModel,Map<String,String> termField,Map<String,String> notTermField,List<String> orderField,List<String> dataField,boolean isPage){
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		//查询条件
		if(null!=termField&&termField.size()!=0){
			for (String key : termField.keySet()) {
				bq.must(QueryBuilders.termQuery(key,termField.get(key)));
			}
		}
		//不包含条件
		if(null!=notTermField&&notTermField.size()!=0){
			for (String key : termField.keySet()) {
				bq.mustNot(QueryBuilders.termQuery(key,termField.get(key)));
			}
		}
		SearchRequestBuilder srb = ESUtils.getSearchRequestBuilder(client);
		//按orderField包含的字段降序排列
		if(null!=orderField&&orderField.size()!=0){
			orderField.forEach((string) -> srb.addSort(SortBuilders.fieldSort(string).order(SortOrder.DESC)));
		}
		Integer pageSize = searchModel.getPageSize();
		Integer pageNumber = searchModel.getPageNumber();
		SearchResponse searchResponse = srb.setQuery(bq).setSize(pageSize*pageNumber).execute().actionGet();
		
		JSONArray rows=new JSONArray();
		JSONArray data=new JSONArray();
		Long total=null; 
		if(null!=searchResponse&&null!=searchResponse.getHits()){
			SearchHits hits = searchResponse.getHits();
			total = hits.getTotalHits();
			hits.forEach((searchHit)->{
				Map<String, Object> map = searchHit.getSource();
				JSONObject obj = new JSONObject();
				obj.put("id",searchHit.getId());
				//获取所需要的字段值
				if(null!=dataField&&dataField.size()!=0){
					for (String string : dataField) {
						obj.put(string,map.get(string));
					}
				}
				rows.add(obj);
			});
		}
		if(isPage){
			searchModel.setTotalSize(Integer.valueOf(total.toString()));
			for (int i = searchModel.getPageFrom(); i < rows.size(); i++) {
				data.add(rows.get(i));
			}
			return data;
		}
		return rows;
		
	}
	
	/**
	 * 获取固定查询条件DEMO
	 * @param gardenName   园区名字
	 * @return
	 */
	protected AreaSearchDTO getAreaSearchDTODemo(String gardenName){
		AreaSearchDTO searchModel = new AreaSearchDTO();
		searchModel.setPark(gardenName);
		searchModel.setPageSize(5);
		return searchModel;
	}
}
