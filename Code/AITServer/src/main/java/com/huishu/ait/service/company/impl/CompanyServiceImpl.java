package com.huishu.ait.service.company.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
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
import org.elasticsearch.search.aggregations.metrics.tophits.InternalTopHits;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanyDTO;
import com.huishu.ait.service.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static Logger LOGGER = Logger.getLogger(CompanyServiceImpl.class);


	@Resource
	private Client client;
	/**
	 * 查询企业排行
	 */
	@Override
	public JSONArray findCompaniesOder(CompanyDTO dto) {
		// TODO Auto-generated method stub
		JSONArray data = new JSONArray();
		try {
			String industry = dto.getIndustry();
			String industryLabel = dto.getIndustryLabel();
			String publishTime = dto.getPublishTime();
			String dimension = dto.getDimension();
			SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			bq.must(QueryBuilders.termQuery("dimension", dimension));
			bq.must(QueryBuilders.termQuery("industry", industry));
			bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
			bq.must(QueryBuilders.termQuery("publishTime", publishTime));
			TermsBuilder vectorBuilder = AggregationBuilders.terms("vector").field("vector");
			TopHitsBuilder topHits = AggregationBuilders.topHits("hitCount").addSort(SortBuilders.fieldSort("hitCount").order(SortOrder.DESC)).setSize(100);
			vectorBuilder.subAggregation(topHits);
			SearchResponse response = requestBuilder.setQuery(bq).addAggregation(vectorBuilder).execute().actionGet();
			System.out.println(requestBuilder); 
			Terms aggs = response.getAggregations().get("vector");
			for (Terms.Bucket e : aggs.getBuckets()) {
				System.out.println(e.getKey()+"~~~"+e.getDocCount());
				InternalTopHits hitr = e.getAggregations().get("hitCount");
				for (SearchHit searchHit : hitr.getHits()) {
					data.add(searchHit.getSource());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}

}
