package com.huishu.ait.service.gardenSupervise.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.entity.CompanyGroup;
import com.huishu.ait.entity.dto.CompanyDTO;
import com.huishu.ait.repository.companyGroup.CompanyGroupRepository;
import com.huishu.ait.service.gardenSupervise.GardenSuperviseService;

import scala.annotation.meta.setter;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：园区监管的实现类
 */
@Service
public class GardenSuperviseImpl implements GardenSuperviseService {
	
	private static Logger log = LoggerFactory.getLogger(GardenSuperviseImpl.class);
	
	@Autowired
	private Client client;
	@Autowired
	private CompanyGroupRepository companyGroupRepository;
	
	/* 
	 * 方法名：getGardenInfo
	 * 描述：获取园区的信息
	 */
	public JSONObject getGardenInfo(String park) {
		try {
			JSONObject json = new JSONObject();
			SearchRequestBuilder srb = ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			if (StringUtils.isNotBlank(park)) {
				bq.must(QueryBuilders.termQuery("park", park));
			}
			SearchResponse actionGet = srb.setQuery(bq).execute().actionGet();
			if (null != actionGet && null != actionGet.getHits()) {
				SearchHits hits = actionGet.getHits();
				for (SearchHit hit : hits) {
					Map<String, Object> map = hit.getSource();
					if (null != map && map.size()> 0 ) {
						map.put("_id", hit.getId());
						json.put("id", map.get("_id"));
						json.put("park", map.get("park"));
						json.put("area", map.get("area"));
						json.put("position", map.get("position"));
						json.put("parkType", map.get("parkType"));
						//占地面积
						json.put("floorArea", map.get("floorArea"));
						//建筑面积
						json.put("tructureArea", map.get("tructureArea"));
					}
				}
			}
			return json;
		} catch (Exception e) {
			log.error("获取园区信息失败", e.getMessage());
			return null;
		}
	}
	/* 
	 * 方法名：getCompanyFromGarden
	 * 描述：获取当前园区内所有企业列表的信息
	 */
	public JSONArray getCompanyFromGarden(String park) {
		try {
			JSONArray jsonArray = new JSONArray();
			SearchRequestBuilder srb = ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			if (StringUtils.isNotBlank(park)) {
				bq.must(QueryBuilders.termQuery("park", park));
			}
			SearchResponse actionGet = srb.setQuery(bq).execute().actionGet();
			if (null != actionGet && null != actionGet.getHits()) {
				SearchHits hits = actionGet.getHits();
				for (SearchHit hit : hits) {
					JSONObject json = new JSONObject();
					Map<String, Object> map = hit.getSource();
					if (null != map && map.size()>0 ) {
						map.put("_id", hit.getId());
						json.put("id", map.get("_id"));
						json.put("park", map.get("park"));//所属园区
						json.put("business", map.get("business"));
						json.put("vector", map.get("vector"));
						json.put("businessLegal", map.get("businessLegal"));
						json.put("position", map.get("position"));//详细位置
						json.put("area", map.get("area"));//区域地址
						jsonArray.add(json);
					}
				}
			}
			return jsonArray;
		} catch (Exception e) {
			log.error("获取园区内企业信息失败", e.getMessage());
			return null;
		}
		
	}
	/**
	 * @param group
	 * @return
	 * 添加企业分组
	 */
	public String addCompanyGroup(String groupName){
		String state = "success";
		try {
			CompanyGroup findGroupByName = companyGroupRepository.findGroupByName(groupName);
			if (null != findGroupByName) {
				state="分组已经存在";
				return state;
			}
			CompanyGroup companyGroup = new CompanyGroup();
			companyGroup.setGroupName(groupName);
			companyGroupRepository.save(companyGroup);
			return state;
		} catch (Exception e) {
			state = "failure";
			log.error("分组保存失败", e.getMessage());
			return state;
		}
	}
	/* 
	 * 方法名：selectCompanyGroup
	 * 描述：查询企业分组
	 */
	@Override
	public List<CompanyGroup> selectCompanyGroup() {
		try {
			List<CompanyGroup> list = (List<CompanyGroup>) companyGroupRepository.findAll();
			return list;
		} catch (Exception e) {
			log.error("分组查询失败", e.getMessage());
			return null;
		}
	}
	/* 
	 * 方法名：getCompanyFromGardenForPage
	 * 描述：查询当前园区内所有企业信息+搜索+分页
	 */
	public JSONArray getCompanyFromGardenForPage(CompanyDTO dto) {
		try {
			JSONArray jsonArray = new JSONArray();
			String park = dto.getPark();
			String industry = dto.getIndustry();
			String keyWord = dto.getKeyWord();
			Integer pageNumber = dto.getPageNumber();
			Integer pageSize = dto.getPageSize();
			Long regCapital = dto.getRegCapital();
			int from = (pageNumber-1)*pageSize;
			SearchRequestBuilder srb = ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			if (StringUtils.isNotBlank(park)) {
				bq.must(QueryBuilders.termQuery("park", park));
			}
			if (StringUtils.isNotBlank(industry)) {
				bq.must(QueryBuilders.termQuery("industry", industry));
			}
			if (null !=regCapital) {
				bq.must(QueryBuilders.termQuery("regCapital", regCapital));
			}
			if (StringUtils.isNotBlank(keyWord)) {
				bq.must(QueryBuilders.termQuery("keyWord", keyWord));
			}
			SearchResponse actionGet = srb.setQuery(bq).setFrom(from).setSize(pageSize).execute().actionGet();
			if (null != actionGet && null != actionGet.getHits()) {
				SearchHits hits = actionGet.getHits();
				for (SearchHit hit : hits) {
					JSONObject json = new JSONObject();
					Map<String, Object> map = hit.getSource();
					if (null != map && map.size()>0 ) {
						map.put("_id", hit.getId());
						json.put("id", map.get("_id"));
						json.put("total", hits.getTotalHits());
						json.put("park", map.get("park"));//所属园区
						json.put("tmPic", map.get("tmPic"));//商标图片链接
						json.put("vector", map.get("vector"));
						json.put("business", map.get("business"));
						json.put("businessLegal", map.get("businessLegal"));//公司领导
						json.put("position", map.get("position"));//详细位置
						json.put("area", map.get("area"));//区域地址
						json.put("regCapital", map.get("regCapital"));//注册资金
						json.put("legalPersonName", map.get("legalPersonName"));//法人
						json.put("regTime", map.get("regTime"));//注册时间
						json.put("percentileScore", map.get("percentileScore"));//评分
						jsonArray.add(json);
					}
				}
			}
			return jsonArray;
		} catch (Exception e) {
			log.error("获取园区内企业信息失败", e.getMessage());
			return null;
		}
		
	}
}
