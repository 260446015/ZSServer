package com.huishu.ManageServer.service.industry.summit.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ManageServer.es.entity.SummitInfo;
import com.huishu.ManageServer.service.AbstractService;
import com.huishu.ManageServer.service.industry.summit.IndustrySummitService;
import com.huishu.ManageServer.es.repository.SummitElasticsearch;

/**
 * @author hhy
 * @date 2018年1月16日
 * @Parem
 * @return 
 * 产业峰会相关service 实现类
 */
@SuppressWarnings("rawtypes")
@Service
public class IndustrySummitServiceImpl extends AbstractService implements IndustrySummitService{
	
	@Autowired
	protected ElasticsearchTemplate template;
	
	@Autowired
	private SummitElasticsearch search;
	
	@Override
	public List<String> getAreaLabel(String industry) {
		JSONObject obj1 = new JSONObject();
		TermsBuilder aggBuilder = null;
		obj1.put("dimension", "高峰论坛");
		if(industry.equals("生物技术")){
			obj1.put("idustryZero", "生物产业");
			aggBuilder = AggregationBuilders
					.terms("idustryZero")
					.field("idustryZero");
		}else{
			obj1.put("idustryThree", industry);
			aggBuilder = AggregationBuilders
					.terms("idustryThree")
					.field("idustryThree");
		}
		BoolQueryBuilder bq = getBoolBuilder(obj1);
		TermsBuilder areaBuilder = AggregationBuilders
				.terms("area")
				.field("area");
		aggBuilder.subAggregation(areaBuilder);
		SearchQuery searchQuery = getSearchQueryBuilder().withQuery(bq).addAggregation(aggBuilder)
				.withSort(SortBuilders.fieldSort("publishTime").order(SortOrder.DESC)).build();
		List<String> li =template.query(searchQuery, res->{
			List<String> list = new ArrayList<String>();
			if(res==null){
				return null;
			}else{
				Terms t = null;
				if(industry.equals("生物技术")){
					t = res.getAggregations().get("idustryZero");
				}else{
					t = res.getAggregations().get("idustryThree");
				}
				for( Bucket bucket : t.getBuckets()){
					Terms t1 = bucket.getAggregations().get("area");
					for(Bucket buk : t1.getBuckets()){
						String area = buk.getKeyAsString();
						String[] split = area.split(" ");
						if(split.length==0){
							list.add(area);
						}else{
							String ss = split[0];
							list.add(ss);
						}
					}
				}
				
			}
			return list;
		});
		return li;
	}

	/**
	 * 获取峰会列表
	 */
	@Override
	public Page<SummitInfo> getIndustryList(JSONObject obj) {

		Sort sort;
		if(obj.getString("type").equals("按热度")){
			sort = new Sort(Direction.DESC,"hitCount");
		}else{
			sort = new Sort(Direction.DESC,"publishTime");
		}
		
		BoolQueryBuilder bq = new BoolQueryBuilder();
		BoolQueryBuilder or = new BoolQueryBuilder();
		JSONArray arr = obj.getJSONArray("industry");
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				if(StringUtil.isNotEmpty(str)){
					or.should(QueryBuilders.termQuery("idustryThree",str ));
				}else{
					String indus = jso.getString("indus");
					or.should(QueryBuilders.termQuery("idustryZero",indus));
				}
			}
		}
		bq.must(or);
		String area = obj.getString("area");
		if(StringUtil.isNotEmpty(area)){
			bq.must(QueryBuilders.wildcardQuery("area", "*"+area+"*"));
		}
		PageRequest pageRequest = new PageRequest(obj.getIntValue("number"),obj.getIntValue("size"),sort);
		Page<SummitInfo> page = search.search(bq, pageRequest);
		return page;
	}

	/**
	 * 根据id查看用户信息
	 */
	@Override
	public SummitInfo findSummitInfoById(String id) {
		return search.findOne(id);
	}

	/**
	 * 删除峰会信息
	 */
	@Override
	public Boolean deleteSummitInfoById(String aLong) {
		try {
			search.delete(aLong);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	@Override
	public boolean saveIndudustrySummitInfo(SummitInfo enter) {
		try {
			SummitInfo save = search.save(enter);
			if(save != null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
