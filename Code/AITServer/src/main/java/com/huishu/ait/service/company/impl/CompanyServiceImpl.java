package com.huishu.ait.service.company.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.entity.Company;
import com.huishu.ait.entity.dto.CompanyDTO;
import com.huishu.ait.repository.company.CompanyRepository;
import com.huishu.ait.service.company.CompanyService;
/**
 * 企业排行榜实现类
 * @author yindawei   
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	private static Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Autowired
	private Client client;
	@Autowired
	private CompanyRepository companyRepository;

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
				bq.must(QueryBuilders.termQuery("publishYear", publishTime));
			}
			String dimension = "企业排行";//这里只做排行榜，先写死
			
			bq.must(QueryBuilders.termQuery("dimension", dimension));
			SearchResponse response = requestBuilder.setQuery(bq).addSort(SortBuilders.fieldSort("publishDate").order(SortOrder.DESC)).setSize(dto.getPageSize()).execute().actionGet();
			SearchHits hits = response.getHits();
			for (SearchHit searchHit : hits) {
				JSONObject obj = new JSONObject();
				JSONObject companie = JSONObject.parseObject(searchHit.getSourceAsString());
				obj.put("id", searchHit.getId());
				obj.put("articleType", companie.getString("articleType"));
				obj.put("vector", companie.getString("vector"));
				obj.put("business", companie.getString("business"));
				DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
				DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				
				Date date = format1.parse(companie.getString("publishTime"));
				String publishDate = format.format(date);
				obj.put("publishDate", publishDate);
				obj.put("content", companie.getString("content"));
				obj.put("title", companie.getString("title"));
				obj.put("summary", StringUtil.replaceHtml(companie.getString("summary")));
				data.add(obj);
			}
			LOGGER.info("查询到的企业:"+data.toJSONString());
		} catch (Exception e) {
			LOGGER.error("企业排行榜查询出错:"+e.getMessage());
		}
		return data;
	}

	@Override
	public List<String> findCname(String park) {
		List<Company> all = companyRepository.findByPark(park);
		List<String> names = new ArrayList<>();
		all.forEach((c)->{
			names.add(c.getCompanyName());
		});
		return names;
		
	}
}
