package com.huishu.ZSServer.service.financing.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.DBConstant;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.entity.dto.CompanySearchDTO;
import com.huishu.ZSServer.entity.dto.FinancingSearchDTO;
import com.huishu.ZSServer.es.entity.FinancingInfo;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.financing.FinancingService;

@Service
public class FinancingServiceImpl extends AbstractService<T> implements FinancingService {
	@Autowired
	private Client client;
	
	@Override
	public Page<FinancingInfo> getCompanyList(CompanySearchDTO dto) {
		List<FinancingInfo> list =new ArrayList<FinancingInfo>();
		String sort;
		if(dto.getSort().equals("按时间")){
			sort = "financingDate";
		}else{
			sort = "financingAmount";
		}
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("dimension", KeyConstan.RONGZIKUAIXUN));
		bq.must(QueryBuilders.wildcardQuery("industry","*"+dto.getIndustry()+"*"));
		bq.must(QueryBuilders.wildcardQuery("area","*"+dto.getArea()+"*"));
		bq.must(QueryBuilders.wildcardQuery("invest","*"+dto.getInvest()+"*"));
		SearchRequestBuilder srb = client.prepareSearch(DBConstant.EsConfig.INDEX3).setTypes(DBConstant.EsConfig.TYPE2);
		srb.addSort(SortBuilders.fieldSort(sort).order(SortOrder.DESC));
		SearchResponse searchResponse = srb.setQuery(bq).execute().actionGet();
		long totalHits=0;
		if (null != searchResponse && null != searchResponse.getHits()) {
			SearchHits hits = searchResponse.getHits();
			totalHits = hits.getTotalHits();
			for (SearchHit searchHit : hits) {
				FinancingInfo info = new FinancingInfo();
				Map<String, Object> map = searchHit.getSource();
				info.setId(searchHit.getId());
				info.setFinancingDate(map.get("financingDate").toString());
				info.setFinancingCompany(map.get("financingCompany").toString());
				info.setIndustry(map.get("industry").toString());
				info.setArea(map.get("area").toString());
				info.setInvest(map.get("invest").toString());
				info.setFinancingAmount(map.get("financingAmount").toString());
				info.setInvestor(map.get("investor").toString());
				info.setArticleLink(map.get("articleLink").toString());
				list.add(info);
			}
		}
		PageRequest request = new PageRequest(dto.getPageNumber(), dto.getPageSize());
		return new PageImpl<FinancingInfo>(list,request,totalHits);
	}

	@Override
	public List<JSONObject> getFinancingDynamic() {
		List<JSONObject> list=new ArrayList<JSONObject>();
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("dimension", KeyConstan.RONGZIDONGTAI));
		bq.should(QueryBuilders.wildcardQuery("industry","*人工智能*"));
		bq.should(QueryBuilders.wildcardQuery("industry","*大数据*"));
		bq.should(QueryBuilders.wildcardQuery("industry","*物联网*"));
		bq.should(QueryBuilders.wildcardQuery("industry","*生物技术*"));
		bq.minimumNumberShouldMatch(1);
		SearchRequestBuilder srb = client.prepareSearch(DBConstant.EsConfig.INDEX3).setTypes(DBConstant.EsConfig.TYPE2).addSort("publishDate", SortOrder.DESC);
		SearchResponse searchResponse = srb.setQuery(bq).setSize(10).execute().actionGet();
		if (null != searchResponse && null != searchResponse.getHits()) {
			SearchHits hits = searchResponse.getHits();
			hits.forEach((searchHit) -> {
				Map<String, Object> map = searchHit.getSource();
				JSONObject obj = new JSONObject();
				obj.put("id", searchHit.getId());
				obj.put("title", map.get("title"));
				obj.put("industry", map.get("industry"));
				list.add(obj);
			});
		}
		return list;
	}

	@Override
	public List<JSONObject> getFinancingCompany(List<String> industry) {
		List<JSONObject> list=new ArrayList<JSONObject>();
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("dimension", KeyConstan.RONGZIKUAIXUN));
		for (String in : industry) {
			bq.should(QueryBuilders.wildcardQuery("industry","*"+in+"*"));
		}
		bq.minimumNumberShouldMatch(1);
		SearchRequestBuilder srb = client.prepareSearch(DBConstant.EsConfig.INDEX3).setTypes(DBConstant.EsConfig.TYPE2);
		SearchResponse searchResponse = srb.setQuery(bq).setSize(7).execute().actionGet();
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
	public JSONObject getHistogram(FinancingSearchDTO dto) {
		JSONObject counts = null;
		if(dto.getType().equals("week")){
			counts = countByWeek(dto.getIndustry());
		}else if(dto.getType().equals("month")){
			counts = countByMonth(dto.getIndustry());
		}else if(dto.getType().equals("season")){
			counts = countBySeason(dto.getIndustry());
		}else if(dto.getType().equals("year")){
			counts = countByYear(dto.getIndustry());
		}
		return counts;
	}

}
