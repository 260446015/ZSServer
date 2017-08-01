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
			String industry = dto.getIndustry();//获取前台传递的产业字段
			if(null == industry){
//				industry = "互联网";
				industry = "高科技";
			}
			String industryLabel = dto.getIndustryLabel();//获取前台传递的产业标签字段
			if(null == industryLabel){
//				industryLabel = "电子竞技";
				industryLabel = "网络游戏";
			}
			String publishTime = dto.getPublishTime();//获取前台传递的发布时间字段这里用的是publishTime只有年份查询
			if(null == publishTime){
//				publishTime = String.valueOf(LocalDate.now().getYear());
				publishTime = "2018";
			}
			String articleType = "企业排行";//这里只做排行榜，先写死
			SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			bq.must(QueryBuilders.termQuery("articleType", articleType));
			bq.must(QueryBuilders.termQuery("industry", industry));
			bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
			bq.must(QueryBuilders.termQuery("publishTime", publishTime));
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
