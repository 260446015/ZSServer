package com.huishu.ait.garden.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ConcersUtils.ESUtil;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.Garden;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.garden.service.GardenService;
import com.huishu.ait.repository.garden.GardenRepository;
import com.huishu.ait.repository.garden_user.GardenUserRepository;

@Service
public class GardenServiceImpl implements GardenService {
	@Resource
	private GardenRepository gardenRepository;
	@Resource
	private GardenUserRepository gardenUserRepository;
	@Resource
	private Client client;

	@Override
	public JSONArray findGardensList(GardenDTO dto) {
		// TODO Auto-generated method stub
		String area = dto.getArea();
		String industryType = dto.getIndustryType();
		JSONArray data = new JSONArray();
		try{
			if(StringUtil.isEmpty(area)){
				
			}
			if(StringUtil.isEmpty(industryType)){
				
			}
			List<Garden> findGardensList = gardenRepository.findGardensList(area, industryType);
			for (Garden garden : findGardensList) {
				JSONObject obj = new JSONObject();
				obj.put("name", garden.getName());
				obj.put("address", garden.getAddress());
				obj.put("area", garden.getArea());
				obj.put("description", garden.getDescription());
				obj.put("industryType", garden.getIndustryType());
				data.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
		
//		try {
//			String industry = (String) msg.get("industry");
//			String industryLabel = (String) msg.get("industryLabel");
//			String publishTime = (String) msg.get("publishTime");
//			SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
//			BoolQueryBuilder bq = new BoolQueryBuilder();
//			bq.must(QueryBuilders.termQuery("industry", industry));
//			bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
//			bq.must(QueryBuilders.termQuery("publishTime", publishTime));
//			TermsBuilder vectorBuilder = AggregationBuilders.terms("vector").field("vector");
//			TopHitsBuilder topHits = AggregationBuilders.topHits("hitCount").addSort(SortBuilders.fieldSort("hitCount").order(SortOrder.DESC)).setSize(100);
//			vectorBuilder.subAggregation(topHits);
//			SearchResponse response = requestBuilder.setQuery(bq).addAggregation(vectorBuilder).execute().actionGet();
//			System.out.println(requestBuilder); 
//			Terms aggs = response.getAggregations().get("vector");
//			for (Terms.Bucket e : aggs.getBuckets()) {
//				System.out.println(e.getKey()+"~~~"+e.getDocCount());
//				InternalTopHits hitr = e.getAggregations().get("hitCount");
//				for (SearchHit searchHit : hitr.getHits()) {
//					data.add(searchHit.getSource());
//				}
//			}
//			result.setData(data).setSuccess(true);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			result.setData(data).setSuccess(false);
//		}
//		return result;
	}

	@Override
	public JSONArray findGardensCondition(GardenDTO dto) {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(String.valueOf(dto.getUserId()))){
			
		}
		JSONArray data = new JSONArray();
		List<String> gardenName = gardenUserRepository.findGardensCondition(dto.getUserId());
		SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
		BoolQueryBuilder bq = new BoolQueryBuilder();
		bq.must(QueryBuilders.termsQuery("park", gardenName));
		SearchResponse response = requestBuilder.setQuery(bq).addSort(SortBuilders.fieldSort("publishDateTime").order(SortOrder.DESC)).execute().actionGet();
		System.out.println(requestBuilder);
		SearchHits hits = response.getHits();
		for (SearchHit searchHit : hits) {
			data.add(searchHit.getSource());
		}
		return data;
	}


}
