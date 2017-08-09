package com.huishu.ait.test;

import java.util.List;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.app.Application;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.repository.garden.GardenRepository;
import com.huishu.ait.repository.garden_user.GardenUserRepository;
import com.huishu.ait.service.garden.impl.GardenServiceImpl;

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
	@Autowired
	private GardenServiceImpl impl;
	@Test
	public void testFindGardensList() {
		GardenDTO dto = new GardenDTO();
//		dto.setArea("北京");
//		dto.setIndustryType("节能环保");
//		dto.setPageNum(0);
		dto.setPageSize(10);
		JSONArray findGardensList = impl.findGardensList(dto);
		System.out.println(findGardensList.toJSONString());
	}
	@Test
	public void testFindGardensCondition(){
		GardenDTO dto = new GardenDTO();
		dto.setUserId(1);
//		dto.setPageNum(0);
		dto.setPageSize(10);
		JSONArray arr = impl.findGardensCondition(dto);
		System.out.println(arr.toJSONString());
	}
	@Test
	public void testFind(){
		JSONArray arr = new JSONArray();
		List<String> gardenName = gardenUserRepository.findGardensCondition(1);
		SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
		BoolQueryBuilder bq = new BoolQueryBuilder();
		bq.must(QueryBuilders.termsQuery("park", gardenName));
		SearchResponse response = requestBuilder.setQuery(bq).addSort(SortBuilders.fieldSort("publishDateTime").order(SortOrder.DESC)).setFrom(0).execute().actionGet();
		System.out.println(requestBuilder);
		SearchHits hits = response.getHits();
//		hits.forEach(s -> arr.add(s.getSource()));
		hits.forEach(System.out::println);
//		for (SearchHit searchHit : hits) {
//			searchHit.getSource();
//		}
	}
	@Test
	public void test2(){
		/*List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "updateDate"));*/

//		PageRequest pageRequest = new PageRequest(0, 10);
//		Page<Garden> findGardensList = gardenRepository.findByAreaAndIndustryType("北京", "互联网", new PageRequest(0, 10));
//		System.out.println(findGardensList.iterator().toString());
	}
}
