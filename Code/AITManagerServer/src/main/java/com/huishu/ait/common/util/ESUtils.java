package com.huishu.ait.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.DBConstant;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;

/**
 * ES工具类
 * 
 * @author yuwei
 *
 */
public class ESUtils {

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
		if (more != null) {
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
		if (moreArr != null) {
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
		if (StringUtils.isNotEmpty(values)) {
			String[] split = values.split(",");
			for (int i = 0; i < split.length; i++) {
				or.should(QueryBuilders.termQuery(fiedName, split[i]));
			}
		}
		return or;
	}

	public static BoolQueryBuilder getMoreCitysWildCard(String fiedName, String values) {
		BoolQueryBuilder or = new BoolQueryBuilder();
		// 北京市，天津市，上海市，重庆市
		if (StringUtils.isNotEmpty(values)) {
			String[] split = values.split(",");
			for (int i = 0; i < split.length; i++) {
				if ("北京".equals(split[i]) || "天津".equals(split[i]) || "上海".equals(split[i]) || "重庆".equals(split[i])) {
					or.should(QueryBuilders.wildcardQuery(fiedName, "*" + split[i] + "*"));
				} else {
					or.should(QueryBuilders.termQuery(fiedName, split[i]));
				}
			}
		}
		return or;
	}

	public static BoolQueryBuilder getMoreCitysAndAreaWildCard(String fiedName, String values) {
		BoolQueryBuilder or = new BoolQueryBuilder();
		// 北京市，天津市，上海市，重庆市
		if (StringUtils.isNotEmpty(values)) {
			String[] split = values.split(",");
			for (int i = 0; i < split.length; i++) {
				if ("北京".equals(split[i]) || "天津".equals(split[i]) || "上海".equals(split[i]) || "重庆".equals(split[i])) {
					or.should(QueryBuilders.wildcardQuery(fiedName, "*" + split[i] + "*"));
				} else {
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
	 * 获取默认的ES 搜索请求构造器
	 * 
	 * @param client
	 * @return
	 */
	public static SearchRequestBuilder getSearchBuilder(Client client) {
		return client.prepareSearch(DBConstant.EsConfig.INDEX1).setTypes(DBConstant.EsConfig.TYPE1);
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