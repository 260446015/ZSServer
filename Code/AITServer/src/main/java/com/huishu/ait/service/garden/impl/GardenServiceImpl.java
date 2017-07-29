package com.huishu.ait.service.garden.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.ConfConstant;
import com.huishu.ait.common.conf.DBConstant;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.es.entity.GardenInformation;
import com.huishu.ait.es.entity.GardenPolicy;
import com.huishu.ait.es.repository.garden.GardenInformationRepository;
import com.huishu.ait.es.repository.garden.GardenPolicyRepository;
import com.huishu.ait.service.garden.GardenService;

@Service
public class GardenServiceImpl implements GardenService {
	
	@Autowired
	private Client client;
	@Autowired
    private GardenPolicyRepository gardenPolicyRepository;
	@Autowired
    private GardenInformationRepository gardenInformationRepository;
	
	@Override
	public JSONObject getGardenPolicyList(SearchModel searchModel) {
		checkPage(searchModel);
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("park", searchModel.getPark()));
		bq.must(QueryBuilders.termQuery("articleType", "政策解读"));
		//按时间和点击量降序排列
		SortBuilder countBuilder = SortBuilders.fieldSort("hitCount").order(SortOrder.DESC);
		SortBuilder dateBuilder = SortBuilders.fieldSort("publishDate").order(SortOrder.DESC);
		
		SearchRequestBuilder srb = client.prepareSearch(DBConstant.EsConfig.INDEX);
		srb.setTypes(DBConstant.EsConfig.TYPE);
		srb.addSort(dateBuilder).addSort(countBuilder);
		Integer pageSize = searchModel.getPageSize();
		Integer pageNumber = searchModel.getPageNumber();
		SearchResponse searchResponse = srb.setQuery(bq).setSize(pageSize*pageNumber).execute().actionGet();
		
		List<JSONObject> rows=new ArrayList<JSONObject>();
		List<JSONObject> data=new ArrayList<JSONObject>();
		long total=0; 
		if(null!=searchResponse&&null!=searchResponse.getHits()){
			SearchHits hits = searchResponse.getHits();
			for (SearchHit searchHit : hits) {
				total = hits.getTotalHits();
				Map<String, Object> map = searchHit.getSource();
				JSONObject obj = new JSONObject();
				obj.put("id",searchHit.getId());
		        obj.put("title",map.get("title"));
		        obj.put("content",map.get("content"));
				rows.add(obj);
			}
		}
		JSONObject result = new JSONObject();
		Long totalPage=(total/pageSize)+((total%pageSize)>0?1:0);
		
		result.put("totalSize",total);
		result.put("totalPage",totalPage);
		if(pageNumber>totalPage){
			pageNumber=Integer.valueOf(totalPage.toString());
		}
		result.put("pageNumber",pageNumber);
		
		for (int i = pageSize*pageNumber-pageSize; i < rows.size(); i++) {
			data.add(rows.get(i));
		}
		result.put("list",data);
		
		return result;
	}
	@Override
	public GardenPolicy getGardenPolicyById(String id) {
		return gardenPolicyRepository.findOne(id);
	}
	@Override
	public JSONObject getGardenInformationList(SearchModel searchModel) {
		checkPage(searchModel);
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("park", searchModel.getPark()));
		bq.must(QueryBuilders.termQuery("articleType", "园区动态"));
		//按时间和点击量降序排列
		SortBuilder countBuilder = SortBuilders.fieldSort("hitCount").order(SortOrder.DESC);
		SortBuilder dateBuilder = SortBuilders.fieldSort("publishDate").order(SortOrder.DESC);
		
		SearchRequestBuilder srb = client.prepareSearch(DBConstant.EsConfig.INDEX);
		srb.setTypes(DBConstant.EsConfig.TYPE);
		srb.addSort(dateBuilder).addSort(countBuilder);
		Integer pageSize = searchModel.getPageSize();
		Integer pageNumber = searchModel.getPageNumber();
		SearchResponse searchResponse = srb.setQuery(bq).setSize(pageSize*pageNumber).execute().actionGet();
		
		List<JSONObject> rows=new ArrayList<JSONObject>();
		List<JSONObject> data=new ArrayList<JSONObject>();
		long total=0; 
		if(null!=searchResponse&&null!=searchResponse.getHits()){
			SearchHits hits = searchResponse.getHits();
			for (SearchHit searchHit : hits) {
				total = hits.getTotalHits();
				Map<String, Object> map = searchHit.getSource();
				JSONObject obj = new JSONObject();
				obj.put("id",searchHit.getId());
		        obj.put("title",map.get("title"));
				rows.add(obj);
			}
		}
		for (int i = pageSize*pageNumber-pageSize; i < rows.size(); i++) {
			data.add(rows.get(i));
		}
		JSONObject result = new JSONObject();
		result.put("list",data);
		result.put("total",total);
		return result;
	}
	@Override
	public GardenInformation getGardenInformationById(String id) {
		return gardenInformationRepository.findOne(id);
	}
	
	/**
	 * 校验page是否有值,没有的话初始化成PageSize为10，PageNumber为1
	 * @param searchModel
	 */
	private void checkPage(SearchModel searchModel){
		if(null == searchModel.getPageSize()){
			searchModel.setPageSize(ConfConstant.DEFAULT_PAGE_SIZE);
		}else if (searchModel.getPageSize()>ConfConstant.MAX_PAGE_SIZE){
			searchModel.setPageSize(ConfConstant.MAX_PAGE_SIZE);
		}else if (searchModel.getPageSize()<ConfConstant.MIN_PAGE_SIZE){
			searchModel.setPageSize(ConfConstant.MIN_PAGE_SIZE);
		}
		//对工具类中最小页码为0表示不理解
		if(null == searchModel.getPageNumber()){
			searchModel.setPageNumber(1);
		}else if (searchModel.getPageNumber()>ConfConstant.MAX_PAGE_NUMBER){
			searchModel.setPageNumber(ConfConstant.MAX_PAGE_NUMBER);
		}else if (searchModel.getPageNumber()<1){
			searchModel.setPageNumber(1);
		}
		
	}
	
}
