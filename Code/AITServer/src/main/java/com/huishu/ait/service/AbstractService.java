package com.huishu.ait.service;

import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.INDEX1;
import static com.huishu.ait.common.conf.DBConstant.EsConfig.TYPE;

import java.security.interfaces.RSAPrivateKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forget.analysis.Analysis;
import com.forget.category.CategoryModel;
import com.forget.findAddress.FindAddress;
import com.huishu.ait.TreeNode.TreeNode;
import com.huishu.ait.common.util.DateUtils;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.common.util.UtilsHelper;
import com.huishu.ait.common.util.datasort.SerieDataComparator;
import com.huishu.ait.echart.series.Serie.SerieData;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.HeadlinesArticleListDTO;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.es.entity.dto.IndicatorDTO;
import com.huishu.ait.security.Digests;
import com.huishu.ait.security.Encodes;
import com.huishu.ait.security.RSAUtils;
import com.merchantKey.articleToKeywordCloud.ArticleConToKeywordCloud;
import com.merchantKey.itemModel.KeywordModel;

/**
 * @author hhy
 * @date 2017年7月29日
 * @Parem
 * @return
 * 
 */
public abstract class AbstractService {
	private static Logger LOGGER = Logger.getLogger(AbstractService.class);
	@Autowired
	private Client client;

	@Autowired
	protected ElasticsearchTemplate template;

	/**
	 * 媒体聚焦--获取载体分布统计环形图
	 * 
	 * @param queryBuilder
	 * @return
	 */
	protected JSONArray getVectorDistribution(QueryBuilder queryBuilder) {

		TermsBuilder vectorBuilder = AggregationBuilders.terms("vector").field("vector");

		TermsBuilder articleBuilder = AggregationBuilders.terms("articleLink").field("articleLink");
		articleBuilder.subAggregation(vectorBuilder).size(1000);
		SearchQuery query = getSearchQueryBuilder().addAggregation(articleBuilder).withQuery(queryBuilder).build();
		JSONArray result = template.query(query, res -> {
			Terms articleLink = res.getAggregations().get("articleLink");
			String key = null;
			long count = 0;
			JSONArray json = new JSONArray();
			Map<String, Object> map = new HashMap<String, Object>();
			if (articleLink != null) {
				for (Terms.Bucket e1 : articleLink.getBuckets()) {
					Terms vector = e1.getAggregations().get("vector");
					for (Bucket bucket : vector.getBuckets()) {
							key = bucket.getKeyAsString();
							count = bucket.getDocCount();
							
								if (map.get(key) != null) {
									map.put(key, (long) map.get(key) + count);
								} else {
									map.put(key, count);
								}
								
						
					}
				}
				for (Entry<String, Object> entry : map.entrySet()) {
					SerieData<Long> data = new SerieData<>(entry.getKey(), (Long) entry.getValue());
					if((Long) entry.getValue()>=10){
						json.add(data);
					}
				}
			}
			return json;
		});
		return sortArray(result);
	}

	/**
	 *
	 * @param array
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private JSONArray sortArray(JSONArray array) {
		List<SerieData<Long>> list = new ArrayList<SerieData<Long>>();

		for (int i = 0; i < array.size(); i++) {
			SerieData<Long> obj = (SerieData<Long>) array.get(i);
			list.add(obj);
		}
		// 排序操作
		Collections.sort(list, new SerieDataComparator());
		// 把数据放回去
		array.clear();
		for (int i = 0; i < list.size(); i++) {
			SerieData<Long> obj = null;
			obj = list.get(i);
			array.add(obj);
		}
		return array;
	}

	/**
	 * 产业头条--关键词云
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	protected JSONArray getCloudWordList(QueryBuilder queryBuilder) {
		List<String> contentList = new ArrayList<String>();
		JSONArray data = new JSONArray();
		SearchQuery query = getSearchQueryBuilder().withQuery(queryBuilder).build();
		data = template.query(query, res -> {
			JSONArray jsonArray = new JSONArray();
			Integer wordCloudNum = 50;
			SearchHits hits = res.getHits();
			if (hits != null) {
				SearchHit[] hitsList = hits.getHits();
				for (SearchHit h : hitsList) {
					if (h.getSource() != null) {
						contentList.add(h.getSource().get("content") + "");
					}
				}
			}
			JSONObject keywordCloud = ArticleConToKeywordCloud.toKeywordCloud(contentList, 0, wordCloudNum);
			if (keywordCloud.getBooleanValue("status")) {
				List<KeywordModel> list = (List<KeywordModel>) keywordCloud.get("result");
				list.forEach(KeywordModel -> {
					String name = KeywordModel.getName();
					if (name.equals("/n ")) {
						list.remove(KeywordModel);
					}
				});
				jsonArray.add((List<KeywordModel>) keywordCloud.get("result"));
			}

			return jsonArray;
		});
		return data;
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
	 * 查询es库，获取更多条件查询 查询企业分类库
	 * 
	 * @return
	 */
	protected NativeSearchQueryBuilder getSearchBuilder() {
		return new NativeSearchQueryBuilder().withIndices(INDEX1).withTypes(TYPE);
	}

