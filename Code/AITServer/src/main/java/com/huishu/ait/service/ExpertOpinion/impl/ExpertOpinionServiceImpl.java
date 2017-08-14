package com.huishu.ait.service.ExpertOpinion.impl;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.Constans;
import com.huishu.ait.common.util.DateCheck;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.entity.ExpertOpinionDetail;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.es.repository.ExpertOpinion.BaseElasticsearch;
import com.huishu.ait.repository.expertOpinionDetail.ExpertOpinionDetailRepository;
import com.huishu.ait.service.ExpertOpinion.ExpertOpinionService;


/**
 * @author yxq
 *	专家观点的实现类
 */
@Service
@Transactional
public class ExpertOpinionServiceImpl implements ExpertOpinionService {
	
	private static Logger log = LoggerFactory.getLogger(ExpertOpinionServiceImpl.class);
	
	@Resource
	private Client client;
	@Autowired
	private BaseElasticsearch baseElasticsearch;
	@Resource
	private ExpertOpinionDetailRepository expertOpinionDetailRepository;
	
	/* 
	 * 方法名：getExertOpinionList
	 * 描述：根据条件获取百家论观点信息
	 */
	public JSONArray getExertOpinionList(ExpertOpinionDTO requestParam){
		try {
			JSONArray data = new JSONArray();
			//获取参数
			String industry = requestParam.getIndustry();
			String industryLabel = requestParam.getIndustryLabel();
			String timeFlag = requestParam.getTimeFlag();
			String startDate = null;
			String endDate = null;
			String dateCheck = DateCheck.dateCheck(timeFlag);
			if (null != dateCheck) {
				String[] split = dateCheck.split("@");
				startDate = split[0];
				endDate = split[1];
			}
			int pageNumber = requestParam.getPageNumber();
			int pageSize = requestParam.getPageSize();
			int from = (pageNumber-1)*pageSize;
			SearchRequestBuilder searchBuilder = ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = QueryBuilders.boolQuery();
			bq.must(QueryBuilders.termQuery("dimension", Constans.BAIJIALUN));
			//根据条件查询
			SearchRequestBuilder requestBuilder = searchBuilder.setQuery(bq)
					.setFrom(from).setSize(pageSize);
			if (StringUtils.isNotEmpty(industry)) {
				bq.must(QueryBuilders.termQuery("industry", industry));
			}
			if (StringUtils.isNotBlank(industryLabel) && !(Constans.BUXIAN).equals(industryLabel)) {
				bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
			}
			if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
				bq.must(QueryBuilders.rangeQuery("publishDate").from(startDate).to(endDate));
			}
			
			SearchResponse actionGet = requestBuilder.execute().actionGet();
			SearchHits hits = actionGet.getHits();
			if (null !=hits ) {
				for (SearchHit hit : hits) {
					Map<String, Object> map = hit.getSource();
						if (null != map && map.size() > 0 ) {
							JSONObject jsonObject = new JSONObject();
							map.put("_id", hit.getId());
							map.put("total", hits.getTotalHits());
							jsonObject.put("id", map.get("_id"));
							jsonObject.put("publishDate", map.get("publishDate"));
							jsonObject.put("title", map.get("title"));
							jsonObject.put("content", map.get("content"));
							jsonObject.put("author", map.get("author"));
							jsonObject.put("sourceLink", map.get("sourceLink"));
							jsonObject.put("source", map.get("source"));
							jsonObject.put("business", map.get("business"));
							jsonObject.put("area", map.get("area"));
							jsonObject.put("total", map.get("total"));
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
	 * 根据姓名查询专家论列表并根据时间进行排序
	 */
	@Override
	public JSONArray findExpertOpinionByAuthor(ExpertOpinionDTO requestParam) {
		
		try {
			JSONArray data = new JSONArray();
			String author = requestParam.getAuthor();
			int pageNumber = requestParam.getPageNumber();
			int pageSize = requestParam.getPageSize();
			int from = (pageNumber-1)*pageSize;
			
			SearchRequestBuilder requestBuilder = ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = QueryBuilders.boolQuery();
			bq.must(QueryBuilders.termQuery("dimension", Constans.ZHUANJIALUN));
			if (StringUtils.isNotBlank(author)) {
				bq.must(QueryBuilders.termQuery("author", author));
			}
			SearchResponse actionGet = requestBuilder.setQuery(bq)
					.addSort("publishDateTime", SortOrder.DESC)
					.setFrom(from).setSize(pageSize)
					.execute().actionGet();
			SearchHits hits = actionGet.getHits();
			if (null != hits) {
				for (SearchHit hit : hits) {
					Map<String, Object> source = hit.getSource();
					if (null!= source && source.size() > 0) {
						source.put("_id", hit.getId());
						source.put("total", hits.getTotalHits());
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("id", source.get("_id"));
						jsonObject.put("title", source.get("title"));
						jsonObject.put("content", source.get("content"));
						jsonObject.put("dimension", source.get("dimension"));
						jsonObject.put("publishDate", source.get("publishDate"));
						jsonObject.put("business", source.get("business"));
						jsonObject.put("businessType", source.get("businessType"));
						jsonObject.put("area", source.get("area"));
						jsonObject.put("total", source.get("total"));
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
	 * 方法名：findExpertOpinionById
	 * 描述：通过id查询文章详情
	 */
	@Override
	public JSONObject findExpertOpinionById(String id) {
		try {
			JSONObject jsonObject = new JSONObject();
			AITInfo aitInfo = baseElasticsearch.findOne(id);
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
	public JSONObject expertOpinionCollect(String articleId){
		JSONObject json = new JSONObject();
		try {
			AITInfo param = baseElasticsearch.findOne(articleId);
			ExpertOpinionDetail findOne = findExpertOpinionDetailByArticleId(articleId);
			//如果不为空先删除
			if (null != findOne) {
				expertOpinionDetailRepository.delete(findOne);
			}
			//保存
			ExpertOpinionDetail expertOpinionDetail = new ExpertOpinionDetail();
			expertOpinionDetail.setArticleId(param.getId());
			expertOpinionDetail.setAuthor(param.getAuthor());
			expertOpinionDetail.setCollectTime(new Date());
			expertOpinionDetail.setPublishTime(param.getPublishDateTime());
			expertOpinionDetail.setTitle(param.getTitle());
			expertOpinionDetail.setContent(param.getContent());
			expertOpinionDetail.setSource(param.getSource());
			expertOpinionDetail.setSourceLink(param.getSourceLink());
			expertOpinionDetail.setIndustry(param.getIndustry());
			expertOpinionDetail.setLanmu(param.getDimension());
			expertOpinionDetailRepository.save(expertOpinionDetail);
			json.put("state", "success");
			return json;
		} catch (Exception e) {
			json.put("state", "failure");
			log.error("收藏失败：",e.getMessage());
			return json;
		}
	}
	/**
	 * 取消收藏专家观点
	 */
	public JSONObject cancelExpertOpinionCollect(String articleId){
		JSONObject json = new JSONObject();
		try {
			ExpertOpinionDetail findOne = findExpertOpinionDetailByArticleId(articleId);
			if (null == findOne) {
				json.put("state", "failure");
			}
			expertOpinionDetailRepository.delete(findOne);
			json.put("state", "success");
			return json;
		} catch (Exception e) {
			json.put("state", "failure");
			log.error("取消收藏失败：",e.getMessage());
			return json;
		}
	}
	/**
	 * @param articleId
	 * @return根据文章id从数据库中查询文章详情
	 */
	public ExpertOpinionDetail findExpertOpinionDetailByArticleId(String articleId){
		return expertOpinionDetailRepository.findExpertOpinionDetailByArticleId(articleId);
	}
}
