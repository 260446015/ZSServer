package com.huishu.ZSServer.service.garden.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.google.common.util.concurrent.AbstractService;
import com.huishu.ZSServer.entity.GardenUser;
import com.huishu.ZSServer.entity.dto.AreaSearchDTO;
import com.huishu.ZSServer.entity.dto.GardenDTO;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.es.repository.BaseElasticsearch;
import com.huishu.ZSServer.service.garden.GardenService;

@Service
public class GardenServiceImpl extends AbstractService implements GardenService {
	
	@Autowired
	private BaseElasticsearch baseElasticsearch;
	
	@Override
	public Page<AITInfo> getInformationPush(AreaSearchDTO dto) {
		List<Order> orders=new ArrayList<Order>();
		orders.add(new Order(Direction. DESC, "publishTime"));
		orders.add(new Order(Direction. DESC, "hitCount"));
		PageRequest pageRequest = new PageRequest(0,10,new Sort(orders));
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("dimension",dto.getDimension()));
		bq.must(QueryBuilders.termQuery("dimension",dto.getPark()));
		return baseElasticsearch.search(bq, pageRequest);
	}

	@Override
	public GardenUser getGardenByName(String gardenName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray findGardensList(GardenDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray findGardensCondition(GardenDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<GardenUser> getAttentionGardenList(GardenDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GardenUser attentionGarden(String gardenId, String userId, boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray findGardensByAreaAndIndustry(String area, String leadIndustry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray findGardensAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doStop() {
		// TODO Auto-generated method stub
		
	}

}