	/**
	 * 建立查询条件筛选
	 */
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
			if ("人工智能".equals(industryLabel))
				bq.must(QueryBuilders.termsQuery("industryLabel", industryLabel, "智能机器人"));
			else
				bq.must(QueryBuilders.termQuery("industryLabel", industryLabel));
		}

		/** 载体 */
		String vector = headlinesDTO.getVector();
		if (StringUtils.isNotEmpty(vector)) {
			bq.must(QueryBuilders.termQuery("vector", vector));
		}
		String dimension = headlinesDTO.getDimension();
		if (StringUtils.isNotEmpty(dimension)) {
			bq.must(QueryBuilders.termQuery("dimension", dimension));
		}
		/** 关键词 */
		String keyword = headlinesDTO.getKeyWord();
		if (StringUtils.isNotEmpty(keyword)) {
			bq.must(QueryBuilders.wildcardQuery("content", "*" + keyword + "*"));
			// bq.must(QueryBuilders.fuzzyQuery("content", keyword));
		}
		/** 时间 */
		String startDate = headlinesDTO.getStartDate();
		String endDate = headlinesDTO.getEndDate();
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			bq.must(QueryBuilders.rangeQuery("publishTime").from(startDate).to(endDate));
		}
		return bq;
	}

	/**
	 * 今日头条，排行前十
	 * 
	 * @param bq
	 * @param object
	 * @param pageable
	 * @return
	 */
	protected Page<HeadlinesArticleListDTO> getArticleRank(BoolQueryBuilder bq, Object object, Pageable pageable) {
		// 第一步：按照声量排序取前500个文章
		TermsBuilder articleLinkBuilder = AggregationBuilders.terms("articleLink").field("articleLink")
				.order(Order.count(false)).size(500);

		SearchQuery authorQuery = getSearchQueryBuilder().withQuery(bq).addAggregation(articleLinkBuilder).build();

		List<HeadlinesArticleListDTO> jsonArray = template.query(authorQuery, res -> {

			List<HeadlinesArticleListDTO> list = new ArrayList<HeadlinesArticleListDTO>();

			Map<String, Object> Source = null;
			SearchHits hits = res.getHits();
			for (SearchHit hit : hits) {
				Source = hit.getSource();
				Source.put("_id", hit.getId());
				getDtoInfo(list, Source);
			}

			return list;
		});

		// 第二步：对结果进行排序，按照热度排序，分页取十条数据

		int total = jsonArray.size();
		int pageNumber = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();

		/*
		 * pageable.getSort().forEach(order -> { String property
		 * =order.getProperty(); Direction direction = order.getDirection();
		 * jsonArray.sort((o1, o2) -> { Double v1 = (Double)
		 * UtilsHelper.getValueByFieldName(o1, property); Double v2 = (Double)
		 * UtilsHelper.getValueByFieldName(o2, property);
		 * 
		 * if (Direction.ASC.equals(direction)) { return v1.compareTo(v2); }
		 * return v2.compareTo(v1); });
		 * 
		 * 
		 * });
		 */

		// 排名后对时间进行排序
		pageable.getSort().forEach(order -> {
			// String property = "publishTime";
			String property = order.getProperty();
			;
			Direction direction = order.getDirection();
			jsonArray.sort((o1, o2) -> {
				String v1 = (String) UtilsHelper.getValueByFieldName(o1, property);
				String v2 = (String) UtilsHelper.getValueByFieldName(o2, property);

				if (Direction.ASC.equals(direction)) {
					return v1.compareTo(v2);
				}
				return v2.compareTo(v1);
			});
		});
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonArray.get(i).setRank(i + 1);
		}

		List<HeadlinesArticleListDTO> newList = new ArrayList<>();
		jsonArray.stream().skip(pageNumber * pageSize).limit(pageSize).forEach(newList::add);

		Page<HeadlinesArticleListDTO> results = new PageImpl<>(newList, pageable, total);

		return results;
	}

	/**
	 * 获取文章内容
	 * 
	 * @param list
	 * @param source
	 */
	private void getDtoInfo(List<HeadlinesArticleListDTO> list, Map<String, Object> source) {
		HeadlinesArticleListDTO dto = new HeadlinesArticleListDTO();
		dto.setId(source.get("_id").toString());
		dto.setArticleLink(source.get("articleLink").toString());
		dto.setContent(source.get("content").toString());
		dto.setPublishTime(source.get("publishTime").toString());

		dto.setSource(source.get("source").toString());
		dto.setSourceLink(source.get("sourceLink").toString());
		dto.setTitle(source.get("title").toString());
		dto.setIstop((boolean) source.get("istop"));
		Integer hitCount = (Integer) source.get("hitCount");
		Integer supportCount = (Integer) source.get("supportCount");

		dto.setHitCount((int) source.get("hitCount"));
		dto.setReplyCount((int) source.get("replyCount"));
		dto.setSupportCount((int) source.get("supportCount"));
		if (hitCount.intValue() == 0) {
			dto.setHot(UtilsHelper.getRound(supportCount / (hitCount + 1) * 1.0));
		} else {
			dto.setHot(UtilsHelper.getRound(supportCount / hitCount * 1.0));
		}
		String summary = (String) source.get("summary");
		if (StringUtils.isEmpty(summary)) {
			/** 如果文章摘要不存在，则将内容的前一百数据取出作为摘要 */
			if (dto.getContent().length() > 300) {
				summary = dto.getContent().substring(0, 300);
			} else {
				summary = dto.getContent().substring(0, 100);
			}
			summary = StringUtil.replaceHtml(summary);
			dto.setSummary(summary);
		} else {
			summary = StringUtil.replaceHtml(summary);
			dto.setSummary(summary);
		}
		List<String> set = getBusiness(dto.getTitle(), dto.getContent());
		dto.setBus(set);
		list.add(dto);
	}

	/**
	 * 加工文章集合，获取公司
	 * 
	 * @param page
	 * @return
	 */
	protected Page<AITInfo> setPageBusiness(Page<AITInfo> page) {
		page.forEach(essay -> {
			List<String> bus = getBusiness(essay.getTitle(), essay.getContent());
			essay.setBus(bus);
		});
		return page;
	}

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

	/**
	 * 获取地域的方法
	 * 
	 * @param title
	 * @param content
	 * @return
	 */
	protected static Set<String> getArea(String title, String content) {
		Set<String> findAddress = FindAddress.findAddress(title + ";" + content);
		return findAddress;
	}

	/**
	 * ES的分页查询数据方法
	 * 
	 * @param searchModel
	 *            查询Model
	 * @param termField
	 *            查询条件集合
	 * @param notTermField
	 *            不包含条件集合
	 * @param orderField
	 *            需要排序的字段集合。 PS：请注意先后顺序
	 * @param dataField
	 *            返回的数据存在的字段集合。 PS：ID属性默认有
	 * @param isPage
	 *            是否分页
	 * @return
	 */
	protected JSONArray getEsData(SearchModel searchModel, Map<String, String> termField,
			Map<String, String> notTermField, List<String> orderField, List<String> dataField, boolean isPage) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		// 查询条件
		if (null != termField && termField.size() != 0) {
			for (String key : termField.keySet()) {
				bq.must(QueryBuilders.termQuery(key, termField.get(key)));
			}
		}
		// 不包含条件
		if (null != notTermField && notTermField.size() != 0) {
			for (String key : notTermField.keySet()) {
				bq.mustNot(QueryBuilders.termQuery(key, notTermField.get(key)));
			}
		}

		SearchRequestBuilder srb = ESUtils.getSearchRequestBuilder(client);
		// 按orderField包含的字段降序排列
		if (null != orderField && orderField.size() != 0) {
			orderField.forEach((string) -> srb.addSort(SortBuilders.fieldSort(string).order(SortOrder.DESC)));
		}
		Integer pageSize = searchModel.getPageSize();
		Integer pageNumber = searchModel.getPageNumber();
		SearchResponse searchResponse = srb.setQuery(bq).setSize(pageSize * pageNumber).execute().actionGet();

		JSONArray rows = new JSONArray();
		JSONArray data = new JSONArray();
		Long total = null;
		if (null != searchResponse && null != searchResponse.getHits()) {
			SearchHits hits = searchResponse.getHits();
			total = hits.getTotalHits();
			hits.forEach((searchHit) -> {
				Map<String, Object> map = searchHit.getSource();
				JSONObject obj = new JSONObject();
				obj.put("id", searchHit.getId());
				// 获取所需要的字段值
				if (null != dataField && dataField.size() != 0) {
					for (String string : dataField) {
						obj.put(string, map.get(string));
					}
				}
				rows.add(obj);
			});
		}
		if (isPage) {
			searchModel.setTotalSize(Integer.valueOf(total.toString()));
			for (int i = searchModel.getPageFrom(); i < rows.size(); i++) {
				data.add(rows.get(i));
			}
			return data;
		}
		return rows;

	}

	/**
	 * 获取固定查询条件DEMO
	 * 
	 * @param gardenName
	 *            园区名字
	 * @return
	 */
	protected AreaSearchDTO getAreaSearchDTODemo(String gardenName) {
		AreaSearchDTO searchModel = new AreaSearchDTO();
		searchModel.setPark(gardenName);
		searchModel.setPageSize(8);
		return searchModel;
	}

	/**
	 * 获取经过解密，加密处理后的密码
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
	protected String getPasswordDB(String password, String salt) {
		// 获取当前用户的私钥
		Object priKey = SecurityUtils.getSubject().getSession().getAttribute("privateKey");
		String newPassword = null;
		try {
			newPassword = RSAUtils.decryptByPrivateKey(password, (RSAPrivateKey) priKey);
		} catch (Exception e) {
			return null;
		}
		newPassword = new StringBuilder(newPassword).reverse().toString();
		byte[] saltByte = Encodes.decodeHex(salt);
		byte[] passwordByte = Digests.sha1(newPassword.getBytes(), saltByte, Encodes.HASH_INTERATIONS);
		String passwordTrue = Encodes.encodeHex(passwordByte);
		return passwordTrue;

	}

	/**
	 * 将统计数量计算成为百分比
	 * 
	 * @param ratio
	 * @return
	 */
	protected Map<String, Float> convertData(List<Object[]> ratio) {
		Map<String, Float> map = new HashMap<String, Float>();
		int sum = 0;
		for (int i = 0; i < ratio.size(); i++) {
			Object[] objects = ratio.get(i);
			if (objects[1] == null || objects[1].equals("")) {
				continue;
			}
			String name = (String) objects[1];
			String[] split = name.split("、");
			if (split.length != 1) {
				for (int j = 0; j < split.length; j++) {
					Object[] data = { objects[0], split[j] };
					if (map.containsKey(data[1])) {
						Float value = map.get(data[1]);
						map.put((String) data[1], Integer.parseInt(String.valueOf(data[0])) + value);
					} else {
						map.put((String) data[1], Float.parseFloat(String.valueOf(data[0])));
					}
					sum += Integer.parseInt(String.valueOf(objects[0]));
				}
			} else {
				if (map.containsKey(ratio.get(i)[1])) {
					Float value = map.get(ratio.get(i)[1]);
					map.put((String) ratio.get(i)[1], Integer.parseInt(String.valueOf(ratio.get(i)[0])) + value);
				} else {
					map.put((String) ratio.get(i)[1], Float.parseFloat(String.valueOf(ratio.get(i)[0])));
				}
				sum += Integer.parseInt(String.valueOf(objects[0]));
			}
		}
		Set<String> set = map.keySet();
		for (String key : set) {
			map.put(key, (float) map.get(key) / sum);
		}
		return map;
	}

	protected List<TreeNode> getIndicatorInfo(BoolQueryBuilder bq) {
		TermsBuilder firstIndicatorBuilder = AggregationBuilders.terms("industryOne").field("industryOne");
		// 获取返回结果
		SearchQuery query = getSearchBuilder().addAggregation(firstIndicatorBuilder).withQuery(bq).build();
		JSONArray result = template.query(query, res -> {
			JSONArray json = new JSONArray();
			Terms first = res.getAggregations().get("industryOne");

			for (Terms.Bucket e1 : first.getBuckets()) {
				TreeNode node = new TreeNode();
				int count = 1;
				// 一级指标作为根节点
				node = getNode(count, node, e1.getKeyAsString());
				count++;
				// 将结果存到jsonArray
				json.add(node);
			}
			return json;
		});
		List<TreeNode> list = new ArrayList<TreeNode>();
		for (int i = 0; i < result.size(); i++) {
			TreeNode obj = (TreeNode) result.get(i);
			list.add(obj);
		}
		return list;
	}

	/**
	 * @param keyAsString
	 * @return 构造一级节点
	 */
	private TreeNode getNode(int count, TreeNode node, String keyAsString) {
		node.setName(keyAsString);
		node.setId(count);
		List<TreeNode> children = getFirstChildren(node, keyAsString);
		node.setChildren(children);
		return node;
	}

	/**
	 * 获取一级节点的子节点
	 * 
	 * @param keyAsString
	 * @return
	 */
	private List<TreeNode> getFirstChildren(TreeNode node, String keyAsString) {
		IndicatorDTO dto = new IndicatorDTO();
		dto.setFirstIndicator(keyAsString);
		BoolQueryBuilder bq = getIndicatorContentBuilder(dto);
		TermsBuilder firstIndicatorBuilder = AggregationBuilders.terms("industryOne").field("industryOne");
		TermsBuilder secondIndicatorBuilder = AggregationBuilders.terms("industryTwo").field("industryTwo");
		// 聚合一级分类下的二级分类
		firstIndicatorBuilder.subAggregation(secondIndicatorBuilder);
		// 获取返回结果
		SearchQuery query = getSearchBuilder().addAggregation(firstIndicatorBuilder).withQuery(bq).build();
		JSONArray json = template.query(query, res -> {
			JSONArray jsonArray = new JSONArray();
			Terms first = res.getAggregations().get("industryOne");
			for (Terms.Bucket e1 : first.getBuckets()) {
				Terms second = e1.getAggregations().get("industryTwo");
				for (Terms.Bucket e2 : second.getBuckets()) {
					Integer pid = node.getId();
					if (pid == 0) {
						pid = 1;
					}
					Integer count = 0;
					TreeNode treeNode = new TreeNode();
					treeNode.setName(e2.getKeyAsString());
					treeNode.setId(count + (pid * 100));
					count++;
					List<TreeNode> children = getSecondChild(treeNode, e1.getKeyAsString(), e2.getKeyAsString());
					treeNode.setChildren(children);
					jsonArray.add(treeNode);
				}
			}
			return jsonArray;
		});
		List<TreeNode> list = new ArrayList<TreeNode>();
		for (int i = 0; i < json.size(); i++) {
			TreeNode obj = (TreeNode) json.get(i);
			list.add(obj);
		}
		return list;
	}

	/**
	 * @param treeNode
	 * @param keyAsString
	 * @param keyAsString2
	 * @return
	 */
	private List<TreeNode> getSecondChild(TreeNode treeNode, String keyAsString, String keyAsString2) {
		IndicatorDTO dto = new IndicatorDTO();
		dto.setFirstIndicator(keyAsString);
		dto.setSecondIndicator(keyAsString2);
		BoolQueryBuilder bq = getIndicatorContentBuilder(dto);
		TermsBuilder firstIndicatorBuilder = AggregationBuilders.terms("industryOne").field("industryOne");
		TermsBuilder secondIndicatorBuilder = AggregationBuilders.terms("industryTwo").field("industryTwo");
		TermsBuilder thirdIndicatorBuilder = AggregationBuilders.terms("industryThree").field("industryThree");
		secondIndicatorBuilder.subAggregation(thirdIndicatorBuilder);
		firstIndicatorBuilder.subAggregation(secondIndicatorBuilder);
		// 获取返回结果
		SearchQuery query = getSearchBuilder().addAggregation(firstIndicatorBuilder).withQuery(bq).build();
		JSONArray json = template.query(query, res -> {
			JSONArray jsonArray = new JSONArray();
			Terms first = res.getAggregations().get("industryOne");
			for (Terms.Bucket e1 : first.getBuckets()) {
				Terms second = e1.getAggregations().get("industryTwo");
				for (Terms.Bucket e2 : second.getBuckets()) {
					Terms third = e2.getAggregations().get("industryThree");
					for (Terms.Bucket e3 : third.getBuckets()) {
						Integer pid = treeNode.getId();
						int count = 0;
						TreeNode treeNode1 = new TreeNode();
						treeNode1.setName(e3.getKeyAsString());
						treeNode1.setId(count + (pid * 1000));
						count++;
						List<TreeNode> children = getThirdChild(treeNode1, e1.getKeyAsString(), e2.getKeyAsString(),
								e3.getKeyAsString());
						treeNode1.setChildren(children);
						jsonArray.add(treeNode1);
					}
				}
			}
			return jsonArray;
		});
		List<TreeNode> list = new ArrayList<TreeNode>();
		for (int i = 0; i < json.size(); i++) {
			TreeNode obj = (TreeNode) json.get(i);
			list.add(obj);
		}
		return list;
	}

	/**
	 * 获取四级的数据
	 * 
	 * @param treeNode1
	 * @param keyAsString
	 * @param keyAsString2
	 * @param keyAsString3
	 * @return
	 */
	private List<TreeNode> getThirdChild(TreeNode treeNode1, String keyAsString, String keyAsString2,
			String keyAsString3) {
		IndicatorDTO dto = new IndicatorDTO();
		dto.setFirstIndicator(keyAsString);
		dto.setSecondIndicator(keyAsString2);
		dto.setThirdIndicator(keyAsString3);
		BoolQueryBuilder bq = getIndicatorContentBuilder(dto);
		TermsBuilder firstIndicatorBuilder = AggregationBuilders.terms("industryOne").field("industryOne");
		TermsBuilder secondIndicatorBuilder = AggregationBuilders.terms("industryTwo").field("industryTwo");
		TermsBuilder thirdIndicatorBuilder = AggregationBuilders.terms("industryThree").field("industryThree");
		TermsBuilder fourIndicatorBuilder = AggregationBuilders.terms("industryFour").field("industryFour");
		thirdIndicatorBuilder.subAggregation(fourIndicatorBuilder);
		secondIndicatorBuilder.subAggregation(thirdIndicatorBuilder);
		firstIndicatorBuilder.subAggregation(secondIndicatorBuilder);
		// 获取返回结果
		SearchQuery query = getSearchBuilder().addAggregation(firstIndicatorBuilder).withQuery(bq).build();
		JSONArray json = template.query(query, res -> {
			JSONArray jsonArray = new JSONArray();
			Terms first = res.getAggregations().get("industryOne");
			for (Terms.Bucket e1 : first.getBuckets()) {
				Terms second = e1.getAggregations().get("industryTwo");
				for (Terms.Bucket e2 : second.getBuckets()) {
					Terms third = e2.getAggregations().get("industryThree");
					for (Terms.Bucket e3 : third.getBuckets()) {
						Terms four = e3.getAggregations().get("industryFour");
						for (Terms.Bucket e4 : four.getBuckets()) {
							Integer pid = treeNode1.getId();
							int count = 0;
							TreeNode treeNode2 = new TreeNode();
							treeNode2.setName(e4.getKeyAsString());
							treeNode2.setId(count + (pid * 1000));
							count++;
							List<TreeNode> children = new ArrayList<TreeNode>();
							treeNode2.setChildren(children);
							jsonArray.add(treeNode2);
						}
					}
				}
			}
			return jsonArray;
		});
		List<TreeNode> list = new ArrayList<TreeNode>();
		for (int i = 0; i < json.size(); i++) {
			TreeNode obj = (TreeNode) json.get(i);
			list.add(obj);
		}
		return list;
	}

	/**
	 * @param bq
	 * @return
	 */
	protected JSONArray getBusinessByIndicator(BoolQueryBuilder bq, int length) {

		TermsBuilder firstIndicatorBuilder = AggregationBuilders.terms("industryOne").field("industryOne");
		TermsBuilder secondIndicatorBuilder = AggregationBuilders.terms("industryTwo").field("industryTwo");
		TermsBuilder thirdIndicatorBuilder = AggregationBuilders.terms("industryThree").field("industryThree");
		TermsBuilder fourIndicatorBuilder = AggregationBuilders.terms("industryFour").field("industryFour");
		TermsBuilder businessBuilder = AggregationBuilders.terms("business").field("business");
		JSONArray result = null;
		switch (length) {
		case 1: {
			// 企业聚合到一级分类下
			firstIndicatorBuilder.subAggregation(businessBuilder).size(3000);
			SearchQuery query = getSearchBuilder().addAggregation(firstIndicatorBuilder).withQuery(bq).build();
			result = template.query(query, res -> {
				JSONArray data = new JSONArray();
				Terms agg = res.getAggregations().get("industryOne");
				for (Terms.Bucket e1 : agg.getBuckets()) {
					Terms firsts = e1.getAggregations().get("business");
					for (Terms.Bucket e2 : firsts.getBuckets()) {
						JSONObject json = new JSONObject();
						json.put("firstIndicator", e1.getKey());
						json.put("business", e2.getKey());
						data.add(json);
					}
				}
				return data;
			});
		}
			break;
		case 2: {
			secondIndicatorBuilder.subAggregation(businessBuilder).size(3000);
			firstIndicatorBuilder.subAggregation(secondIndicatorBuilder);
			SearchQuery query = getSearchBuilder().addAggregation(firstIndicatorBuilder).withQuery(bq).build();
			result = template.query(query, res -> {
				JSONArray data = new JSONArray();
				Terms agg = res.getAggregations().get("industryOne");
				for (Terms.Bucket e1 : agg.getBuckets()) {
					Terms firsts = e1.getAggregations().get("industryTwo");
					for (Terms.Bucket e2 : firsts.getBuckets()) {
						Terms seconds = e2.getAggregations().get("business");
						for (Terms.Bucket e3 : seconds.getBuckets()) {
							JSONObject json = new JSONObject();
							json.put("firstIndicator", e1.getKey());
							json.put("secondIndicator", e2.getKey());
							json.put("business", e3.getKey());
							data.add(json);
						}
					}
				}
				return data;
			});
		}
			break;
		case 3: {
			// 企业聚合到四级分类下
			thirdIndicatorBuilder.subAggregation(businessBuilder).size(3000);
			secondIndicatorBuilder.subAggregation(thirdIndicatorBuilder);
			firstIndicatorBuilder.subAggregation(secondIndicatorBuilder);
			SearchQuery query = getSearchBuilder().addAggregation(firstIndicatorBuilder).withQuery(bq).build();
			result = template.query(query, res -> {
				JSONArray data = new JSONArray();
				Terms agg = res.getAggregations().get("industryOne");
				for (Terms.Bucket e1 : agg.getBuckets()) {
					Terms firsts = e1.getAggregations().get("industryTwo");
					for (Terms.Bucket e2 : firsts.getBuckets()) {
						Terms seconds = e2.getAggregations().get("industryThree");
						for (Terms.Bucket e3 : seconds.getBuckets()) {
							Terms thirds = e3.getAggregations().get("business");
							for (Terms.Bucket e4 : thirds.getBuckets()) {
								JSONObject json = new JSONObject();
								json.put("firstIndicator", e1.getKey());
								json.put("secondIndicator", e2.getKey());
								json.put("thirdIndicator", e3.getKey());
								json.put("business", e4.getKey());
								data.add(json);
							}
						}
					}
				}
				return data;
			});
		}
			break;
		case 4: {
			// 企业聚合到四级分类下
			fourIndicatorBuilder.subAggregation(businessBuilder).size(3000);
			thirdIndicatorBuilder.subAggregation(fourIndicatorBuilder);
			secondIndicatorBuilder.subAggregation(thirdIndicatorBuilder);
			firstIndicatorBuilder.subAggregation(secondIndicatorBuilder);
			SearchQuery query = getSearchBuilder().addAggregation(firstIndicatorBuilder).withQuery(bq).build();

			result = template.query(query, res -> {
				JSONArray data = new JSONArray();
				Terms agg = res.getAggregations().get("industryOne");

				for (Terms.Bucket e1 : agg.getBuckets()) {
					Terms firsts = e1.getAggregations().get("industryTwo");
					for (Terms.Bucket e2 : firsts.getBuckets()) {
						Terms seconds = e2.getAggregations().get("industryThree");
						for (Terms.Bucket e3 : seconds.getBuckets()) {
							Terms thirds = e3.getAggregations().get("industryFour");
							for (Terms.Bucket e4 : thirds.getBuckets()) {
								Terms fours = e4.getAggregations().get("business");
								for (Terms.Bucket e5 : fours.getBuckets()) {
									JSONObject json = new JSONObject();
									json.put("firstIndicator", e1.getKey());
									json.put("secondIndicator", e2.getKey());
									json.put("thirdIndicator", e3.getKey());
									json.put("fourIndicator", e4.getKey());
									json.put("business", e5.getKey());
									data.add(json);
								}
							}
						}
					}
				}

				return data;

			});
		}
			break;
		}
		return result;
	}

	/**
	 * 建立关于产业分类的查询条件筛选器
	 * 
	 * @param dto
	 * @return
	 */
	protected BoolQueryBuilder getIndicatorContentBuilder(IndicatorDTO dto) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		String firstIndicator = dto.getFirstIndicator();
		if (StringUtils.isNotEmpty(firstIndicator)) {
			bq.must(QueryBuilders.termQuery("industryOne", firstIndicator));
		}
		String secondIndicator = dto.getSecondIndicator();
		if (StringUtils.isNotEmpty(secondIndicator)) {
			bq.must(QueryBuilders.termQuery("industryTwo", secondIndicator));
		}

		String thirdIndicator = dto.getThirdIndicator();
		if (StringUtils.isNotEmpty(thirdIndicator)) {
			bq.must(QueryBuilders.termQuery("industryThree", thirdIndicator));
		}

		String fourIndicator = dto.getFourIndicator();
		if (StringUtils.isNotEmpty(fourIndicator)) {
			bq.must(QueryBuilders.termQuery("industryFour", fourIndicator));
		}
		/*
		 * String dimension = dto.getDimension(); if
		 * (StringUtils.isNotEmpty(dimension)) {
		 * bq.must(QueryBuilders.termQuery("dimension", dimension)); }
		 */
		return bq;
	}

	protected String[] analysisDate(String day) {
		String time1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = DateUtils.getNow();
		today.add(Calendar.DATE, +1);
		String time2 = sdf.format(today.getTime());
		Calendar nextDate = DateUtils.getNow();
		if (day.equals("今日")) {
			nextDate.add(Calendar.DATE, +1);
			time1 = sdf.format(new Date());
		} else if (day.equals("昨日")) {
			nextDate.add(Calendar.DATE, -1);
			time1 = sdf.format(nextDate.getTime());
		} else if (day.equals("近一周")) {
			nextDate.add(Calendar.DATE, -6);
			time1 = sdf.format(nextDate.getTime());
		} else if (day.equals("近三个月")) {
			nextDate.add(Calendar.MONTH, -3);
			time1 = sdf.format(nextDate.getTime());
		} else if (day.equals("近六个月")) {
			nextDate.add(Calendar.MONTH, -6);
			time1 = sdf.format(nextDate.getTime());
		} else if (day.equals("近一年")) {
			nextDate.add(Calendar.MONTH, -12);
			time1 = sdf.format(nextDate.getTime());
		} else {
			time1 = "2010-01-01";
		}
		String[] times = { time1, time2 };
		return times;
	}

	protected List<String> findArea(String title, String content) {
		try {
			// 分析方法
			JSONObject json = Analysis.getCompany(title, content);
			System.out.println(json);
			if (json.getBoolean("status")) {
				// @SuppressWarnings("unchecked")
				// Map<String, CategoryModel> finder = (Map<String,
				// CategoryModel>) json.get("result");
				// for (Map.Entry<String, CategoryModel> entry :
				// finder.entrySet()) {
				// System.out.println("企业名称:" + entry.getKey());
				// System.out.println("对应情感：" + entry.getValue().getCategory());
				// System.out.println("============");
				// }

				@SuppressWarnings("unchecked")
				HashSet<String> address = (HashSet<String>) json.get("address");
				System.out.println("省市名称:" + address);
				return new ArrayList<>(address);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
