package com.huishu.ait.service.gardenSupervise.impl;

import java.util.ArrayList;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.util.ESUtils;
import com.huishu.ait.entity.Company;
import com.huishu.ait.entity.CompanyGroup;
import com.huishu.ait.entity.CompanyGroupMiddle;
import com.huishu.ait.entity.dto.CompanyDTO;
import com.huishu.ait.repository.company.CompanyRepository;
import com.huishu.ait.repository.companyGroup.CompanyGroupRepository;
import com.huishu.ait.repository.company_group_middle.CompanyGroupMiddleRepository;
import com.huishu.ait.service.gardenSupervise.GardenSuperviseService;

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
	@Autowired
	private CompanyGroupMiddleRepository middleRepository;
	@Autowired
	private CompanyRepository companyRepository;

	/*
	 * 方法名：getGardenInfo 描述：获取园区的信息
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
					if (null != map && map.size() > 0) {
						map.put("_id", hit.getId());
						json.put("id", map.get("_id"));
						json.put("park", map.get("park"));
						json.put("area", map.get("area"));
						json.put("position", map.get("position"));
						json.put("parkType", map.get("parkType"));
						// 占地面积
						json.put("floorArea", map.get("floorArea"));
						// 建筑面积
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
	 * 方法名：getCompanyFromGarden 描述：获取当前园区内所有企业列表的信息
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
					if (null != map && map.size() > 0) {
						map.put("_id", hit.getId());
						json.put("id", map.get("_id"));
						json.put("park", map.get("park"));// 所属园区
						json.put("business", map.get("business"));
						json.put("vector", map.get("vector"));
						json.put("businessLegal", map.get("businessLegal"));
						json.put("position", map.get("position"));// 详细位置
						json.put("area", map.get("area"));// 区域地址
						json.put("registerCapital", map.get("registerCapital"));// 获取注册资本
						json.put("registerDate", map.get("registerDate"));// 获取注册时间
						json.put("boss", map.get("boss"));// 新的企业法人
						json.put("logo", map.get("logo"));
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
	 * @return 添加企业分组
	 */
	public String addCompanyGroup(String groupName, Long userId) {
		String state = "success";
		try {
			CompanyGroup findGroupByName = companyGroupRepository.findGroupByName(groupName, userId);
			if (null != findGroupByName) {
				state = "分组已经存在";
				return state;
			}
			CompanyGroup companyGroup = new CompanyGroup();
			companyGroup.setGroupName(groupName);
			companyGroup.setUserId(userId);
			companyGroupRepository.save(companyGroup);
			return state;
		} catch (Exception e) {
			state = "failure";
			log.error("分组保存失败", e.getMessage());
			return state;
		}
	}

	/*
	 * 方法名：selectCompanyGroup 描述：查询企业分组
	 */
	@Override
	public List<CompanyGroup> selectCompanyGroup(Long userId) {
		try {
			List<CompanyGroup> list = companyGroupRepository.findByUserId(userId);
			return list;
		} catch (Exception e) {
			log.error("分组查询失败", e.getMessage());
			return null;
		}
	}

	/*
	 * 方法名：getCompanyFromGardenForPage 描述：查询当前园区内所有企业信息+搜索+分页
	 */
	public JSONArray getCompanyFromGardenForPage(CompanyDTO dto) {
		try {
			JSONArray jsonArray = new JSONArray();
			String park = dto.getPark();
			String industry = dto.getIndustry();
			String keyWord = dto.getKeyWord();
			Integer pageNumber = dto.getPageNumber();
			Integer pageSize = dto.getPageSize();
			// Long regCapital = dto.getRegCapital();
			// int from = (pageNumber - 1) * pageSize;
			SearchRequestBuilder srb = ESUtils.getSearchRequestBuilder(client);
			BoolQueryBuilder bq = new BoolQueryBuilder();
			if (StringUtils.isNotBlank(park)) {
				bq.must(QueryBuilders.termQuery("park", park));
			}
			if (StringUtils.isNotBlank(industry)) {
				bq.must(QueryBuilders.termQuery("industry", industry));
			}
			// if (null !=regCapital) {
			// bq.must(QueryBuilders.termQuery("regCapital", regCapital));
			// }
			if (StringUtils.isNotBlank(keyWord)) {
				bq.must(QueryBuilders.termQuery("keyWord", keyWord));
			}
			SearchResponse actionGet = srb.setQuery(bq).setSize(200).execute().actionGet();
			if (null != actionGet && null != actionGet.getHits()) {
				SearchHits hits = actionGet.getHits();
				for (SearchHit hit : hits) {
					JSONObject json = new JSONObject();
					Map<String, Object> map = hit.getSource();
					if (null != map && map.size() > 0) {
						json.put("business", map.get("business"));
						json.put("boss", map.get("boss"));
						json.put("businessType", map.get("businessType"));// 所属园区
						json.put("park", map.get("park"));// 商标图片链接
						json.put("engageState", map.get("engageState"));
						json.put("registerCapital", map.get("registerCapital"));
						json.put("registerDate", map.get("registerDate"));// 公司领导
						json.put("address", map.get("address"));// 详细位置
						json.put("area", map.get("area"));// 区域地址
						json.put("logo", map.get("logo"));// 注册资金
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

	/*
	 * 方法名：getCompanyFromGardenForPage 描述：查询当前园区内所有企业信息+搜索+分页
	 * 原本是从es库中取，现改为从mysql
	 */
	public JSONArray getCompanyFromGardenForPage2(@RequestBody CompanyDTO dto) {
		try {
			JSONArray jsonArray = new JSONArray();
			String park = dto.getPark();
			Integer pageNumber = dto.getPageNumber();
			Integer pageSize = dto.getPageSize();
			String industry = dto.getIndustry().equals("全部") ? "%%" : dto.getIndustry();// 产业描述
			double start = dto.getStart();
			double end = dto.getEnd();
			PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize);
			Page<Company> page = null;
			if (dto.getGroupname().equals("全部")) {
				page = companyRepository.findByIndustryLikeAndParkAndRegisterCapitalBetween(industry, park, start, end,
						pageRequest);
			} else {
				CompanyGroup cg = companyGroupRepository.findGroupByName(dto.getGroupname(), dto.getUserId());
				if (cg == null) {
					return null;
				}
				List<CompanyGroupMiddle> ms = middleRepository.findByGroupId(cg.getGroupid());
				if (ms == null) {
					return null;
				}
				List<Long> companyIds = new ArrayList<>();
				for (CompanyGroupMiddle m : ms) {
					companyIds.add(m.getCompanyId());
				}
				page = companyRepository.findByIndustryLikeAndParkAndCidInAndRegisterCapitalBetween(industry, park,
						companyIds, start, end, pageRequest);
			}
			jsonArray.add(page);
			return jsonArray;
		} catch (Exception e) {
			log.error("获取园区内企业信息失败", e.getMessage());
			return null;
		}

	}

	@Override
	public boolean dropCompanyGroup(String[] groupNames, Long userId) {
		boolean flag = false;
		try {
			for (String groupName : groupNames) {
				CompanyGroup findGroupByName = companyGroupRepository.findGroupByName(groupName, userId);
				if (findGroupByName != null) {
					companyGroupRepository.delete(findGroupByName);
					middleRepository.deleteByGroupId(findGroupByName.getGroupid());
				}
			}
			flag = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean saveCompanyByGroupId(CompanyGroupMiddle middle, Long userId) {
		boolean flag = false;
		try {
			CompanyGroup cg = companyGroupRepository.findByGroupNameAndUserId(middle.getGroupname(), userId);
			if (cg == null) {
				return flag;
			}
			middle.setGroupId(cg.getGroupid());
			middle.setGroupname(cg.getGroupName());
			middle.setUserId(userId);
			middleRepository.save(middle);
			flag = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return flag;

	}

	@Override
	public boolean deleteCompanyInGroup(CompanyGroupMiddle middle) {
		boolean flag = false;
		try {
			middleRepository.deleteByCompanyId(middle.getCompanyId());
			flag = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return flag;

	}

}
