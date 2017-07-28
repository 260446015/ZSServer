package com.huishu.ait.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.DBConstant;
import com.huishu.ait.service.GardenService;

@Service
public class GardenServiceImpl implements GardenService {
	@Autowired
	private Client client;
	@Override
	public List<JSONObject> getGardenPolicyList(String park, String pageNumber, String pageSize) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		bq.must(QueryBuilders.termQuery("park", park));
		bq.must(QueryBuilders.termQuery("articleType", "政策解读"));
		SearchRequestBuilder srb = client.prepareSearch(DBConstant.EsConfig.INDEX);
		srb.setTypes(DBConstant.EsConfig.TYPE);
		
		SearchResponse searchResponse = srb.setQuery(bq).execute().actionGet();

		List<JSONObject> rows=null;
		if(null!=searchResponse&&null!=searchResponse.getHits()){
			SearchHits hits = searchResponse.getHits();
			long totalHits = hits.getTotalHits();
			rows = new ArrayList<JSONObject>();
			for (SearchHit searchHit : hits) {
				Map<String, Object> map = searchHit.getSource();
				JSONObject obj = new JSONObject();
		        obj.put("park",map.get("park"));
		        obj.put("title",map.get("title"));
		        obj.put("content",map.get("content"));
				rows.add(obj);
			}
		}
		List<JSONObject> result = new ArrayList<JSONObject>();
		//i小于分页起始条数，不是小于0(这个貌似没有分页)
		for (int i = 0; i < rows.size(); i++) {
			result.add(rows.get(i));
		}
		//然后将result返回回去
		for (JSONObject jsonObject : result) {
			System.out.println(jsonObject.toString());
		}
		return result;
	}

	
}
