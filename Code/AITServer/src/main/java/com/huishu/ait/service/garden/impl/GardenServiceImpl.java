package com.huishu.ait.service.garden.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.Garden;
import com.huishu.ait.entity.GardenUser;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.GardenInformation;
import com.huishu.ait.es.entity.GardenPolicy;
import com.huishu.ait.es.repository.garden.GardenInformationRepository;
import com.huishu.ait.es.repository.garden.GardenPolicyRepository;
import com.huishu.ait.repository.garden.GardenRepository;
import com.huishu.ait.repository.garden_user.GardenUserRepository;
import com.huishu.ait.service.garden.GardenService;

@Service
public class GardenServiceImpl implements GardenService {
	
	@Autowired
	private Client client;
	@Autowired
    private GardenPolicyRepository gardenPolicyRepository;
	@Autowired
    private GardenInformationRepository gardenInformationRepository;
	@Resource
	private GardenRepository gardenRepository;
	@Resource
	private GardenUserRepository gardenUserRepository;
	
	private static Logger LOGGER = LoggerFactory.getLogger(GardenServiceImpl.class);
	
	@Override
	public JSONArray getGardenPolicyList(SearchModel searchModel) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("park", searchModel.getPark()));
		bq.must(QueryBuilders.termQuery("articleType", "政策解读"));
		//按时间和点击量降序排列
		SortBuilder countBuilder = SortBuilders.fieldSort("hitCount").order(SortOrder.DESC);
		SortBuilder dateBuilder = SortBuilders.fieldSort("publishDate").order(SortOrder.DESC);
		
		SearchRequestBuilder srb = ESUtils.getSearchRequestBuilder(client);
		srb.addSort(dateBuilder).addSort(countBuilder);
		Integer pageSize = searchModel.getPageSize();
		Integer pageNumber = searchModel.getPageNumber();
		SearchResponse searchResponse = srb.setQuery(bq).setSize(pageSize*pageNumber).execute().actionGet();
		
		JSONArray rows=new JSONArray();
		JSONArray data=new JSONArray();
		Long total=null; 
		if(null!=searchResponse&&null!=searchResponse.getHits()){
			SearchHits hits = searchResponse.getHits();
			total = hits.getTotalHits();
			for (SearchHit searchHit : hits) {
				Map<String, Object> map = searchHit.getSource();
				JSONObject obj = new JSONObject();
				obj.put("id",searchHit.getId());
		        obj.put("title",map.get("title"));
		        obj.put("content",map.get("content"));
				rows.add(obj);
			}
		}
		searchModel.setTotalSize(Integer.valueOf(total.toString()));
		for (int i = searchModel.getPageFrom(); i < rows.size(); i++) {
			data.add(rows.get(i));
		}
		
		return data;
	}
	@Override
	public GardenPolicy getGardenPolicyById(String id) {
		return gardenPolicyRepository.findOne(id);
	}
	@Override
	public JSONArray getGardenInformationList(SearchModel searchModel) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("park", searchModel.getPark()));
		bq.must(QueryBuilders.termQuery("articleType", "园区情报"));
		//按时间和点击量降序排列 
		SortBuilder countBuilder = SortBuilders.fieldSort("hitCount").order(SortOrder.DESC);
		SortBuilder dateBuilder = SortBuilders.fieldSort("publishDate").order(SortOrder.DESC);
		
		SearchRequestBuilder srb = ESUtils.getSearchRequestBuilder(client);
		srb.addSort(dateBuilder).addSort(countBuilder);
		Integer pageSize = searchModel.getPageSize();
		Integer pageNumber = searchModel.getPageNumber();
		SearchResponse searchResponse = srb.setQuery(bq).setSize(pageSize*pageNumber).execute().actionGet();
		
		JSONArray rows=new JSONArray();
		JSONArray data=new JSONArray();
		Long total=null; 
		if(null!=searchResponse&&null!=searchResponse.getHits()){
			SearchHits hits = searchResponse.getHits();
			total = hits.getTotalHits();
			for (SearchHit searchHit : hits) {
				Map<String, Object> map = searchHit.getSource();
				JSONObject obj = new JSONObject();
				obj.put("id",searchHit.getId());
				obj.put("vector",map.get("vector"));
		        obj.put("title",map.get("title"));
				rows.add(obj);
			}
		}
		searchModel.setTotalSize(Integer.valueOf(total.toString()));
		for (int i = searchModel.getPageFrom(); i < rows.size(); i++) {
			data.add(rows.get(i));
		}
		
		return data;
	}
	@Override
	public GardenInformation getGardenInformationById(String id) {
		return gardenInformationRepository.findOne(id);
	}
	@Override
	public JSONArray getGardenBusinessList(SearchModel searchModel) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("park", searchModel.getPark()));
		bq.must(QueryBuilders.termQuery("dimension", "企业排行"));
		return null;
	}
	
	@Override
	public JSONArray findGardensList(GardenDTO dto) {
		String area = dto.getArea();
		String industryType = dto.getIndustryType();
		String searchName = dto.getSerarchName();
		int pageNum = dto.getPageNum();
		int pageSize = dto.getPageSize();
		int from = pageSize*pageNum - pageSize;
		JSONArray data = new JSONArray();
		Page<Garden> findGardensPage = null;
		try{
			if(StringUtil.isEmpty(area)){
				area = "北京";
			}
			if(StringUtil.isEmpty(industryType)){
				industryType = "节能环保";
			}
			PageRequest pageRequest = new PageRequest(pageNum, pageSize);
			if(!StringUtil.isEmpty(searchName)){
				findGardensPage = gardenRepository.findByNameLike(searchName,pageRequest);
			}else{//
				findGardensPage = gardenRepository.findByAreaAndIndustryType(area, industryType, pageRequest);
			}
			/*List<Garden> gardens = findGardensPage.getContent();
			for (Garden garden : gardens) {
				JSONObject obj = new JSONObject();
				obj.put("name", garden.getName());
				obj.put("address", garden.getAddress());
				obj.put("area", garden.getArea());
				obj.put("description", garden.getDescription());
				obj.put("industryType", garden.getIndustryType());
				data.add(obj);
			}*/
			data.add(findGardensPage);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return data;
	}
	
	@Override
	public JSONArray findGardensCondition(GardenDTO dto) {
		JSONArray data = new JSONArray();
		int from = dto.getPageNum()*dto.getPageSize()-dto.getPageSize();
		if(from < 0){
			from = 0;
		}
		try{
			List<String> gardenName = gardenUserRepository.findGardensCondition(dto.getUserId());
			SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			bq.must(QueryBuilders.termsQuery("park", gardenName));
			SearchResponse response = requestBuilder.setQuery(bq).addSort(SortBuilders.fieldSort("publishDateTime").order(SortOrder.DESC)).setFrom(from).execute().actionGet();
			System.out.println(requestBuilder);
			SearchHits hits = response.getHits();
			for (SearchHit searchHit : hits) {
				data.add(searchHit.getSource());
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("园区动态查询结果:"+data.toJSONString());
		return data;
	}
	
    @Override
    public Page<GardenUser> getAttentionGardenList(GardenDTO dto) {
        Integer userId = dto.getUserId();
        String area = dto.getArea();
        String industryType = dto.getIndustryType();
        try{
            PageRequest pageRequest = new PageRequest(dto.getPageNum(), dto.getPageSize());
            Page<GardenUser> page = null;
            if(StringUtil.isEmpty(area) && StringUtil.isEmpty(industryType)){
                page = gardenUserRepository.findByUserId(userId, pageRequest);
            }else if(!StringUtil.isEmpty(area) && StringUtil.isEmpty(industryType)){
                page = gardenUserRepository.findByUserIdAndArea(userId, area, pageRequest);
            }else if(!StringUtil.isEmpty(industryType) && StringUtil.isEmpty(area)){
                page = gardenUserRepository.findByUserIdAndIndustryType(userId, industryType, pageRequest);
            }else{
                page = gardenUserRepository.findByUserIdAndAreaAndIndustryType(userId, area, industryType, pageRequest);
            }
            return page;
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            return null;
        }
    }
	
}
