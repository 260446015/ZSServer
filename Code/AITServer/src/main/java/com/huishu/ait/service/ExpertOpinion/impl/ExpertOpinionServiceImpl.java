package com.huishu.ait.service.ExpertOpinion.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.Constans;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.UserCollection;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.ExpertOpinionDTO;
import com.huishu.ait.es.repository.ExpertOpinion.BaseElasticsearch;
import com.huishu.ait.repository.expertOpinionDetail.UserCollectionRepository;
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
	private UserCollectionRepository userCollectionRepository;
	
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
			/*String timeFlag = requestParam.getTimeFlag();
			String startDate = null;
			String endDate = null;
			String dateCheck = DateCheck.dateCheck(timeFlag);
			if (null != dateCheck) {
				String[] split = dateCheck.split("@");
				startDate = split[0];
				endDate = split[1];
			}*/
			SearchRequestBuilder searchBuilder = ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = QueryBuilders.boolQuery();
			bq.must(QueryBuilders.termQuery("dimension", Constans.BAIJIALUN));
			bq.must(QueryBuilders.termQuery("industry", industry));
			if(!industryLabel.equals("不限")){
				bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
			}
			//根据条件查询
			SearchRequestBuilder requestBuilder = searchBuilder.setQuery(bq)
					.setSize(requestParam.getPageSize()).addSort(SortBuilders.fieldSort("publishDate").order(SortOrder.DESC));
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
							jsonObject.put("summary", StringUtil.replaceHtml((String) map.get("summary")));
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
			
			Sort sort = new Sort(Direction.DESC, "publishTime");
			PageRequest pageRequest = new PageRequest(pageNumber-1, pageSize, sort);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			bq.must(QueryBuilders.termQuery("dimension", Constans.ZHUANJIALUN));
			if (StringUtils.isNotBlank(author)) {
				bq.must(QueryBuilders.termQuery("author", author));
			}
			Page<AITInfo> page = baseElasticsearch.search(bq, pageRequest);
			data.add(page);
			return data;
		} catch (Exception e) {
			log.error("查询失败：",e);
			return null;
		}
	}

	/**
	 * 收藏专家观点
	 */
	public JSONObject expertOpinionCollect(String articleId,Long userId){
		JSONObject json = new JSONObject();
		try {
			AITInfo param = baseElasticsearch.findOne(articleId);
			UserCollection findOne = userCollectionRepository.findByArticleIdAndUserId(articleId,userId);
			//如果不为空先删除
			if (null != findOne) {
				userCollectionRepository.delete(findOne);
			}
			//保存
			UserCollection uc = new UserCollection();
			uc.setArticleId(param.getId());
			uc.setAuthor(param.getAuthor());
			uc.setCollectTime(new Date().toString());
			uc.setPublishTime(param.getPublishDateTime());
			uc.setTitle(param.getTitle());
			uc.setContent(param.getContent());
			uc.setSource(param.getSource());
			uc.setSourceLink(param.getSourceLink());
			uc.setIndustry(param.getIndustry());
			uc.setLanmu(param.getDimension());
			uc.setUserId(userId);
			userCollectionRepository.save(uc);
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
	public JSONObject cancelExpertOpinionCollect(String articleId,Long userId){
		JSONObject json = new JSONObject();
		try {
			UserCollection findOne = userCollectionRepository.findByArticleIdAndUserId(articleId, userId);
			if (null == findOne) {
				json.put("state", "failure");
			}
			userCollectionRepository.delete(findOne);
			json.put("state", "success");
			return json;
		} catch (Exception e) {
			json.put("state", "failure");
			log.error("取消收藏失败：",e.getMessage());
			return json;
		}
	}
}