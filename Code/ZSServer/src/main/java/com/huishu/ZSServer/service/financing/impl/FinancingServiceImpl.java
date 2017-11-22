package com.huishu.ZSServer.service.financing.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.DBConstant;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.es.entity.FinancingInfo;
import com.huishu.ZSServer.es.repository.FinancingElasticsearch;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.financing.FinancingService;

@Service
public class FinancingServiceImpl extends AbstractService<T> implements FinancingService {
	@Autowired
	private FinancingElasticsearch financingElasticsearch;
	@Autowired
	private Client client;
	
	@Override
	public Page<FinancingInfo> getCompanyList(CompanySearchDTO dto) {
		Sort sort;
		if(dto.getSort().equals("按时间")){
			sort = new Sort(Direction.DESC, "financingDate");
		}else{
			sort = new Sort(Direction.DESC, "financingAmount");
		}
		PageRequest pageRequest = new PageRequest(dto.getPageNumber(), dto.getPageSize(),sort);
		return financingElasticsearch.findByAreaLikeAndIndustryLikeAndInvestLike(dto.getArea(),dto.getIndustry(), dto.getInvest(), pageRequest);
	}

	@Override
	public Page<FinancingInfo> getFinancingDynamic() {
		Sort sort = new Sort(Direction.DESC, "publishDate");
		PageRequest pageRequest = new PageRequest(0,10,sort);
		return financingElasticsearch.findAll(pageRequest);
	}

	@Override
	public List<JSONObject> getFinancingCompany(List<String> industry) {
		List<JSONObject> list=new ArrayList<JSONObject>();
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termsQuery("industry", industry));
		SearchRequestBuilder srb = client.prepareSearch(DBConstant.EsConfig.INDEX3).setTypes(DBConstant.EsConfig.TYPE2);
		SearchResponse searchResponse = srb.setQuery(bq).setSize(10).execute().actionGet();
		if (null != searchResponse && null != searchResponse.getHits()) {
			SearchHits hits = searchResponse.getHits();
			hits.forEach((searchHit) -> {
				Map<String, Object> map = searchHit.getSource();
				JSONObject obj = new JSONObject();
				obj.put("id", searchHit.getId());
				obj.put("financingAmount", map.get("financingAmount"));
				obj.put("financingCompany", map.get("financingCompany"));
				obj.put("industry", map.get("industry"));
				list.add(obj);
			});
		}
		return list;
	}

	@Override
	public List<JSONObject> getHistogram(String type) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		List<String> industries = Arrays.asList("人工智能","大数据","物联网","生物技术");
		for (String industry : industries) {
			JSONObject counts = null;
			if(type.equals("week")){
				counts = countByWeek(industry);
			}else if(type.equals("month")){
				counts = countByMonth(industry);
			}else if(type.equals("season")){
				counts = countBySeason(industry);
			}else if(type.equals("year")){
				counts = countByYear(industry);
			}
			list.add(counts);
		}
		return list;
	}

}
