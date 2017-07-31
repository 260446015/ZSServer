package com.huishu.ait.test;

import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram.Order;
import org.elasticsearch.search.aggregations.bucket.range.RangeBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.InternalTopHits;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.app.Application;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.service.company.impl.CompanyServiceImpl;

//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。  
@RunWith(SpringJUnit4ClassRunner.class)  
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下  
@SpringBootTest(classes = Application.class)  
@WebAppConfiguration 
public class CompanyTest {
	/***/
	@Resource
	private Client client;

	@Test
	public void testFindCompaniesOder() {
		JSONArray data = new JSONArray();
		try {
			String industry = "互联网";
			String industryLabel = "网络游戏";
			String publishTime = "2017";
			String articleType = "企业排行";
			SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			bq.must(QueryBuilders.termQuery("industry", industry));
			bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
			bq.must(QueryBuilders.termQuery("publishTime", publishTime));
			bq.must(QueryBuilders.termQuery("articleType", articleType));
//			TermsBuilder titleBuilder = AggregationBuilders.terms("title").field("title");
//			TopHitsBuilder topHits = AggregationBuilders.topHits("hitCount").addSort(SortBuilders.fieldSort("hitCount").order(SortOrder.DESC)).setSize(100);
//			vectorBuilder.subAggregation(topHits);
			SearchResponse response = requestBuilder.setQuery(bq).execute().actionGet();
			System.out.println(requestBuilder); 
			SearchHits hits = response.getHits();
			for (SearchHit searchHit : hits) {
				JSONObject obj = new JSONObject();
				Map<String, Object> source = searchHit.getSource();
				data.add(source);
			}
			System.out.println(data.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
