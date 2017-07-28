package com.huishu.ait.service.garden.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.DBConstant;
import com.huishu.ait.service.garden.GardenService;

@Service
public class GardenServiceImpl implements GardenService {
	@Autowired
	private Client client;
	@Override
	public JSONObject getGardenPolicyList(String park) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("park", park));
		bq.must(QueryBuilders.termQuery("articleType", "政策解读"));
		//按时间和点击量降序排列
		SortBuilder countBuilder = SortBuilders.fieldSort("hitCount").order(SortOrder.DESC);
		SortBuilder dateBuilder = SortBuilders.fieldSort("publishDate").order(SortOrder.DESC);
		
		SearchRequestBuilder srb = client.prepareSearch(DBConstant.EsConfig.INDEX);
		srb.setTypes(DBConstant.EsConfig.TYPE);
		srb.addSort(dateBuilder).addSort(countBuilder);
		SearchResponse searchResponse = srb.setQuery(bq).execute().actionGet();
		
		List<JSONObject> rows=null;
		long total=0; 
		if(null!=searchResponse&&null!=searchResponse.getHits()){
			SearchHits hits = searchResponse.getHits();
			rows = new ArrayList<JSONObject>();
			for (SearchHit searchHit : hits) {
				total = hits.getTotalHits();
				Map<String, Object> map = searchHit.getSource();
				JSONObject obj = new JSONObject();
				obj.put("id",searchHit.getId());
		        obj.put("park",map.get("park"));
		        obj.put("title",map.get("title"));
		        obj.put("content",map.get("content"));
				rows.add(obj);
			}
		}
		JSONObject result = new JSONObject();
		result.put("list",rows);
		result.put("total",total);
		return result;
	}
	@Override
	public JSONObject getGardenPolicyById(String id) {
		GetRequest rq = new GetRequest(DBConstant.EsConfig.INDEX,DBConstant.EsConfig.TYPE, id);
		GetResponse response = client.get(rq).actionGet();
		JSONObject result = new JSONObject();;
		//判断非空
		if(!response.isSourceEmpty()){
			Map<String, Object> data = response.getSource();
			//result.put("id",response.getId());
			result.put("title",data.get("title"));
			result.put("vector",data.get("vector"));   //来源
			result.put("publishDateTime",data.get("publishDateTime"));
			  //网站，字段呢？
			result.put("articleLink",data.get("articleLink"));	//网址
			result.put("source",data.get("source"));    //情报采集
			result.put("sourceLink",data.get("sourceLink"));   //情报原址
			result.put("content",data.get("content"));
			result.put("author",data.get("author"));
		}
		return result;
	}
	@Override
	public JSONObject getGardenInformationList(String park) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public JSONObject getGardenInformationById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
