package com.huishu.ZSServer.service.label.impl;

import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.INDEX3;
import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.INDEX2;
import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.TYPE2;

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
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.service.label.GetLabelService;

/**
 * @author hhy
 * @date 2018年2月8日
 * @Parem
 * @return 
 */
@Service
public class GetLabelServiceImpl implements GetLabelService {
	
	@Autowired
	protected ElasticsearchTemplate template;
	
	@SuppressWarnings("unused")
	@Override
	public List<String> getLabelInfo(JSONObject obj) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		BoolQueryBuilder or = new BoolQueryBuilder();
		TermsBuilder industryBuilder = null;
		String type = obj.getString("type");
		JSONArray arr = obj.getJSONArray("info");
		for(int i=0;i<arr.size();i++){
			JSONObject ject = arr.getJSONObject(i);
			if(type.equals("one")){
				or.should(QueryBuilders.termQuery("idustryThree", ject.getString("industry")));
			}else{
				or.should(QueryBuilders.wildcardQuery("industry", "*"+ject.getString("industry")+"*"));
			}
		}
		bq.must(or);
		if(type.equals("two")){
			bq.must(QueryBuilders.termQuery("dimension", KeyConstan.RONGZIKUAIXUN));
			industryBuilder = AggregationBuilders.terms("industry").field("industry");
			
		}else{
			industryBuilder = AggregationBuilders.terms("idustryThree").field("idustryThree");
		}
		
		TermsBuilder areaBuilder = AggregationBuilders.terms("area").field("area");
		industryBuilder.subAggregation(areaBuilder);
		SearchQuery query = getSearchQueryBuilder(type).addAggregation(industryBuilder).withQuery(bq).build();
		List<String> list = template.query(query, res->{
			List<String> sList = new ArrayList<String>();
			Terms t = null;
			if(type.equals("one")){
				t = res.getAggregations().get("idustryThree");
			}else{
				t = res.getAggregations().get("industry");
			}
			for (Bucket bucket : t.getBuckets()) {
				String industry = bucket.getKeyAsString();
				if(industry.equals("物联网")||industry.equals("生物医药")||industry.contains("生物技术")||industry.equals("人工智能")||industry.equals("大数据")){
					Terms te = bucket.getAggregations().get("area");
					for(Bucket buc : te.getBuckets()){
						if(StringUtil.isEmpty(buc.getKeyAsString())){
							continue;
						}
						String area = buc.getKeyAsString().replaceAll("市", "").replaceAll("自治区", "").replaceAll("省", "");
						if(sList.contains(area)){
							continue;
						}else{
							sList.add(area);
						}
					}
				}
			}
			
			return sList;
		});
		return list;
	}

	/**
	 * @return
	 */
	private NativeSearchQueryBuilder getSearchQueryBuilder(String type) {
		if(type.equals("one")){
			return new NativeSearchQueryBuilder().withIndices(INDEX2).withTypes(TYPE2);
		}else{
			return new NativeSearchQueryBuilder().withIndices(INDEX3).withTypes(TYPE2);
		}
	}

}
