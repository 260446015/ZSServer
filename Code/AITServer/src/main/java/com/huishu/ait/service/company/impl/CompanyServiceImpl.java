package com.huishu.ait.service.company.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.entity.dto.CompanyDTO;
import com.huishu.ait.service.company.CompanyService;
/**
 * 企业排行榜实现类
 * @author yindawei
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	private static Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);


	@Resource
	private Client client;
	/**
	 * 查询企业排行
	 */
	@Override
	public JSONArray findCompaniesOder(CompanyDTO dto) {
		JSONArray data = new JSONArray();
		try {
			SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			String industry = dto.getIndustry();//获取前台传递的产业字段
			String industryLabel = dto.getIndustryLabel();//获取前台传递的产业标签字段
			String publishTime = dto.getPublishTime();//获取前台传递的发布时间字段这里用的是publishTime只有年份查询
			if(null != industry){
				bq.must(QueryBuilders.termQuery("industry", industry));
			}
			if(null != industryLabel){
				bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
			}
			if(null != publishTime){
				bq.must(QueryBuilders.termQuery("publishTime", publishTime));
			}
			String articleType = "企业排行";//这里只做排行榜，先写死
			
			bq.must(QueryBuilders.termQuery("articleType", articleType));
			int from = dto.getPageSize()*dto.getPageNum() - dto.getPageSize();
			if(from < 0){
				from = 0;
			}
			SearchResponse response = requestBuilder.setQuery(bq).setFrom(from).execute().actionGet();
			System.out.println(requestBuilder); 
			SearchHits hits = response.getHits();
			for (SearchHit searchHit : hits) {
				Map<String, Object> source = searchHit.getSource();
				data.add(source);
			}
			LOGGER.info("查询到的企业:"+data.toJSONString());
		} catch (Exception e) {
			LOGGER.error("企业排行榜查询出错:"+e.getMessage());
		}
		return data;
	}

}
