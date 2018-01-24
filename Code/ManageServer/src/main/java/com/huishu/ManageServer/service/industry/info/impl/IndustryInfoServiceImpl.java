package com.huishu.ManageServer.service.industry.info.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
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
import com.huishu.ManageServer.es.entity.AITInfo;
import com.huishu.ManageServer.es.entity.SummitInfo;
import com.huishu.ManageServer.service.industry.info.IndustryInfoService;
import com.merchantKey.articleToKeywordCloud.ArticleConToKeywordCloud;
import com.merchantKey.itemModel.KeywordModel;
import com.huishu.ManageServer.es.repository.BaseElasticsearch;
import com.huishu.ManageServer.repository.first.KeyArticleRepository;
import com.huishu.ManageServer.repository.first.KeyWordRepository;
import com.huishu.ManageServer.service.AbstractService;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.entity.dbFirst.KeyWordEntity;
import com.huishu.ManageServer.entity.dbFirst.KeywordArticle;

/**
 * @author hhy
 * @date 2018年1月18日
 * @Parem
 * @return 
 * 产业动态相关service实现类
 */
@Service
public class IndustryInfoServiceImpl extends AbstractService  implements IndustryInfoService {
	@Autowired
	protected ElasticsearchTemplate template;
	
	@Autowired
	protected BaseElasticsearch rep;
	
	@Autowired
	private KeyArticleRepository keyRep;
	
