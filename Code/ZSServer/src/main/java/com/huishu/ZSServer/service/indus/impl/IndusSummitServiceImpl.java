package com.huishu.ZSServer.service.indus.impl;

import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.INDEX2;
import static com.huishu.ZSServer.common.conf.DBConstant.EsConfig.TYPE2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ZSServer.entity.UserSummitInfo;
import com.huishu.ZSServer.es.entity.Essay;
import com.huishu.ZSServer.es.entity.SummitInfo;
import com.huishu.ZSServer.es.repository.EssayElasticsearch;
import com.huishu.ZSServer.es.repository.EssayElasticsearch2;
import com.huishu.ZSServer.es.repository.EssayElasticsearch3;
import com.huishu.ZSServer.es.repository.SummitElasticsearch;
import com.huishu.ZSServer.repository.summit.SummitRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.indus.IndusSummitService;

/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 * 产业峰会service实现层
 */
@Service
public class IndusSummitServiceImpl extends AbstractService implements IndusSummitService {
	private Logger LOGGER = LoggerFactory.getLogger(IndusSummitServiceImpl.class);
	
	@Autowired
	protected ElasticsearchTemplate template;
	
	@Autowired
	protected Client client;
	
	@Autowired
	private SummitElasticsearch search;
	
	@Autowired
	private EssayElasticsearch essayElasticsearch;
	@Autowired
	private EssayElasticsearch2 essayElasticsearch2;
	@Autowired
	private EssayElasticsearch3 essayElasticsearch3;
	
	@Autowired
	private SummitRepository rep;
	
	
	/**
	 * 根据 条件查看 产业峰会 列表
	 */
	@Override
	public Page<SummitInfo> getIndustryForPage(JSONObject obj){
		BoolQueryBuilder bq = getBoolBuilder(obj);
		Page<SummitInfo> page = template.queryForPage(getSearchQueryBuilder().withQuery(bq).build(), SummitInfo.class);
		return page;
	}

	/**
	 * 查询es库，获取更多条件查询
	 * 
	 * @return
	 */
	protected NativeSearchQueryBuilder getSearchQueryBuilder() {
		return new NativeSearchQueryBuilder().withIndices(INDEX2).withTypes(TYPE2);
	}

	/**
	 * 根据id查看文章详情
	 */
	@Override
	public Essay getSummitInfoById(String id) {
		Essay one = essayElasticsearch.findOne(id);
		if(one==null){
			one = essayElasticsearch2.findOne(id);
		}
		if(one==null){
			one = essayElasticsearch3.findOne(id);
		}
		List<String> business = getBusiness(one.getTitle(), one.getContent());
		String company = one.getBusiness();
		if(!StringUtil.isEmpty(company)){
			if(business.size()==1&&business.get(0).equals("暂无")){
				String[] split = company.split("、");
				for (String string : split) {
					if(!StringUtil.isEmpty(string)){
						if(business.get(0).equals("暂无")){
							business.set(0, string);
						}else{
							business.add(string);
						}
					}
				}
			}else{
				String[] split = company.split("、");
				for (String string : split) {
					if(!StringUtil.isEmpty(string)){
						business.add(string);
					}
				}
			}
		}
		one.setBus(business);
		return one;
	}

