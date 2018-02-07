package com.huishu.ManageServer.service.industry.map.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ManageServer.entity.dbFirst.IndustryRank;
import com.huishu.ManageServer.es.entity.SummitInfo;
import com.huishu.ManageServer.repository.first.IndusRankRepository;
import com.huishu.ManageServer.repository.first.InstitutionalRepostitory;
import com.huishu.ManageServer.service.AbstractService;
import com.huishu.ManageServer.service.industry.map.IndustryMapService;

/**
 * @author hhy
 * @date 2018年1月15日
 * @Parem
 * @return 
 * 产业地图相关实现类
 */
@Service
public class InsutryMapServiceImpl extends AbstractService implements IndustryMapService {
	private static final Logger LOGGER = Logger.getLogger(InsutryMapServiceImpl.class);
	
	@Autowired
	private IndusRankRepository rankrep;
	
	@Autowired
	protected ElasticsearchTemplate template;
	
	@Autowired
	private InstitutionalRepostitory instrep;
	/**
	 * 根据产业获取到产业地图相关数据
	 */
	@Override
	public JSONObject findMapInfoByIndustry(String industry) {
		JSONObject obj = new JSONObject();
		//产业热度数据
		List<IndustryRank> list1 = null;
		List<Object[]> list2 = null;
		try {
			list1 = rankrep.findByIndustry(industry);
			obj.put("rank", list1);
		} catch (Exception e) {
			LOGGER.debug("获取产业热度排行数据时报错："+e);
			obj.put("rank", list1);
		}
		//重点实验室数据--获取当前产业下实验室的数量
		try {
			list2 = instrep.findByIndustry(industry);
			obj.put("count", list2);
		} catch (Exception e) {
			LOGGER.debug("获取重点实验室数据时报错："+e);
			obj.put("rank", list2);
		}
		//产业峰会数据
		getSummitInfoByIndustry(obj,industry);
		return obj;
	}
	
	/**
	 * @param obj
	 * @param industry
	 * 获取峰会的数据
	 */
	private void getSummitInfoByIndustry(JSONObject obj, String industry) {
		JSONObject obj1 = new JSONObject();
		obj1.put("dimension", "高峰论坛");
		if(industry.equals("生物技术")){
			obj1.put("industry", "生物产业");
		}else{
			obj1.put("industryLabel", industry);
		}
		BoolQueryBuilder bq = getBoolBuilder(obj1);
		SearchQuery searchQuery = getSearchQueryBuilder().withQuery(bq)
				.withSort(SortBuilders.fieldSort("publishTime").order(SortOrder.DESC)).build();
		List<SummitInfo> info =	template.query(searchQuery, res->{
			List<SummitInfo> list= new ArrayList<SummitInfo>();
			SearchHits hits = res.getHits();
			for(SearchHit hit : hits){
				Map<String, Object> map = hit.getSource();
				SummitInfo suinfo = new SummitInfo();
				suinfo.setArticleLink(map.get("articleLink").toString());
				suinfo.setId(hit.getId());
				suinfo.setPublishTime(map.get("publishTime").toString());
				if(StringUtil.isEmpty(map.get("exhibitiontime").toString())){
					suinfo.setExhibitiontime("");
				}else{
					suinfo.setExhibitiontime(map.get("exhibitiontime").toString());
				}	
				suinfo.setTitle(map.get("title").toString());
				suinfo.setIdustryThree(map.get("idustryThree").toString());
				suinfo.setIdustryZero(map.get("idustryZero").toString());
				suinfo.setIdustryTwice(map.get("idustryTwice").toString());
				list.add(suinfo);
			}
			return list;
		});
		obj.put("summit", info);
	}

	/**
	 * 产业热度数据更新或保存
	 */
	@Override
	public boolean saveOrUpdateRank(IndustryRank rank) {
	try {
		IndustryRank save = rankrep.save(rank);
		if(save!= null){
			return true;
		}else{
			return false;
		}
	} catch (Exception e) {
		LOGGER.debug("进行产业热度数据更新时出错",e);
		return false;
		}
	}

	/**
	 * 根据id删除产业热度数据
	 */
	@Override
	public Boolean deleteRankInfoById(String id) {
		try {
			rankrep.delete(Long.valueOf(id));
			return true;
		} catch (Exception e) {
			LOGGER.debug("删除产业热度数据时出错",e);
			return false;
		}
		
	}

}
