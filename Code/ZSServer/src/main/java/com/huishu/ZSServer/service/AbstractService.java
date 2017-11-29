package com.huishu.ZSServer.service;

import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.TYPE;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.jpa.domain.Specification;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.forget.category.CategoryModel;
import com.huishu.ZSServer.common.conf.DBConstant;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.HttpUtils;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.entity.openeyes.SearchCount;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.es.repository.BaseElasticsearch;
import com.huishu.ZSServer.repository.openeyes.SearchCountRepository;
import com.huishu.ZSServer.security.Digests;
import com.huishu.ZSServer.security.Encodes;

public class AbstractService<T> {

	private static Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

	@Autowired
	private BaseElasticsearch baseElasticsearch;
	@Autowired
	private SearchCountRepository searchCountRepository;
	@Autowired
	private Client client;
	@Autowired
	protected ElasticsearchTemplate template;
	
	/**
	 * @param title
	 * @param content
	 *            提取文章内部公司名录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getBusiness(String title, String content) {

		// JSONObject findCompany = Analysis.findCompany(title, content);
		JSONObject findCompany = null;
		try {
			findCompany = Analysis.getCompany(title, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> set = new ArrayList<String>();
		if (findCompany.getBoolean("status")) {
			Map<String, CategoryModel> finder = (Map<String, CategoryModel>) findCompany.get("result");

			for (Map.Entry<String, CategoryModel> entry : finder.entrySet()) {
				LOGGER.info("获取的公司名称为：" + entry.getKey());
				set.add(entry.getKey());
				LOGGER.info("对应情感为 ：" + entry.getValue().getCategory());

			}
			return set;
		}else{
			
			return null;
		}
	}

	public Page<AITInfo> getAitinfo(Map<String, Object> params, PageRequest pageRequest) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		params.forEach((k, v) -> {
			if (v instanceof Collection)
				bq.must(QueryBuilders.termsQuery(k, v));
			else{
				if(k.equals("area")){
					bq.must(QueryBuilders.wildcardQuery("area", "*" + v + "*"));
				}else
					bq.must(QueryBuilders.termQuery(k, v));
			}
		});
		Page<AITInfo> search = null;
		try {
			search = baseElasticsearch.search(bq, pageRequest);
			search.forEach((ait) -> {
				ait.setSummary(StringUtil.replaceHtml(ait.getSummary()));
				List<String> business = getBusiness(ait.getTitle(), ait.getContent());
				ait.setBus(business);
			});
		} catch (Exception e) {
			LOGGER.error("获取es数据库aitinfo失败:", e.getMessage());
		}
		return search;
	}

	/**
	 * 封装动态查询数据库的功能
	 * 
	 * @param params
	 *            相当于where条件
	 * @return 需要repository继承JpaSpecificationExecutor
	 */
	protected Specification<T> getSpec(Map<String, Object> params) {
		return new Specification<T>() {
			List<Predicate> predicates = new ArrayList<Predicate>();

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				for (Map.Entry<String, Object> entry : params.entrySet()) {
//					if (entry.getValue() instanceof String) {
						String key = entry.getKey();
						Object value = entry.getValue();
						if (!value.equals("不限") && !value.equals("全部")) {
							predicates.add(cb.equal(root.<String> get(key), value));
						}
//					}
				}
				return query.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
			}
		};
	}

	protected JSONObject getOpenEyesTarget(String spec, Map<String, Object> params,String from) {
		JSONObject parseObj = null;
		try {
			parseObj = JSONObject.parseObject(HttpUtils.sendHttpGet(spec, params));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String today = format.format(date);
			String id = getGeneratedId(from+today);
			SearchCount search = searchCountRepository.findOne(id);
			search = assemblySearchCount(date, today, id, from, search);
			searchCountRepository.save(search);
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.info("查询到的天眼查接口信息:" + parseObj);
		return parseObj;
	}

	private SearchCount assemblySearchCount(Date date, String today, String id, String from, SearchCount search) {
		if(null == search){
			search = new SearchCount();
			search.setId(id);
			search.setToday(today);
			search.setStartTime(date.getTime());
			search.setLastTime(date.getTime());
			search.setTotal(1);
			search.setFromType(from);
		}else{
			search.setLastTime(date.getTime());
			search.setTotal(search.getTotal() + 1);
		}
		return search;
	}

	protected String getGeneratedId(Object info) {
		byte[] hashPassword = Digests.sha1(info.toString().getBytes(), null, Encodes.HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * @param json
	 * @return
	 */
	protected BoolQueryBuilder getQueryBoolBuilder(JSONObject json) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		if (StringUtil.isNotEmpty(json.getString("industry"))) {
			String industry = json.getString("industry");
			bq.must(QueryBuilders.termQuery("industry", industry));
		}
		if (StringUtil.isNotEmpty(json.getString("industryLabel"))) {
			String industryLabel = json.getString("industryLabel");
			bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
		}
		if (StringUtil.isNotEmpty(json.getString("area"))) {
			String area = json.getString("area");
			bq.must(QueryBuilders.wildcardQuery("area", "*" + area + "*"));
		}
		if (StringUtil.isNotEmpty(json.getString("startTime")) && StringUtil.isNotEmpty(json.getString("endTime"))) {
			String startTime = json.getString("startTime");
			String endTime = json.getString("endTime");
			bq.must(QueryBuilders.rangeQuery("publishTime").from(startTime).to(endTime));
		}
		if (StringUtil.isNotEmpty(json.getString("dimension"))) {
			String dimension = json.getString("dimension");
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		if (StringUtil.isNotEmpty(json.getString("keyWord"))) {
			String keyWord = json.getString("keyWord");
			bq.must(QueryBuilders.wildcardQuery("content", "*" + keyWord + "*"));
		}
		return bq;
	}
	/**
	 * @param json
	 * @return
	 */
	protected BoolQueryBuilder getBoolBuilder(JSONObject json) {
		BoolQueryBuilder bq = new BoolQueryBuilder();
		if (StringUtil.isNotEmpty(json.getString("industry"))) {
			String industry = json.getString("industry");
			bq.must(QueryBuilders.termQuery("idustryZero", industry));
		}
		if (StringUtil.isNotEmpty(json.getString("industryLabel"))) {
			String industryLabel = json.getString("industryLabel");
			bq.must(QueryBuilders.termQuery("idustryTwice", industryLabel));
		}
		if (StringUtil.isNotEmpty(json.getString("area"))) {
			String area = json.getString("area");
			bq.must(QueryBuilders.wildcardQuery("area", "*" + area + "*"));
		}
		if (StringUtil.isNotEmpty(json.getString("startTime")) && StringUtil.isNotEmpty(json.getString("endTime"))) {
			String startTime = json.getString("startTime");
			String endTime = json.getString("endTime");
			bq.must(QueryBuilders.rangeQuery("publishTime").from(startTime).to(endTime));
		}
		if (StringUtil.isNotEmpty(json.getString("dimension"))) {
			String dimension = json.getString("dimension");
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		if (StringUtil.isNotEmpty(json.getString("keyWord"))) {
			String keyWord = json.getString("keyWord");
			bq.must(QueryBuilders.wildcardQuery("content", "*" + keyWord + "*"));
		}
		return bq;
	}
	/**
	 * 得到点击数最多的文章id
	 * @return
	 */
	public List<String> getMaxHitCountArticle(BoolQueryBuilder bq){
		TopHitsBuilder hitCount = AggregationBuilders.topHits("hitCount").addField("hitCount");
		AggregationBuilders.terms("").field("");
		
		return null;
	}

	/**
	 * 查询es库，获取更多条件查询
	 * 
	 * @return
	 */
	protected NativeSearchQueryBuilder getSearchQueryBuilder() {
		return new NativeSearchQueryBuilder().withIndices(INDEX).withTypes(TYPE);
	}
	
	/**
	 * 产业融资柱状分布图——按周
	 * @param industry
	 * @return
	 */
	protected JSONObject countByWeek(String[] industry){
		return null;
	}
	
	/**
	 * 产业融资柱状分布图——按月
	 * @param industry
	 * @return
	 */
	protected JSONObject countByMonth(String[] industry){
		return null;
	}
	
	/**
	 * 产业融资柱状分布图——按季度
	 * @param industry
	 * @return
	 */
	protected JSONObject countBySeason(String[] industry){
		JSONObject result = new JSONObject();
		Calendar c = Calendar.getInstance();  
        int currentMonth = c.get(Calendar.MONTH) + 1;
        String todayFour=c.get(Calendar.YEAR)+"第四季";
        String todayThree=c.get(Calendar.YEAR)+"第三季";
        String todayTwo=c.get(Calendar.YEAR)+"第二季";
        String todayOne=c.get(Calendar.YEAR)+"第一季";
        String beforeFour=(c.get(Calendar.YEAR)-1)+"第四季";
        String beforeThree=(c.get(Calendar.YEAR)-1)+"第三季";
        String beforeTwo=(c.get(Calendar.YEAR)-1)+"第二季";
        if (currentMonth >= 1 && currentMonth <= 3){
        	c.set(Calendar.MONTH, 0);
        	String[] charts= {beforeTwo,beforeThree,beforeFour,todayOne};
        	result.put("charts", charts);
        }
        else if (currentMonth >= 4 && currentMonth <= 6){
        	c.set(Calendar.MONTH, 3);
        	String[] charts= {beforeThree,beforeFour,todayOne,todayTwo};
        	result.put("charts", charts);
        }
        else if (currentMonth >= 7 && currentMonth <= 9){
        	c.set(Calendar.MONTH, 6);
        	String[] charts= {beforeFour,todayOne,todayTwo,todayThree};
        	result.put("charts", charts);
        }
        else if (currentMonth >= 10 && currentMonth <= 12){
        	c.set(Calendar.MONTH, 9);
        	String[] charts= {todayOne,todayTwo,todayThree,todayFour};
        	result.put("charts", charts);
        }
        JSONArray array = new JSONArray();
        for (String in : industry) {
        	List<Double> list = new ArrayList<Double>();
        	for (int i = 0; i < 4; i++) {
	        	BoolQueryBuilder bq = QueryBuilders.boolQuery();
	        	bq.must(QueryBuilders.termQuery("dimension", KeyConstan.RONGZIKUAIXUN));
	    		bq.must(QueryBuilders.wildcardQuery("industry","*"+in+"*"));
	    		for (int j = c.get(Calendar.MONTH) + 1; j < c.get(Calendar.MONTH) + 4; j++) {
	    			String month=""+j;
	    			if(j<10) month= "0"+j;
	    			bq.should(QueryBuilders.wildcardQuery("financingDate","*"+c.get(Calendar.YEAR)+"?"+month+"*"));
				}
	    		bq.minimumNumberShouldMatch(1);
	    		SearchRequestBuilder srb = client.prepareSearch(DBConstant.EsConfig.INDEX3).setTypes(DBConstant.EsConfig.TYPE2);
	    		SearchResponse searchResponse = srb.setQuery(bq).execute().actionGet();
	    		Double esdata=0.00;
	    		if (null != searchResponse && null != searchResponse.getHits()) {
	    			Long totalHits = searchResponse.getHits().getTotalHits();
	    			SearchResponse searchResponse2 = srb.setQuery(bq).setSize(totalHits.intValue()).execute().actionGet();
	    			SearchHits hits = searchResponse2.getHits();
	    			for (SearchHit searchHit : hits) {
	    				Map<String, Object> map = searchHit.getSource();
	    				String value = map.get("financingAmount").toString();
	    				 if(value.indexOf("未透露")==-1&&value.indexOf("数")==-1){  
	    					 String dataString = map.get("financingAmount").toString();
	    					 if(dataString.indexOf("亿")==-1){
	    						 esdata += Double.valueOf(dataString.replace("RMB", "").replace("万", ""))*0.001;
	    					 }else{
	    						 esdata += Double.valueOf(dataString.replace("RMB", "").replace("亿", ""));
	    					 }
						 }
					}
	    		}
	    		//RMB某亿
	    		list.add(esdata);
	    		c.add(Calendar.MONTH, -3);
			}
        	c.add(Calendar.MONTH, +12);
	        Collections.reverse(list);
	        JSONObject object = new JSONObject();
	        object.put("data", list);
	        object.put("type", "bar");
	        object.put("name", in);
	        array.add(object);
        }
		result.put("series",array);
		return result;
	}
	
	/**
	 * 产业融资柱状分布图——按年
	 * @param industry
	 * @return
	 */
	protected JSONObject countByYear(String[] industry){
		return null;
	}
}
