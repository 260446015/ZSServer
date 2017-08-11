package com.huishu.ait.service.company.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.entity.dto.CompanyDTO;
import com.huishu.ait.es.entity.CompanyElastic;
import com.huishu.ait.es.repository.company.CompanyElasticsearchRepository;
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
	@Resource
	private CompanyElasticsearchRepository elasticsearchRepository;
	/**
	 * 查询企业排行
	 */
	@Override
	public JSONArray findCompaniesOder(CompanyDTO dto) {
		JSONArray data = new JSONArray();
		try {
			SearchRequestBuilder requestBuilder =  ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			String[] msg = dto.getMsg();
			String industry = msg[0];//获取前台传递的产业字段
			String industryLabel = msg[1];//获取前台传递的产业标签字段
			String publishTime = msg[2];//获取前台传递的发布时间字段这里用的是publishTime只有年份查询
			if(null != industry){
				bq.must(QueryBuilders.termQuery("industry", industry));
			}
			if(!"不限".equals(industryLabel)){
				bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
			}
			if(null != publishTime){
				bq.must(QueryBuilders.termQuery("publishTime", publishTime));
			}
			String articleType = "企业排行";//这里只做排行榜，先写死
			
			bq.must(QueryBuilders.termQuery("articleType", articleType));
			int from = dto.getPageSize()*dto.getPageNumber() - dto.getPageSize();
//			if(from < 0){
//				from = 0;
//			}
			SearchResponse response = requestBuilder.setQuery(bq).setFrom(from+dto.getPageSize()).setSize(dto.getPageSize()).execute().actionGet();
			SearchHits hits = response.getHits();
			for (SearchHit searchHit : hits) {
				JSONObject obj = new JSONObject();
				JSONObject companie = JSONObject.parseObject(searchHit.getSourceAsString());
				obj.put("id", searchHit.getId());
				obj.put("articleType", companie.getString("articleType"));
				obj.put("vector", companie.getString("vector"));
				DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
				DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				
				Date date = format1.parse(companie.getString("publishDate"));
				String publishDate = format.format(date);
				obj.put("publishDate", publishDate);
				obj.put("content", companie.getString("content"));
				obj.put("title", companie.getString("title"));
				data.add(obj);
			}
			LOGGER.info("查询到的企业:"+data.toJSONString());
		} catch (Exception e) {
			LOGGER.error("企业排行榜查询出错:"+e.getMessage());
		}
		return data;
	}
	@Override
	public JSONObject findCompanieOrderById(String coid) {
		CompanyElastic company = new CompanyElastic();
		try {
			company = elasticsearchRepository.findOne(coid);
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		return JSONObject.parseObject(company.toString());
	}

}
