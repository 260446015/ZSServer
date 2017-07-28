package com.huishu.ait.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.DBConstant;

/**
 * ES工具类
 * 
 * @author yuwei
 *
 */
public class ESUtils {

	/**
	 * 获取拥车时间查询器
	 * 
	 * @param holdDaysRange
	 * @return
	 */
	public static BoolQueryBuilder getHoldDaysRangeQueryBuilder(JSONArray holdDaysRange) {
		BoolQueryBuilder or = new BoolQueryBuilder();
		if(holdDaysRange != null){
			for (int i = 0; i < holdDaysRange.size(); i++) {
				BoolQueryBuilder must = new BoolQueryBuilder();
				RangeQueryBuilder range = new RangeQueryBuilder("holdDays");
				JSONObject date = holdDaysRange.getJSONObject(i);
				Long holdStartDate = date.getLong("holdStartDays");
				if (holdStartDate != null) {
					range.gte(holdStartDate);
				} else {
					range.gte(0);
				}
				Long holdEndDate = date.getLong("holdEndDays");
				if (holdEndDate != null) {
					range.lt(holdEndDate);
				} else {
					range.lt(Long.MAX_VALUE);
				}
				must.must(range);
				or.should(must);
			}
		}
		return or;
	}

	/**
	 * 获取价格查询器
	 *
	 * @param priceRange
	 * @return
	 */
	public static BoolQueryBuilder getPriceRangeQueryBuilder(JSONArray priceRange) {
		BoolQueryBuilder should = new BoolQueryBuilder();
		if (priceRange != null) {
			for (int i = 0; i < priceRange.size(); i++) {
				JSONObject data = priceRange.getJSONObject(i);
				Double priceCeiling = null;// 上限
				if (org.apache.commons.lang.StringUtils.isNotBlank(data.getString("priceCeiling"))) {
					priceCeiling = data.getDouble("priceCeiling");
				} else {
					// 如果价格上限为空，那么默认值为Long的最大值
					priceCeiling = Double.parseDouble(Long.MAX_VALUE+"");
				}

				Double priceFloor = null;// 下限
				if (org.apache.commons.lang.StringUtils.isNotBlank(data.getString("priceFloor"))) {
					priceFloor = data.getDouble("priceFloor");
				} else {
					// 如果价格下限为空，那么默认值为0
					priceFloor = 0D;
				}

				/**
				 * 价格下限 <= 车辆最低价 < 价格上限 
				 * priceFloor <= lowestPrice < priceCeiling
				 * 或者 
				 * 价格下限 <= 车辆最高价 < 价格上限 
				 * priceFloor <= highestPrice < priceCeiling
				 */
				BoolQueryBuilder must = new BoolQueryBuilder();
				BoolQueryBuilder or = new BoolQueryBuilder();
				// 1、priceFloor <= lowestPrice < priceCeiling (价格下限 <= 车辆最低价 < 价格上限)
				BoolQueryBuilder must1 = new BoolQueryBuilder();
				must1.must(QueryBuilders.rangeQuery("lowestPrice").gte(priceFloor).lt(priceCeiling));
				or.should(must1);
				// 2、priceFloor <= highestPrice < priceCeiling(价格下限 <= 车辆最高价 < 价格上限 )
				BoolQueryBuilder must2 = new BoolQueryBuilder();
				must2.must(QueryBuilders.rangeQuery("highestPrice").gte(priceFloor).lt(priceCeiling));
				or.should(must2);
				must.must(or);
				should.should(must);
			}
		}

		return should;
	}

	/**
	 * 获取更多条件查询器
	 * 
	 * @param fiedName
	 *            字段名称
	 * @param more
	 *            参数数组
	 * @return
	 */
	public static BoolQueryBuilder getMoreQueryBuilder(String fiedName, JSONArray more) {
		BoolQueryBuilder or = new BoolQueryBuilder();
		if(more != null){
			for (int i = 0; i < more.size(); i++) {
				JSONObject data = more.getJSONObject(i);
				or.should(QueryBuilders.termQuery(fiedName, data.getString("value")));
			}
		}
		return or;
	}
	
	/**
	 * 获取更多条件查询器，参数为用逗号分隔的String字符串
	 * 
	 * @param fiedName
	 *            字段名称
	 * @param more
	 *            参数数组
	 * @return
	 */
	public static BoolQueryBuilder getMoreQueryBuilder(String fiedName, String more) {
		BoolQueryBuilder or = new BoolQueryBuilder();
		String[] moreArr = more.split(",");
		if(moreArr != null){
			for (int i = 0; i < moreArr.length; i++) {
				or.should(QueryBuilders.termQuery(fiedName, moreArr[i]));
			}
		}
		return or;
	}
	
	
	/**
	 * 获取更多条件查询器 用逗号分割
	 * 
	 * @param fiedName
	 *            字段名称
	 * @param more
	 *            参数数组
	 * @return
	 */
	public static BoolQueryBuilder getMoreQueryBuilderString(String fiedName, String values) {
		BoolQueryBuilder or = new BoolQueryBuilder();
		if(StringUtils.isNotEmpty(values)){
			String[] split = values.split(",");
			for (int i = 0; i < split.length; i++) {
				or.should(QueryBuilders.termQuery(fiedName, split[i]));
			}
		}
		return or;
	}
	