	@Autowired
	private KeyWordRepository krep;
	/**
	 * 获取科研成果的数据
	 */
	@Override
	public List<AITInfo> findResearchList(JSONObject json) {
		List<AITInfo>  list= new ArrayList<AITInfo>();
		String ss = json.getString("dimension");
		
		JSONArray arr = json.getJSONArray("industryLabel");
		
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				List<AITInfo> li = getInfo(str,ss);
				if(li.size()>1){
					list.add(li.get(0));
					list.add(li.get(1));
				}
			}
		}
		return list;
	}

	/**
	 * @param str
	 * @param ss
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private List<AITInfo> getInfo(String str, String ss) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		if (StringUtil.isNotEmpty(ss)) {
			bq.must(QueryBuilders.termQuery("dimension", ss));
		}
		bq.must(QueryBuilders.termQuery("industryLabel",str ));
		Pageable pageable = new PageRequest(0, 3,new Sort(Direction.DESC, "publishTime"));
		SearchQuery query = getBoolQueryBuilder().withQuery(bq).withPageable(pageable).withSort(SortBuilders.fieldSort("hitCount")).build();
		List<AITInfo> li = template.query(query, res->{
			SearchHits hits = res.getHits();
			List<AITInfo>  list = new ArrayList<AITInfo>();
			if(hits != null){
				SearchHit[] hitsList = hits.getHits();
				for (SearchHit hit : hits) {
					AITInfo info = new AITInfo();
					String industrylabel = hit.getSource().get("industryLabel").toString();
					if(industrylabel.equals("生物医药")){
						info.setIndustryLabel("生物技术");
					}else{
						info.setIndustryLabel(industrylabel);
					}
					
					String title = hit.getSource().get("title").toString();
					info.setArticleLink(hit.getSource().get("articleLink").toString());
					
					info.setTitle(title);
					info.setId(hit.getId());
					try {
						String publishTime = hit.getSource().get("publishTime").toString();
						
						info.setPublishTime(publishTime);
					} catch (Exception e) {

						info.setPublishTime("");
					}
					String content = hit.getSource().get("content").toString();
					List<String> business = null;
					try {
						 business = getBusiness(title,content);
						 info.setBus(business);
					} catch (Exception e) {
						business = getBusiness(title,content);
						info.setBus(business);
					}
					if(content.length()>300){
						String replaceHtml = StringUtil.replaceHtml(content.substring(0, 300));
						info.setContent(replaceHtml);
					}else{
						String replaceHtml = StringUtil.replaceHtml(content.substring(0, content.length()));
						info.setContent(replaceHtml);
					}
					list.add(info);
				}
			}
			return list;
		});
		return li;
	}

	
	/**
	 * 获取产业咨询相关内容
	 */
	@Override
	public Page<AITInfo> getIndustryInfoByPage(JSONObject json) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		JSONArray arr = json.getJSONArray("industry");
		BoolQueryBuilder or = new BoolQueryBuilder();
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				or.should(QueryBuilders.termQuery("industryLabel",str ));
			}
		}
		bq.must(or);
		String dimension = json.getString("dimension");
		if(StringUtil.isNotEmpty(dimension)){
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		
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

	
	@Override
	public JSONArray getKeyWordList(JSONObject json) {
		JSONArray data = new JSONArray();
		String time = json.getString("time");
		if(StringUtil.isEmpty(time)){
			return null;
		}
		List<KeyWordEntity> list = krep.getByTime(time);
		list.forEach(action ->{
			JSONObject obj = new JSONObject();
			obj.put("name", action.getKey());
			obj.put("value", action.getValue());
			data.add(obj);
		});
		return data;
	}

	@Override
	public JSONArray findArticleInfo(String time, String keyWord) {
		JSONArray arr = new JSONArray();
		KeyWordEntity entry = 	krep.findByTimeAndKey(time,keyWord);
		 if( entry != null){
			List<KeywordArticle> list = keyRep.findBykid( entry.getId());
			for(int i =0; i<10;i++){
				JSONObject jsonObject = new JSONObject();
				KeywordArticle ent = list.get(i);
				jsonObject.put("id", ent.getId());
				jsonObject.put("aid", ent.getAid());
				jsonObject.put("industryLabel", ent.getIndustryLabel());
				jsonObject.put("title", ent.getTitle());
				jsonObject.put("articleLink", ent.getArticleLink());
				arr.add(jsonObject);
			}
			return arr;
		 }else{
			 return null;
		 }
	}

	
	@Override
	public List<KeywordModel> fiindKeyWordList(JSONObject json) {
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
		JSONArray arr = json.getJSONArray("industryLabel");
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				bq.should(QueryBuilders.termQuery("industryLabel",str ));
			}
		}
		
		PageRequest pageRequest = new PageRequest(0,500);
		SearchQuery query = getSearchQueryBuilder().withQuery(bq).withPageable(pageRequest).build();
		List<String> contentList = new ArrayList<String>();
		template.query(query, res -> {
			SearchHits hits = res.getHits();
			if (hits != null) {
				SearchHit[] hitsList = hits.getHits();
				for (SearchHit h : hitsList) {
					if (h.getSource() != null) {
						contentList.add(h.getSource().get("content") + "");
					}
				}
			}
			return "";
		});
	 	JSONObject keywordCloud = ArticleConToKeywordCloud.toKeywordCloud(contentList, 0, 50);
	 	List<KeywordModel> list = null;
	 	if (keywordCloud.getBooleanValue("status")) {
		  list = (List<KeywordModel>) keywordCloud.get("result");
	 	}
		return list;
	}
	@Override
	public JSONArray getArticleListByKeyWord(JSONObject obj) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		if (StringUtil.isNotEmpty(obj.getString("dimension"))) {
			String dimension = obj.getString("dimension");
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		JSONArray arr = obj.getJSONArray("industryLabel");
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				bq.should(QueryBuilders.termQuery("industryLabel",str ));
			}
		}
		if (StringUtil.isNotEmpty(obj.getString("keyWord"))) {
			String keyWord = obj.getString("keyWord");
			bq.must(QueryBuilders.wildcardQuery("content", "*" + keyWord + "*"));
		}
		if (StringUtil.isNotEmpty(obj.getString("startTime")) && StringUtil.isNotEmpty(obj.getString("endTime"))) {
			String startTime = obj.getString("startTime");
			String endTime = obj.getString("endTime");
			bq.must(QueryBuilders.rangeQuery("publishTime").from(startTime).to(endTime));
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
	 * 删除产业资讯的文章信息根据id
	 */
	@Override
	public boolean deleteArticleInfoById(String id) {
		try {
			rep.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 产业资讯获取文章接口
	 */
	@Override
	public AITInfo findIndustryInfoById(String id) {
		AITInfo one = rep.findOne(id);
		return one;
	}

	/**
	 * 删除研究成果的数据
	 */
	@Override
	public boolean deleteInfoById(String id) {
		AITInfo info = rep.findOne(id);
		if( info != null){
			try {
				rep.delete(info);
				return true;
			} catch (Exception e) {
				return false;
			}
		}else{
			return false;
		}
	}

	
	@Override
	public boolean saveIndudustryInfo(AITInfo enter) {
		try {
				AITInfo save = rep.save(enter);
			if(save != null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
