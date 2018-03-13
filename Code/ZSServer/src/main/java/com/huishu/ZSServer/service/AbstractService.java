package com.huishu.ZSServer.service;

import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.TYPE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.es.repository.BaseElasticsearch;
import com.huishu.ZSServer.security.Digests;
import com.huishu.ZSServer.security.Encodes;

public class AbstractService<T> {

	private static Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    private BaseElasticsearch baseElasticsearch;
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
		} else {

			return null;
		}
	}

	public Page<AITInfo> getAitinfo(Map<String, Object> params, PageRequest pageRequest) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		params.forEach((k, v) -> {
			if (v instanceof Collection){
				List<String> list = (List<String>) v;
				bq.should(QueryBuilders.termsQuery("park", list.toArray()));
			}else {
				if (k.equals("area")) {
					bq.must(QueryBuilders.wildcardQuery("area", "*" + v + "*"));
				} else
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
					// if (entry.getValue() instanceof String) {
					String key = entry.getKey();
					Object value = entry.getValue();
					if (!value.equals("不限") && !value.equals("全部")) {
						predicates.add(cb.equal(root.<String> get(key), value));
					}
					// }
				}
				return query.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
			}
		};
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
			bq.must(QueryBuilders.termQuery("idustryThree", industryLabel));
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
	 *
	 * @return
	 */
	public List<String> getMaxHitCountArticle(BoolQueryBuilder bq) {
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
	 *
	 * @param industry
	 * @return
	 */
	protected JSONObject countByWeek(String[] industry) {
		return null;
	}

	/**
	 * 产业融资柱状分布图——按月
	 *
	 * @param industry
	 * @return
	 */
	protected JSONObject countByMonth(String[] industry) {
		return null;
	}

	/**
	 * 产业融资柱状分布图——按季度
	 *
	 * @param industry
	 * @return
	 */
	protected JSONObject countBySeason(String[] industry) {
		JSONObject result = new JSONObject();
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		String todayFour = c.get(Calendar.YEAR) + "第四季";
		String todayThree = c.get(Calendar.YEAR) + "第三季";
		String todayTwo = c.get(Calendar.YEAR) + "第二季";
		String todayOne = c.get(Calendar.YEAR) + "第一季";
		String beforeFour = (c.get(Calendar.YEAR) - 1) + "第四季";
		String beforeThree = (c.get(Calendar.YEAR) - 1) + "第三季";
		String beforeTwo = (c.get(Calendar.YEAR) - 1) + "第二季";
		if (currentMonth >= 1 && currentMonth <= 3) {
			c.set(Calendar.MONTH, 0);
			String[] charts = { beforeTwo, beforeThree, beforeFour, todayOne };
			result.put("charts", charts);
		} else if (currentMonth >= 4 && currentMonth <= 6) {
			c.set(Calendar.MONTH, 3);
			String[] charts = { beforeThree, beforeFour, todayOne, todayTwo };
			result.put("charts", charts);
		} else if (currentMonth >= 7 && currentMonth <= 9) {
			c.set(Calendar.MONTH, 6);
			String[] charts = { beforeFour, todayOne, todayTwo, todayThree };
			result.put("charts", charts);
		} else if (currentMonth >= 10 && currentMonth <= 12) {
			c.set(Calendar.MONTH, 9);
			String[] charts = { todayOne, todayTwo, todayThree, todayFour };
			result.put("charts", charts);
		}
		JSONArray array = new JSONArray();
		for (String in : industry) {
			List<Double> list = new ArrayList<Double>();
			for (int i = 0; i < 4; i++) {
				BoolQueryBuilder bq = QueryBuilders.boolQuery();
				bq.must(QueryBuilders.termQuery("dimension", KeyConstan.RONGZIKUAIXUN));
				bq.must(QueryBuilders.wildcardQuery("industry", "*" + in + "*"));
				for (int j = c.get(Calendar.MONTH) + 1; j < c.get(Calendar.MONTH) + 4; j++) {
					String month = "" + j;
					if (j < 10)
						month = "0" + j;
					bq.should(QueryBuilders.wildcardQuery("financingDate",
							"*" + c.get(Calendar.YEAR) + "?" + month + "*"));
				}
				bq.minimumNumberShouldMatch(1);
				SearchRequestBuilder srb = client.prepareSearch(DBConstant.EsConfig.INDEX3)
						.setTypes(DBConstant.EsConfig.TYPE2);
				SearchResponse searchResponse = srb.setQuery(bq).execute().actionGet();
				Double esdata = 0.00;
				if (null != searchResponse && null != searchResponse.getHits()) {
					Long totalHits = searchResponse.getHits().getTotalHits();
					SearchResponse searchResponse2 = srb.setQuery(bq).setSize(totalHits.intValue()).execute()
							.actionGet();
					SearchHits hits = searchResponse2.getHits();
					for (SearchHit searchHit : hits) {
						Map<String, Object> map = searchHit.getSource();
						String value = map.get("financingAmount").toString();
						if(!StringUtil.isEmpty(value)){
							if (value.indexOf("未") == -1 && value.indexOf("数") == -1 && value.indexOf("近") == -1
									&& value.indexOf("及") == -1 && value.indexOf("过") == -1) {
								String dataString = map.get("financingAmount").toString();
								if (dataString.indexOf("RMB") != -1 || dataString.indexOf("¥") != -1 || dataString.indexOf("￥") != -1
										|| dataString.indexOf("人民币") != -1) {
									String replace = dataString.replace("RMB", "").replace("人民币", "").replace("¥", "").replace("￥", "");
									esdata += conversionData(replace);
								} else if (dataString.indexOf("$") != -1 || dataString.indexOf("美元") != -1 || dataString.indexOf("USD") != -1) {
									String replace = dataString.replace("美元", "").replace("$", "").replace("USD", "");
									esdata += conversionData(replace) * 6.6;
								} else if (dataString.indexOf("港币") != -1) {
									String replace = dataString.replace("港币", "");
									esdata += conversionData(replace) * 0.8455;
								} else {
									LOGGER.error("无法识别的融资金额币种来源：" + dataString);
								}
							}
						}
					}
				}
				// RMB某亿
				BigDecimal bd=new BigDecimal(esdata);
			    bd=bd.setScale(4, BigDecimal.ROUND_HALF_UP);
				list.add(bd.doubleValue());
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
		result.put("series", array);
		return result;
	}

	/**
	 * 产业融资柱状分布图——按年
	 *
	 * @param industry
	 * @return
	 */
	protected JSONObject countByYear(String[] industry) {
		return null;
	}

	/**
	 * 将字符串转成数字（单位：亿）
	 *
	 * @param money
	 * @return
	 */
	protected Double conversionData(String money) {
		Double myMoney;
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(money);
		String moneyNum = m.replaceAll("").trim();
		try {
			if (money.indexOf("亿") != -1) {
				myMoney = Double.valueOf(moneyNum);
			} else if (money.indexOf("千万") != -1) {
				myMoney = Double.valueOf(moneyNum) * 0.1;
			} else if (money.indexOf("万") != -1) {
				myMoney = Double.valueOf(moneyNum) * 0.0001;
			} else {
				myMoney = 0.0;
				LOGGER.error("无法识别的融资金额计量单位：" + money);
			}
		} catch (NumberFormatException e) {
			LOGGER.error("无法识别的融资金额计量单位：" + money);
			return 0.0;
		}
		return myMoney;
	}

	/**
	 * 将拼音简写地域转化成汉字
	 * @param list
	 * @return
	 */
	protected String conversionArea(String string) {
			 switch(string){
	            case "bj":
	            	return "北京";
	            case "tj":
	            	return "天津";
	            case "heb":
	            	return "河北";
	            case "sx":
	            	return "山西";
	            case "nmg":
	            	return "内蒙古";
	            case "ln":
	            	return "辽宁";
	            case "jl":
	            	return "吉林";
	            case "hlj":
	            	return "黑龙江";
	            case "sh":
	            	return "上海";

	            case "js":
	            	return "江苏";

	            case "zj":
	            	return "浙江";

	            case "ah":
	            	return "安徽";

	            case "fj":
	            	return "福建";

	            case "jx":
	            	return "江西";

	            case "sd":
	            	return "山东";

	            case "gd":
	            	return "广东";

	            case "gx":
	            	return "广西";

	            case "han":
	            	return "海南";

	            case "hen":
	            	return "河南";

	            case "hub":
	            	return "湖北";

	            case "hun":
	            	return "湖南";

	            case "cq":
	            	return "重庆";

	            case "sc":
	            	return "四川";

	            case "gz":
	            	return "贵州";

	            case "yn":
	            	return "云南";

	            case "xz":
	            	return "西藏";

	            case "snx":
	            	return "陕西";

	            case "gs":
	            	return "甘肃";

	            case "qh":
	            	return "青海";

	            case "nx":
	            	return "宁夏";

	            case "xj":
	            	return "新疆";

			 }
			return null;
	}

	/**
	 * 将汉字地域转化成拼音简写
	 * @param list
	 * @return
	 */
	protected String conversionAreaTwo(String string) {
			 switch(string){
	            case "北京":
	            	return "bj";
	            case "天津":
	            	return "tj";
	            case "河北":
	            	return "heb";
	            case "山西":
	            	return "sx";
	            case "内蒙古":
	            	return "nmg";
	            case "辽宁":
	            	return "ln";
	            case "吉林":
	            	return "jl";
	            case "黑龙江":
	            	return "hlj";
	            case "上海":
	            	return "sh";
	            case "江苏":
	            	return "js";
	            case "浙江":
	            	return "zj";
	            case "安徽":
	            	return "ah";
	            case "福建":
	            	return "fj";
	            case "江西":
	            	return "jx";
	            case "山东":
	            	return "sd";
	            case "广东":
	            	return "gd";
	            case "广西":
	            	return "gx";
	            case "海南":
	            	return "han";
	            case "河南":
	            	return "hen";
	            case "湖北":
	            	return "hub";
	            case "湖南":
	            	return "hun";
	            case "重庆":
	            	return "cq";
	            case "四川":
	            	return "sc";
	            case "贵州":
	            	return "gz";
	            case "云南":
	            	return "yn";
	            case "西藏":
	            	return "xz";
	            case "陕西":
	            	return "snx";
	            case "甘肃":
	            	return "gs";
	            case "青海":
	            	return "qh";
	            case "宁夏":
	            	return "nx";
	            case "新疆":
	            	return "xj";
			 }
			return null;
	}
}
