package com.huishu.aitanalysis.service.indus;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.aitanalysis.entity.IndustryInfo;
import com.huishu.aitanalysis.es.repository.Index3Elasticsearch;
import com.huishu.aitanalysis.repository.industry.IndustryInfoRepository;
import com.huishu.aitanalysis.util.ESDataUtil;
import com.huishu.aitanalysis.util.Util;

@Service
public class IndustryInfoServiceImpl implements IndustryInfoService {

	@Autowired
	private IndustryInfoRepository repository;
	@Autowired
	private Index3Elasticsearch search;
	@Autowired
	private Client client;

	@Override
	public IndustryInfo getIndusbyIndustryLabel(String industryLabel) {
		IndustryInfo info2 = null;
		try {
			info2 = repository.findByIndustryThree(industryLabel);

		} catch (Exception e) {
			return null;
		}

		return info2;
	}

	@Override
	public boolean removalInfo(JSONObject jsonObject) {
		String dimension = Util.getDimension(jsonObject);
		if (dimension.equals("融资快讯")) {
			String str = "$融资快讯";
			Long dId = Util.getDimensionId(jsonObject, str);
			if (35002001 <= dId && dId <= 35002005) {
				dimension = "融资动态";
			} else if (35001001 <= dId && dId <= 35001005) {
				dimension = "融资快讯";
			}
		}
		try {

			if (dimension.equals("融资快讯")) {
				// 去重融资快讯的数据
				String company = Util.getFinancingCompany(jsonObject);
				String articleLink = Util.getArticleLink(jsonObject);
				BoolQueryBuilder bq = QueryBuilders.boolQuery();
				if (StringUtil.isNotEmpty(company)) {
					bq.must(QueryBuilders.termQuery("financingCompany", company));
				}
				if (StringUtil.isNotEmpty(articleLink)) {
					bq.must(QueryBuilders.termQuery("articleLink", articleLink));
				}
				SearchRequestBuilder request = ESDataUtil.getSearchRequestBuilder(client);
				TermsBuilder companyBuilder = AggregationBuilders.terms("company").field("company");
				TermsBuilder articleBuilder = AggregationBuilders.terms("articleLink").field("articleLink");
				TermsBuilder idBuilder = AggregationBuilders.terms("id").field("id");
				articleBuilder.subAggregation(idBuilder);
				companyBuilder.subAggregation(articleBuilder).size(1000);
				SearchResponse searchResponse = request.setQuery(bq).addAggregation(companyBuilder).execute()
						.actionGet();
				Terms terms = searchResponse.getAggregations().get("company");
				List<Bucket> zerobuckets = terms.getBuckets();
				List<JSONObject> list = new ArrayList<JSONObject>();
				;
				for (Bucket bucket : zerobuckets) {
					String zeroCom = bucket.getKeyAsString();
					System.out.println("公司名：" + zeroCom);
					long docCount = bucket.getDocCount();
					System.out.println(docCount);
					Terms zterm = bucket.getAggregations().get("articleLink");
					List<Bucket> twobucket = zterm.getBuckets();
					for (Bucket bucket2 : twobucket) {
						String twoarticle = bucket2.getKeyAsString();
						long docCount2 = bucket2.getDocCount();
						Terms thterm = bucket2.getAggregations().get("id");
						List<Bucket> buckets = thterm.getBuckets();
						for (Bucket bucket3 : buckets) {
							String key = bucket3.getKeyAsString();
							JSONObject obj = new JSONObject();
							obj.put("id", key);
							obj.put("value", docCount2);
							list.add(obj);
						}
					}
				}
				list.forEach(action -> {
					if (action.getLongValue("value") != 1) {
						search.delete(action.getString("id"));
					}
					;
				});
				return true;
			} else {
				// 去重融资动态的数据
				String title = Util.getTitle(jsonObject);
				// String company = Util.getFinancingCompany(jsonObject);

				String articleLink = Util.getArticleLink(jsonObject);

				BoolQueryBuilder bq = QueryBuilders.boolQuery();
				if (StringUtil.isNotEmpty(title)) {
					bq.must(QueryBuilders.termQuery("title", title));
				}
				if (StringUtil.isNotEmpty(articleLink)) {
					bq.must(QueryBuilders.termQuery("articleLink", articleLink));
				}
				SearchRequestBuilder request = ESDataUtil.getSearchRequestBuilder(client);
				TermsBuilder companyBuilder = AggregationBuilders.terms("title").field("title");
				TermsBuilder articleBuilder = AggregationBuilders.terms("articleLink").field("articleLink");
				TermsBuilder idBuilder = AggregationBuilders.terms("id").field("id");
				articleBuilder.subAggregation(idBuilder);
				companyBuilder.subAggregation(articleBuilder).size(1000);
				SearchResponse searchResponse = request.setQuery(bq).addAggregation(companyBuilder).execute()
						.actionGet();
				Terms terms = searchResponse.getAggregations().get("title");
				List<Bucket> zerobuckets = terms.getBuckets();
				List<JSONObject> list = new ArrayList<JSONObject>();
				;
				for (Bucket bucket : zerobuckets) {
					String zeroCom = bucket.getKeyAsString();
					System.out.println("公司名：" + zeroCom);
					long docCount = bucket.getDocCount();
					System.out.println(docCount);
					Terms zterm = bucket.getAggregations().get("articleLink");
					List<Bucket> twobucket = zterm.getBuckets();
					for (Bucket bucket2 : twobucket) {
						String twoarticle = bucket2.getKeyAsString();
						System.out.println(">>>" + twoarticle);
						long docCount2 = bucket2.getDocCount();
						System.out.println(">>>>" + docCount2);
						Terms thterm = bucket2.getAggregations().get("id");
						List<Bucket> buckets = thterm.getBuckets();
						for (Bucket bucket3 : buckets) {
							String key = bucket3.getKeyAsString();
							// long docCount3 = bucket3.getDocCount();
							JSONObject obj = new JSONObject();
							obj.put("id", key);
							obj.put("value", docCount2);
							list.add(obj);
						}
					}
				}
				list.forEach(action -> {
					if (action.getLongValue("value") != 1) {
						search.delete(action.getString("id"));
					}
					;
				});
				return true;
			}

		} catch (Exception e) {
			return false;
		}
	}
}