	/**
	 * 关注峰会
	 */
	@Override
	public boolean insertSummitInfoById(UserSummitInfo info) {
		UserSummitInfo usi = rep.findByAidAndUid(info.getAid(),info.getUid());
		UserSummitInfo save = null;
		if( usi == null ){
			save = rep.save(info);
		}else if( usi.getUid()==info.getUid() && usi.getAid().equals(info.getAid())){
			System.out.println("该用户信息已存在>>>>>>"+usi.getAid());
			return false;
		}
		if(save!= null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 *  取消 关注 峰会
	 */
	@Override
	public boolean deleteSummitInfoById(Long id) {
		LOGGER.info("取消关注的id为：>>>>>"+id);
		rep.delete(id);
		boolean info = rep.exists(id);
		if(info){
			LOGGER.debug("取消关注失败："+info);
			return false;
		}else{
			return true;
		}
	}
	

	
	@Override
	public List<SummitInfo> findIndustrySummitList(JSONObject obj) {
		BoolQueryBuilder bq = getBoolBuilder(obj);
		SearchQuery searchQuery = getSearchQueryBuilder().withQuery(bq)
				.withSort(SortBuilders.fieldSort("publishTime").order(SortOrder.DESC)).build();
		List<SummitInfo> info = template.query(searchQuery, res->{
			List<SummitInfo> list= new ArrayList<SummitInfo>();
			SearchHits hits = res.getHits();
			for (SearchHit hit : hits) {
				Map<String, Object> map = hit.getSource();
				SummitInfo suinfo = new SummitInfo();
				suinfo.setAddress((String) map.get("address"));
				suinfo.setArea(map.get("area").toString());
				suinfo.setArticleLink(map.get("articleLink").toString());
				suinfo.setId(hit.getId());
				if(StringUtil.isEmpty(map.get("bus").toString())){
					suinfo.setBus("");
				}else{
					suinfo.setBus(map.get("bus").toString());
				}
				if(StringUtil.isEmpty(map.get("business").toString())){
					suinfo.setBusiness("");
				}else{
					suinfo.setBusiness(map.get("business").toString());
				}		
				suinfo.setContent(map.get("content").toString());
				suinfo.setTitle(map.get("title").toString());
				suinfo.setDimension(map.get("dimension").toString());
				suinfo.setEmotion(map.get("emotion").toString());
				suinfo.setHasWarn((Boolean) map.get("hasWarn"));
				if(StringUtil.isEmpty(map.get("exhibitiontime").toString())){
					suinfo.setExhibitiontime("");
				}else{
					suinfo.setExhibitiontime(map.get("exhibitiontime").toString());
				}			
				suinfo.setIstop((Boolean) map.get("istop"));
				suinfo.setIdustryThree(map.get("idustryThree").toString());
				suinfo.setPublishTime(map.get("publishTime").toString());
				suinfo.setIdustryZero(map.get("idustryZero").toString());
				suinfo.setIdustryTwice(map.get("idustryTwice").toString());
				suinfo.setLogo(map.get("logo").toString());
				suinfo.setSource(map.get("source").toString());
				list.add(suinfo);
			}
			return list;
		});
		return info;
	}

	/**
	 * 获取所有数据
	 */
	@Override
	public Page<SummitInfo> findAll() {
		Pageable pageable = new PageRequest(0, 8);
		Page<SummitInfo> all = null;
		try {
			 all = search.findAll(pageable);
			
		} catch (Exception e) {

		}
		
		return all;
	}

	/**
	 * 获取峰会列表
	 */
	@Override
	public Page<SummitInfo> getIndustryList(JSONObject obj) {
		
		Sort sort;
		if(obj.getString("type").equals("按热度")){
			sort = new Sort(Direction.DESC,"hitCount");
		}else{
			sort = new Sort(Direction.DESC,"publishTime");
		}
		
		BoolQueryBuilder bq = new BoolQueryBuilder();
		BoolQueryBuilder or = new BoolQueryBuilder();
		JSONArray arr = obj.getJSONArray("industry");
		if(arr!= null){
			for(int i=0;i<arr.size();i++){
				JSONObject jso = arr.getJSONObject(i);
				String str = jso.getString("value");
				if(StringUtil.isNotEmpty(str)){
					or.should(QueryBuilders.termQuery("idustryThree",str ));
				}else{
					String indus = jso.getString("indus");
					or.should(QueryBuilders.termQuery("idustryZero",indus));
				}
			}
		}
		bq.must(or);
		String area = obj.getString("area");
		if(StringUtil.isNotEmpty(area)){
			bq.must(QueryBuilders.wildcardQuery("area", "*"+area+"*"));
		}
		PageRequest pageRequest = new PageRequest(obj.getIntValue("number"),obj.getIntValue("size"),sort);
		Page<SummitInfo> page = search.search(bq, pageRequest);
		return page;
	}

	/**
	 * 关注峰会详情
	 */
	@Override
	public String saveSummitInfoById(String aid,Long uid) {
		SummitInfo one = search.findOne(aid);
		UserSummitInfo usi = rep.findByAidAndUid(aid,uid);
		if(usi==null){
			try {
				UserSummitInfo entity = new UserSummitInfo();
				String[] split = one.getAddress().split("\n");
				if(split.length>0){
					entity.setAddress(split[0]);
				}
				entity.setAid(aid);
				entity.setLogo(one.getLogo());
				entity.setExhibitiontime(one.getExhibitiontime());
				entity.setTitle(one.getTitle());
				entity.setUid(uid);
				entity.setIdustry(one.getIdustryZero()+one.getIdustryTwice()+one.getIdustryThree());
				entity.setArticleLink(one.getArticleLink());
				rep.save(entity);
			} catch (Exception e) {
				return "关注失败！";
			}
			return "关注成功";
			
		}else{
			return "已关注";
		}
	}

	@Override
	public List<String> findSummitArea() {
		TermsBuilder aggBuilder = AggregationBuilders
				.terms("area").field("area");
		SearchQuery query = getSearchQueryBuilder()
				.addAggregation(aggBuilder).withSort(SortBuilders.fieldSort("publishDate").order(SortOrder.DESC))
				.build();
		List<String> list = template.query(query, res -> {
			List<String> sList = new ArrayList<>();
			Terms t = res.getAggregations().get("area");
			for (Bucket bucket : t.getBuckets()) {
				if(StringUtil.isEmpty(bucket.getKeyAsString())){
					continue;
				}
				String area = bucket.getKeyAsString().replaceAll("市", "").replaceAll("自治区", "").replaceAll("省", "");
				sList.add(area);
			}
			return sList;
		});
		return list;
	}
	
}
