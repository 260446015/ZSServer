package com.huishu.ZSServer.service;

import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.TYPE;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.jpa.domain.Specification;

import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.forget.category.CategoryModel;
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
			else
				bq.must(QueryBuilders.termQuery(k, v));
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
					if (entry.getValue() instanceof String) {
						String key = entry.getKey();
						String value = (String) entry.getValue();
						if (!value.equals("不限") && !value.equals("全部")) {
							predicates.add(cb.equal(root.<String> get(key), value));
						}
					}
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
}
