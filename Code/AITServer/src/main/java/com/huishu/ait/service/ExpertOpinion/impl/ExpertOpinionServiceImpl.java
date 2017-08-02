package com.huishu.ait.service.ExpertOpinion.impl;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.entity.ExpertOpinionDetail;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.es.repository.ExpertOpinion.ExpertOpinionElasticsearch;
import com.huishu.ait.repository.expertOpinionDetail.ExpertOpinionDetailRepository;
import com.huishu.ait.service.ExpertOpinion.ExpertOpinionService;


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
			String sortByHotFlag = requestParam.getSortByHotFlag();
			String sortByTimeFlag = requestParam.getSortByTimeFlag();
			String startDate = requestParam.getStartDate();
			String endDate = requestParam.getEndDate();
			int pageNumber = requestParam.getPageNumber();
			int pageSize = requestParam.getPageSize();
			int from = (pageNumber-1)*pageSize;
			SearchRequestBuilder searchBuilder = ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = QueryBuilders.boolQuery();
			bq.must(QueryBuilders.matchAllQuery());
			//根据条件查询
			SearchRequestBuilder requestBuilder = searchBuilder.setQuery(bq)
					.setFrom(from).setSize(pageSize);
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
			int pageNumber = requestParam.getPageNumber();
			int pageSize = requestParam.getPageSize();
			int from = (pageNumber-1)*pageSize;
			
			SearchRequestBuilder requestBuilder = ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = QueryBuilders.boolQuery();
			bq.must(QueryBuilders.matchAllQuery());
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
	public Boolean expertOpinionCollect(String articleId){
		Boolean flag= true;
		try {
			AITInfo param = expertOpinionElasticsearch.findOne(articleId);
			ExpertOpinionDetail findOne = findExpertOpinionDetailByArticleId(articleId);
			//如果不为空先删除
			if (null != findOne) {
				expertOpinionDetailRepository.delete(findOne);
			}
			//保存
			ExpertOpinionDetail expertOpinionDetail = new ExpertOpinionDetail();
			expertOpinionDetail.setArticleId(param.getId());
			expertOpinionDetail.setAuthor(param.getAuthor());
			expertOpinionDetail.setPublishTime(param.getPublishDateTime());
			expertOpinionDetail.setTitle(param.getTitle());
			expertOpinionDetail.setContent(param.getContent());
			expertOpinionDetail.setSource(param.getSource());
			expertOpinionDetail.setSourceLink(param.getSourceLink());
			expertOpinionDetail.setIndustry(param.getIndustry());
			expertOpinionDetail.setLanmu(param.getDimension());
			expertOpinionDetailRepository.save(expertOpinionDetail);
			return flag;
		} catch (Exception e) {
			flag=false;
			log.error("收藏失败：",e.getMessage());
			return flag;
		}
	}
	/**
	 * 取消收藏专家观点
	 */
	public Boolean cancelExpertOpinionCollect(String articleId){
		Boolean flag= true;
		try {
			ExpertOpinionDetail findOne = findExpertOpinionDetailByArticleId(articleId);
			if (null == findOne) {
				flag=false;
			}
			expertOpinionDetailRepository.delete(findOne);
			return flag;
		} catch (Exception e) {
			flag=false;
			log.error("取消收藏失败：",e.getMessage());
			return flag;
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
