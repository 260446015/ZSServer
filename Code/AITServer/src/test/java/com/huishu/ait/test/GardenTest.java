package com.huishu.ait.test;

import java.util.List;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.app.Application;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.entity.Garden;
import com.huishu.ait.repository.garden.GardenRepository;
import com.huishu.ait.repository.garden_user.GardenUserRepository;

@RunWith(SpringJUnit4ClassRunner.class)  
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下  
@SpringBootTest(classes = Application.class)  
@WebAppConfiguration 
public class GardenTest {

	@Resource
	private Client client;
	
	@Resource
	private GardenRepository dao;
	@Resource
	private GardenUserRepository gardenUserRepository;
	@Autowired
	private GardenRepository gardenRepository;
	@Test
	public void testFindGardensList() {
		JSONArray jsonArray = new JSONArray();
		String area = "广州";
		String industryType = "新能源";
		SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
		BoolQueryBuilder bq = new BoolQueryBuilder();
		bq.must(QueryBuilders.termQuery("area", area));
		bq.must(QueryBuilders.termQuery("industryType", industryType));
		TermsBuilder parkBuilder = AggregationBuilders.terms("park").field("park");
//		TopHitsBuilder topHits = AggregationBuilders.topHits("hitCount").addSort(SortBuilders.fieldSort("hitCount").order(SortOrder.DESC)).setSize(100);
//		parkBuilder.subAggregation(topHits);
		SearchResponse response = requestBuilder.setQuery(bq).addAggregation(parkBuilder).execute().actionGet();
		System.out.println(requestBuilder);
		Terms terms = response.getAggregations().get("park");
		for (Terms.Bucket entry : terms.getBuckets()) {
			JSONObject obj = new JSONObject();
			JSONArray json = new JSONArray();
			System.out.println("工业园:"+entry.getKey()+"~~~有"+entry.getDocCount()+"个");
		}
	}
	@Test
	public void testFindGardensCondition(){
		SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
		BoolQueryBuilder bq = new BoolQueryBuilder();
//		bq.must(queryBuilder)
	}
	@Test
	public void testFind(){
		List<String> gardenName = gardenUserRepository.findGardensCondition(1);
		SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
		BoolQueryBuilder bq = new BoolQueryBuilder();
		bq.must(QueryBuilders.termsQuery("park", gardenName));
		SearchResponse response = requestBuilder.setQuery(bq).addSort(SortBuilders.fieldSort("publishDateTime").order(SortOrder.DESC)).setFrom(0).execute().actionGet();
		System.out.println(requestBuilder);
		SearchHits hits = response.getHits();
		for (SearchHit searchHit : hits) {
			searchHit.getSource();
		}
	}
	@Test
	public void test2(){
		/*List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "updateDate"));*/

//		PageRequest pageRequest = new PageRequest(0, 10);
		Page<Garden> findGardensList = gardenRepository.findByAreaAndIndustryType("北京", "互联网", new PageRequest(0, 10));
		System.out.println(findGardensList.iterator().toString());
	}
}
