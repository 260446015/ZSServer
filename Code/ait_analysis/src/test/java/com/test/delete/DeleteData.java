package com.test.delete;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.huishu.aitanalysis.App;
import com.huishu.aitanalysis.common.DBConstant.EsConfig;
import com.huishu.aitanalysis.es.entity.Index;
import com.huishu.aitanalysis.es.repository.IndexElasticsearch;
import com.test.util.DeleteManager;
import com.test.util.ESTools;

@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = App.class)

public class DeleteData {
	
	@Autowired
	private IndexElasticsearch search;
	@Test
	public void test3(){
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("publishDate", "2016-07-03 10:00:00"));
		bq.must(QueryBuilders.termQuery("dimension", "科学研究"));
		SearchRequestBuilder srb =ESTools.client
				.prepareSearch(EsConfig.INDEX)
				.setTypes(EsConfig.TYPE);
		SearchResponse searchResponse = srb.setQuery(bq).setSize(150).execute().actionGet();
		if(null!=searchResponse&&null!=searchResponse.getHits()){
			SearchHits hits = searchResponse.getHits();
			System.out.println(hits.getTotalHits());
			for (SearchHit searchHit : hits) {
				int i = DeleteManager.deleteSOBanggByKey(searchHit.getId());
				System.out.println(searchHit.getId()+"----"+i);
			}
		}
	}
	@Test
	public void testDeleteEs(){
		System.out.println("=================================");
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("dimension", "科学研究"));
		Iterable<Index> findAll = search.search(bq);
		Map<Integer, Index> map = new HashMap<>();
		for (Index a : findAll) {
			String title = a.getTitle();
			String content = a.getContent();
			int hashCode = (title + content).hashCode();
			if(map.containsKey(hashCode)){
				search.delete(a);
			}else{
				map.put(hashCode, a);
			}
		}
	}
	@Test
	public void testDeleteEss(){
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("dimension", "产业头条"));
//		bq.must(QueryBuilders.termQuery("industry", "互联网+"));
//		bq.must(QueryBuilders.termQuery("emotion", "negative"));
		SearchRequestBuilder srb =ESTools.client
				.prepareSearch(EsConfig.INDEX)
				.setTypes(EsConfig.TYPE);
		SearchResponse searchResponse = srb.setQuery(bq).setSize(10000).addSort(SortBuilders.fieldSort("publishTime").order(SortOrder.DESC)).execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		System.out.println(hits.getTotalHits());
		Map<Integer, SearchHit> map = new HashMap<>();
		int count = 0;
		for (SearchHit searchHit : hits) {
			 Map<String, Object> source = searchHit.getSource();
			 String summary = (String) source.get("summary");
			int hashCode = summary.hashCode();
			if(map.containsKey(hashCode)){
				int i = DeleteManager.deleteSOBanggByKey(searchHit.getId());
				System.out.println("删除"+(++count)+"条数据");
			}else{
				map.put(hashCode, searchHit);
			}
		}
	}
	
}
