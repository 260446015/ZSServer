package com.huishu.ait.service;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.huishu.ait.echart.Option;
import com.huishu.ait.echart.Tooltip;
import com.huishu.ait.echart.series.Pie;
import com.huishu.ait.echart.series.Serie.SerieData;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.echart.Legend;

/**
 * @author hhy
 * @date 2017年7月29日
 * @Parem
 * @return
 * 
 */
public abstract class AbstractService {

	@Autowired
	protected ElasticsearchTemplate template;

	/**
	 * 获取载体分布统计环形图
	 * 
	 * @param p
	 * @return
	 */
	protected Option getVectorDistribution(QueryBuilder queryBuilder) {
		TermsBuilder vectorBuilder = AggregationBuilders.terms("vector").field("vector");

		TermsBuilder articleBuilder = AggregationBuilders.terms("articleLink").field("articleLink");
		articleBuilder.subAggregation(vectorBuilder);
		SearchQuery query = getSearchQueryBuilder().addAggregation(articleBuilder).withQuery(queryBuilder).build();

		Option result = template.query(query, res -> {
			Option opt = new Option().setTooltip(new Tooltip().setTrigger("item"));
			Pie<SerieData<Long>> pie = new Pie<>();
			pie.addRadiu("40%").addRadiu("55%");
			pie.setName("载体分布统计");
			Legend legend = new Legend();
			Terms articleLink = res.getAggregations().get("articleLink");
			String key = null;
			long count = 0;
			SerieData<Long> data = new SerieData<>(key, count);
			Map<String ,Object> map = new HashMap<String, Object>();
			if (articleLink != null) {
				for (Terms.Bucket e1 : articleLink.getBuckets()) {
					Terms vector = e1.getAggregations().get("vector");
					for (Bucket bucket : vector.getBuckets()) {
					    key = bucket.getKeyAsString();
						count = bucket.getDocCount();
						map.put(key, count);
						data.setName(key);
						data.setValue(count);
					}
					
				}
				System.out.println(map.toString());
				legend.addData(key);
//					data = new SerieData<>(key, count);
				System.out.println(data.getName()+data.getValue().toString());
				pie.addData(data);

			}
			opt.setLegend(legend);
			opt.addSeries(pie);
			return opt;
		});
		return result;
	}

	/**
	 * 查询es库，获取更多条件查询
	 * 
	 * @return
	 */
	private NativeSearchQueryBuilder getSearchQueryBuilder() {
		return new NativeSearchQueryBuilder().withIndices(INDEX).withTypes(TYPE);
	}

	/**
	 * 建立查询条件筛选
	 */
	@SuppressWarnings("unused")
	protected BoolQueryBuilder getIndustryContentBuilder(HeadlinesDTO headlinesDTO) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		/** 产业 */
		String industry = headlinesDTO.getIndustry();
		if (StringUtils.isNotEmpty(industry)) {
			bq.must(QueryBuilders.termQuery("industry", industry));
		}
		/** 产业标签 */
		String industryLabel = headlinesDTO.getIndustryLabel();
		if (StringUtils.isNotEmpty(industryLabel)) {
			bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
		}
		/** 载体 */
		String vector = headlinesDTO.getVector();
		if (StringUtils.isNotEmpty(vector)) {
			bq.must(QueryBuilders.termQuery("vector", vector));
		}
		/** 时间 */
		String startDate = headlinesDTO.getStartDate();
		String endDate = headlinesDTO.getEndDate();
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			bq.must(QueryBuilders.rangeQuery("publishDate").from(startDate).to(endDate));
		}
		return bq;
	}
}