	public static BoolQueryBuilder getMoreCitysWildCard(String fiedName, String values) {
		BoolQueryBuilder or = new BoolQueryBuilder();
		//北京市，天津市，上海市，重庆市
		if(StringUtils.isNotEmpty(values)){
			String[] split = values.split(",");
			for (int i = 0; i < split.length; i++) {
				if("北京".equals(split[i])||"天津".equals(split[i])||"上海".equals(split[i])||"重庆".equals(split[i])){
					or.should(QueryBuilders.wildcardQuery(fiedName, "*"+split[i]+"*"));
				}else{
					or.should(QueryBuilders.termQuery(fiedName, split[i]));
				}
			}
		}
		return or;
	}
	
	public static BoolQueryBuilder getMoreCitysAndAreaWildCard(String fiedName, String values) {
		BoolQueryBuilder or = new BoolQueryBuilder();
		//北京市，天津市，上海市，重庆市
		if(StringUtils.isNotEmpty(values)){
			String[] split = values.split(",");
			for (int i = 0; i < split.length; i++) {
				if("北京".equals(split[i])||"天津".equals(split[i])||"上海".equals(split[i])||"重庆".equals(split[i])){
					or.should(QueryBuilders.wildcardQuery(fiedName, "*"+split[i]+"*"));
				}else{
					or.should(QueryBuilders.termQuery("city", split[i]));
				}
			}
		}
		return or;
	}


	/**
	 * 获取默认的ES 搜索请求构造器
	 * 
	 * @param client
	 * @return
	 */
	public static SearchRequestBuilder getSearchRequestBuilder(Client client) {
		return client.prepareSearch(DBConstant.EsConfig.INDEX).setTypes(DBConstant.EsConfig.TYPE);
	}
	
	/**
	 * 获取默认的ES 搜索请求构造器---舆情管理内容分析
	 * 
	 * @param client
	 * @return
	 */
	public static SearchRequestBuilder getSearchRequestOpinionContentBuilder(Client client) {
		return client.prepareSearch("cdap_content_alias").setTypes("contentinfo");
	}
	
	/**
	 * 获取舆情管理 SearchRequestBuilder
	 * 
	 * @param client
	 * @return
	 */
	public static SearchRequestBuilder getOpinionSearchReqBuilder(Client client) {
		return client.prepareSearch().setIndices("opinion_alias").setTypes("opinioninfo");
	}
	
	/**
	 * 获取产品管理战败分析 SearchRequestBuilder
	 * 
	 * @param client
	 * @return
	 */
	public static SearchRequestBuilder getDefeatanalyzeSearchReqBuilder(Client client) {
		return client.prepareSearch().setIndices("defeate_analysis_alias").setTypes("defeatanalyze");
	}
	
	public static RangeQueryBuilder getPublishDateQuery(String startDate, String endDate) {
		RangeQueryBuilder range = QueryBuilders.rangeQuery("publishDate");
		range.from(startDate).to(endDate);
		return range;
	}
	
	public static RangeQueryBuilder getBuyDateQuery(String startDate, String endDate) {
		RangeQueryBuilder range = QueryBuilders.rangeQuery("buyDate");
		range.from(startDate).to(endDate);
		return range;
	}

	/**
	 * 获取发布时间范围筛选
	 * 
	 * @param startDate
	 *            开始时间 yyyy-MM-dd HH:mm:ss
	 * @param endDate
	 *            结束时间 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static RangeQueryBuilder getPublishDateTimeQuery(String startDate, String endDate) {
		RangeQueryBuilder range = QueryBuilders.rangeQuery("publishDateTime");
		range.from(startDate).to(endDate);
		return range;
	}

	/**
	 * 判断指定的索引是否存在
	 * 
	 * @param client
	 * @param index
	 * @return
	 */
	public static boolean isExistIndex(Client client, String index) {
		IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(index).get();
		return existsResponse.isExists();
	}

	/**
	 * 根据查询条件获取总人数
	 * 
	 * @param bq
	 * @param client
	 * @return
	 */
	public long getTotalPeople(Client client, BoolQueryBuilder bq) {
		SearchRequestBuilder requestBuilder = ESUtils.getSearchRequestBuilder(client);
		CardinalityBuilder carModelAuthor = AggregationBuilders.cardinality("carModelId_authorId")
				.field("carModelId_authorId");

		requestBuilder.setQuery(bq);
		requestBuilder.addAggregation(carModelAuthor);
		System.out.println("totalPeople query: " + requestBuilder);
		SearchResponse response = requestBuilder.execute().actionGet();
		InternalCardinality carModelAuthorNum = response.getAggregations().get("carModelId_authorId");

		return carModelAuthorNum.getValue() == 0 ? 1 : carModelAuthorNum.getValue();
	}

	/**
	 * 获取属性的范围值
	 * 
	 * @param string
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static QueryBuilder getFieldRangeQuery(String field, String startDate, String endDate) {
		RangeQueryBuilder range = QueryBuilders.rangeQuery(field);
		range.from(startDate).to(endDate);
		return range;
	}

}