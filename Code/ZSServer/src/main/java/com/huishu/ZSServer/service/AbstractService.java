package com.huishu.ZSServer.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.forget.category.CategoryModel;
import com.huishu.ZSServer.common.util.HttpUtils;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.es.repository.BaseElasticsearch;
import com.huishu.ZSServer.security.Digests;
import com.huishu.ZSServer.security.Encodes;

public class AbstractService<T> {

	private static Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

	@Autowired
	private BaseElasticsearch baseElasticsearch;

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
			// TODO Auto-generated catch block
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
		}
		return null;
	}

	public Page<AITInfo> getAitinfo(Map<String, Object> params, PageRequest pageRequest) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		params.forEach((k, v) -> {
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

	protected JSONObject getOpenEyesTarget(String spec, Map<String, String> params) {
		String response = "";
		JSONObject parseObj = null;
		try {
			response = HttpUtils.sendHttpGet(spec, params);
			parseObj = JSONObject.parseObject(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.info("查询到的天眼查接口信息:"+parseObj);
		return parseObj;
	}
	protected String getGeneratedId(Object info){
		byte[] hashPassword = Digests.sha1(info.toString().getBytes(), null, Encodes.HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}
}
