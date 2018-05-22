package com.huishu.ManageServer.service.financing.impl;

import com.huishu.ManageServer.common.conf.DBConstant;
import com.huishu.ManageServer.common.conf.KeyConstan;
import com.huishu.ManageServer.entity.dto.CompanySearchDTO;
import com.huishu.ManageServer.entity.dto.FinancingDTO;
import com.huishu.ManageServer.es.entity.FinancingInfo;
import com.huishu.ManageServer.es.repository.FinancingElasticsearch;
import com.huishu.ManageServer.service.financing.FinancingService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 融资数据
 * @author yindq
 * @date 2018/1/31
 */
@Service
public class FinancingServiceImpl implements FinancingService {
	@Autowired
	private Client client;
	@Autowired
	private FinancingElasticsearch financingElasticsearch;

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
		bq.mustNot(QueryBuilders.termQuery("vector", "投融界"));
		bq.mustNot(QueryBuilders.termQuery("vector", "投资界"));
		SearchRequestBuilder srb = client.prepareSearch(DBConstant.EsConfig.INDEX3).setTypes(DBConstant.EsConfig.TYPE2);
		srb.addSort(SortBuilders.fieldSort(sort).order(SortOrder.DESC));
		SearchResponse searchResponse = srb.setQuery(bq).setSize(dto.getPageSize()).setFrom(dto.getPageNum()*dto.getPageSize()).execute().actionGet();
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
				info.setLogo(map.get("logo").toString());
				list.add(info);
			}
		}
		PageRequest request = new PageRequest(dto.getPageNum(), dto.getPageSize());
		return new PageImpl<FinancingInfo>(list,request,totalHits);
	}

	@Override
	public Boolean dropCompany(String id) {
		financingElasticsearch.delete(id);
		return true;
	}

	@Override
	public FinancingInfo getCompanyById(String id) {
		return financingElasticsearch.findOne(id);
	}

	@Override
	public Boolean saveCompany(FinancingDTO dto) {
		FinancingInfo one = financingElasticsearch.findOne(dto.getId());
		one.setFinancingAmount(dto.getFinancingAmount());
		one.setFinancingCompany(dto.getFinancingCompany());
		one.setFinancingDate(dto.getFinancingDate());
		one.setInvest(dto.getInvest());
		one.setInvestor(dto.getInvestor());
		one.setLogo(dto.getLogo());
		FinancingInfo save = financingElasticsearch.save(one);
		if(save==null){
			return false;
		}
		return true;
	}
}
