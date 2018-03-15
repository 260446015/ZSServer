package com.huishu.aitanalysis.service.index;

import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import com.huishu.aitanalysis.common.DBConstant.EsConfig;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.aitanalysis.common.Constants.MyPage;
import com.huishu.aitanalysis.common.Constants.MySort;
import com.huishu.aitanalysis.es.entity.Index;
import com.huishu.aitanalysis.es.entity.Index2;
import com.huishu.aitanalysis.es.entity.Index3;
import com.huishu.aitanalysis.es.repository.Index2Elasticsearch;
import com.huishu.aitanalysis.es.repository.Index3Elasticsearch;
import com.huishu.aitanalysis.es.repository.IndexElasticsearch;
//import com.huishu.aitanalysis.repository.IIndexDao;
import com.huishu.aitanalysis.util.EmptyUtils;
import com.huishu.aitanalysis.util.Util;

/**
 * 索引业务层
 * 
 * @author LvDapeng
 * @date 2015年8月22日 上午12:40:36
 */
@Repository("indexService")
public class IndexService implements IIndexService {

	// @Autowired private IIndexDao indexDao;
	@Autowired
	private IndexElasticsearch es;
	@Autowired
	private Index2Elasticsearch es2;
	@Autowired
	private Index3Elasticsearch es3;
	
	@Autowired
	protected ElasticsearchTemplate template;
	@Override
	public void index(Index index, Logger log) {
		Index save = es.save(index);
		log.info("save的结果是》》》》》》》》》》》》》》" + save);

	}

	@Override
	public void indexInfo(Index2 index, Logger log) {
		Index2 save = es2.save(index);
		log.info("save的结果是》》》》》》》》》》》》》》" + save);
	}

	@Override
	public void savaIndex3(Index3 index3, Logger log) {
		Index3 save = es3.save(index3);	
		log.info("save的结果是》》》》》》》》》》》》》》" + save);
	}
	@Override
	public void bulkIndex(List<Index> indexs, Logger log) {
		// indexDao.bulkIndex(indexs, log);
	}
	

	@Override
	public Index queryByID(String documentId, Logger log) {
		return null;
		// return indexDao.queryByID(documentId, log);
	}

	/*@Override
	public boolean modifyByID(String documentId, JSONObject obj, Logger log) {
		return true;
		// return indexDao.modifyByID(documentId, obj, log);
	}*/

	/**
	 * 获取分页请求
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageRequest getPageRequest(Integer pageNo, Integer pageSize) {
		// 分页设置
		Integer page = 0;
		if (EmptyUtils.isEmpty(pageNo)) {
			page = MyPage.page;
		} else {
			// 页面传递的页码是从1开始的, 但是ES查询中页面从0开始,所以这里做减1的处理,
			// 避免查询结果不足一页的时候, 如果是1获取的是第二页数据,这样返回的数据结果就是 空的
			page = pageNo - 1;
		}
		Integer size = 0;
		if (EmptyUtils.isEmpty(pageSize)) {
			size = MyPage.size;
		} else {
			size = pageSize;
		}

		PageRequest pageRequest = new PageRequest(page, size);
		return pageRequest;
	}

	/**
	 * 排序构造
	 * 
	 * @param sortField
	 * @param sortType
	 * @return
	 */
	public FieldSortBuilder getSortBuilder(String sortField, String sortType) {
		// 排序设置
		if (EmptyUtils.isEmpty(sortField)) {
			sortField = MySort.sortField;
		}

		FieldSortBuilder sort = new FieldSortBuilder(sortField);
		sort.ignoreUnmapped(true);

		if (EmptyUtils.isEmpty(sortType)) {
			sortType = MySort.sortType;
		}

		try {
			if ("ASC".equals(sortType.toUpperCase())) {
				sort.order(SortOrder.ASC);
			} else {
				sort.order(SortOrder.DESC);
			}
		} catch (Exception e) {
			sort.order(SortOrder.ASC);
		}

		return sort;
	}

