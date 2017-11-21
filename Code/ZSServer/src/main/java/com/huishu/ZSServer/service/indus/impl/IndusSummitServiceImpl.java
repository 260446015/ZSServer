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
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ZSServer.entity.UserSummitInfo;
import com.huishu.ZSServer.es.entity.SummitInfo;
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

}
