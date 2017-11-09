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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ZSServer.entity.UserSummitInfo;
import com.huishu.ZSServer.es.entity.SummitInfo;
import com.huishu.ZSServer.es.repository.SummitElasticsearch;
import com.huishu.ZSServer.repository.summit.SummitRepository;
import com.huishu.ZSServer.service.indus.IndusSummitService;

/**
 * @author hhy
 * @date 2017年11月2日
 * @Parem
 * @return 
 * 产业峰会service实现层
 */
@Service
public class IndusSummitServiceImpl implements IndusSummitService {
	private Logger LOGGER = LoggerFactory.getLogger(IndusSummitServiceImpl.class);
	
	@Autowired
	protected ElasticsearchTemplate template;
	
	@Autowired
	protected Client client;
	
	@Autowired
	private SummitElasticsearch search;
	
	@Autowired
	private SummitRepository rep;
	/**
	 * 根据 条件查看 产业峰会 列表
	 */
	@Override
	public Page<SummitInfo> getIndustryForPage(JSONObject obj){
		BoolQueryBuilder bq = getQueryBoolBuilder(obj);
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
	 *根据id查看峰会详情
	 */
	@Override
	public SummitInfo getSummitInfoById(String id) {
		LOGGER.info("查询峰会详情的id为：>>>>>>>"+id);
		return search.findOne(id);
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
	/**
	 * @param json
	 * @return
	 */
	private BoolQueryBuilder getQueryBoolBuilder(JSONObject json) {
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

	
	@Override
	public List<SummitInfo> findIndustrySummitList(JSONObject obj) {
		BoolQueryBuilder bq = getQueryBoolBuilder(obj);
		SearchQuery searchQuery = getSearchQueryBuilder().withQuery(bq).build();
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
				suinfo.setExhibitiontime(map.get("exhibitiontime").toString());
				suinfo.setPublishTime(map.get("publishTime").toString());
				suinfo.setIdustryZero(map.get("idustryZero").toString());
				suinfo.setIdustryTwice(map.get("idustryTwice").toString());
				list.add(suinfo);
			}
			return list;
		});
		return info;
	}

}