	@Override
	public boolean filterURL(String articleLink) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("articleLink", articleLink));
		Iterable<Index> search = es.search(bq);
		for (Index index : search) {
			if (index.getArticleLink() != null) {
				System.out.println("" + index.getArticleLink());
			}
		}
		if (search.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean filterTitle(String title, String articleLink) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("title", title));
		Iterable<Index> search = es.search(bq);
		if (search.iterator().hasNext()) {

			bq.must(QueryBuilders.termQuery("articleLink", articleLink));
			Iterable<Index> sea = es.search(bq);
			sea.forEach(Index -> {
				es.delete(Index.getId());
				Index findOne = es.findOne(Index.getId());
				if (findOne != null) {
					System.out.println(findOne.getId());
				}
			});
			Iterable<Index> search2 = es.search(bq);
			if (search2.iterator().hasNext()) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	@Override
	public boolean filterByTitleAndLink(String title, String articleLink) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("title", title));
		Iterable<Index2> search = es2.search(bq);
		
		if (search.iterator().hasNext()) {

			bq.must(QueryBuilders.termQuery("articleLink", articleLink));
			Iterable<Index2> sea = es2.search(bq);
			sea.forEach(Index -> {
				es2.delete(Index.getId());
				Index2 findOne = es2.findOne(Index.getId());
				if (findOne != null) {
				}
			});
			Iterable<Index2> search2 = es2.search(bq);
			if (search2.iterator().hasNext()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	//进行去重
	@Override
	public boolean filterByDimensionAndJSON(String dimension2,JSONObject jsonObject) {
		switch(dimension2){
	 	 case ("融资快讯"):
	 		boolean info =  deleteInfo(jsonObject);
	 	 	return info;
	 	 case ("高峰论坛"):
	 		 boolean i = deleteInfo2(jsonObject);
	 		return i;
	 	default:
	 		boolean i1 = deleteInfo3(jsonObject);
	 		return i1;
		}
		
	}
	private boolean deleteInfo3(JSONObject jsonObject) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		String articleLink = Util.getArticleLink(jsonObject);
		String title = Util.getTitle(jsonObject);
		if(StringUtil.isEmpty(articleLink)){
			return false;
		}else{
			bq.must(QueryBuilders.termQuery("articleLink", articleLink));
		}
		PageRequest pageRequest = new PageRequest(0,10);
		SearchQuery query = getSearchQueryBuilder3().withQuery(bq).withPageable(pageRequest).build();
		boolean info = template.query(query, res ->{
			SearchHits hits = res.getHits();
			if (hits != null) {
				SearchHit[] hitsList = hits.getHits();
				for (SearchHit h : hitsList) {
					String title1 = h.getSource().get("title").toString();
					if(title1.equals(title)){
						try {
							es.delete(h.getSource().get("id").toString());
						} catch (Exception e) {
							System.out.println("删除数据失败！");
						}
					}
				}
				return true;
			}else{				
				return true;
			}
		});
		return info;
	}

	//峰会的数据去重
	private boolean deleteInfo2(JSONObject jsonObject) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		String articleLink = Util.getArticleLink(jsonObject);
		String title = Util.getTitle(jsonObject);
		if(StringUtil.isEmpty(articleLink)){
			return false;
		}else{
			bq.must(QueryBuilders.termQuery("articleLink", articleLink));
		}
		PageRequest pageRequest = new PageRequest(0,10);
		SearchQuery query = getSearchQueryBuilder2().withQuery(bq).withPageable(pageRequest).build();
		boolean info = template.query(query, res ->{
			return updateData(title, res);
			}
		);
		SearchQuery query1 = getSearchQueryBuilder3().withQuery(bq).withPageable(pageRequest).build();
		boolean into = template.query(query1, res ->{
			SearchHits hits = res.getHits();
			if (hits != null) {
				SearchHit[] hitsList = hits.getHits();
				for (SearchHit h : hitsList) {
					String title1 = h.getSource().get("title").toString();
					if(title1.equals(title)){
						try {
							es.delete(h.getSource().get("id").toString());
						} catch (Exception e) {
							System.out.println("删除数据失败！");
						}
					}
				}
				return true;
			}else{				
				return true;
			}
		});
		if(into||info){
			return true;
		}else{
			return false;
		}
	}
	//删除融资数据
	private Boolean updateData(String title, SearchResponse res) {
		SearchHits hits = res.getHits();
		if (hits != null) {
			SearchHit[] hitsList = hits.getHits();
			for (SearchHit h : hitsList) {
				String title1 = h.getSource().get("title").toString();
				if(title1.equals(title)){
					try {
						es3.delete(h.getSource().get("id").toString());
						
					} catch (Exception e) {
						System.out.println("删除数据失败！");
					}
				}
			}
			return true;
		}else{				
			return true;
		}
	}

	private boolean deleteInfo(JSONObject jsonObject) {
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		String articleLink = Util.getArticleLink(jsonObject);
		String title = Util.getTitle(jsonObject);
		if(StringUtil.isEmpty(articleLink)){
			return false;
		}else{
			bq.must(QueryBuilders.termQuery("articleLink", articleLink));
		}
		PageRequest pageRequest = new PageRequest(0,10);
		SearchQuery query = getSearchQueryBuilder().withQuery(bq).withPageable(pageRequest).build();
		boolean info = template.query(query, res ->{
			return updateData(title, res);
			}
		);
		return info;
	}

	//融资事件
	private NativeSearchQueryBuilder getSearchQueryBuilder() {
		
		return new NativeSearchQueryBuilder().withIndices(EsConfig.INDEX2).withTypes(EsConfig.TYPE1);
	}
	//产业峰会
	private NativeSearchQueryBuilder getSearchQueryBuilder2() {
		
		return new NativeSearchQueryBuilder().withIndices(EsConfig.INDEX1).withTypes(EsConfig.TYPE1);
	}
	//产业头条
	private NativeSearchQueryBuilder getSearchQueryBuilder3() {
		
		return new NativeSearchQueryBuilder().withIndices(EsConfig.INDEX).withTypes(EsConfig.TYPE);
	}

	

}
