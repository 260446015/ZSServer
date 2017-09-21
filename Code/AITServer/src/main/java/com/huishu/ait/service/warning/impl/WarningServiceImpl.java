package com.huishu.ait.service.warning.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.entity.ChangeInfo;
import com.huishu.ait.entity.Company;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.entity.dto.InformationSearchDTO;
import com.huishu.ait.es.entity.ExternalFlow;
import com.huishu.ait.es.entity.WarningInformation;
import com.huishu.ait.es.repository.ExternalFlowRepository;
import com.huishu.ait.es.repository.warning.WarningInformationRepository;
import com.huishu.ait.repository.company.CompanyRepository;
import com.huishu.ait.repository.skyeye.ChangeRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.warning.WarningService;

@Service
public class WarningServiceImpl extends AbstractService implements WarningService {

	private static Logger LOGGER = LoggerFactory.getLogger(WarningServiceImpl.class);

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private WarningInformationRepository warningInformationRepository;
	@Autowired
	private ExternalFlowRepository externalFlowRepository;
	@Autowired
	private ChangeRepository changeRepository;

	/*
	 * @Override public JSONArray getBusinessOutflowList(AreaSearchDTO
	 * searchModel) { // 组装查询条件 Map<String, String> map = new HashMap<String,
	 * String>(); map.put("dimension", "疑似外流"); map.put("park",
	 * searchModel.getPark()); // 组装排序字段,按时间和点击量降序排列 String[] order = {
	 * "publishDate", "hitCount" }; List<String> orderList =
	 * Arrays.asList(order); // 组装返回数据字段 String[] data = {
	 * "publishDate","business", "title", "content","warnTime","park"};
	 * List<String> dataList = Arrays.asList(data); JSONArray array =
	 * getEsData(searchModel, map, null,orderList, dataList,true); return array;
	 * }
	 */

	@Override
	public Page<ExternalFlow> getBusinessOutflowList(AreaSearchDTO searchModel) {
		// 组装查询条件
		BoolQueryBuilder bq = QueryBuilders.boolQuery();
		bq.must(QueryBuilders.termQuery("dimension", "疑似外流"))
				.must(QueryBuilders.termQuery("park", searchModel.getPark()));
		Sort sort = new Sort(Direction.DESC, "publishDate");
		PageRequest pageable = new PageRequest(searchModel.getPageNumber() - 1, searchModel.getPageSize(), sort);
		Page<ExternalFlow> page = externalFlowRepository.search(bq, pageable);
		return page;
	}

	@Override
	public Page<ChangeInfo> getInformationChangeList(InformationSearchDTO searchModel) {
		List<Company> list = companyRepository.findByPark(searchModel.getPark());
		if (list == null || list.size() == 0) {
			return null;
		}
		Sort sort = new Sort(Direction.DESC, "createTime");
		PageRequest pageRequest = new PageRequest(searchModel.getPageNumber() - 1, searchModel.getPageSize(), sort);
		Page<ChangeInfo> findAll = null;
		try {
			findAll = changeRepository.findByPark(searchModel.getPark(), pageRequest);
		} catch (Exception e) {
			LOGGER.error("查询信息变更失败");
		}
		return findAll;
	}

	@Override
	public ChangeInfo getInformationChangeById(String id) {
		return changeRepository.findOne(Integer.valueOf(id));
	}

	@Override
	public List<ChangeInfo> getChangeInfo(String park) {
		List<ChangeInfo> list = changeRepository.findByParkAndDr(park, 0);
		return list;

	}

	@Override
	public boolean deleteWarning(String id) {
		try {
			ChangeInfo change = changeRepository.findOne(Integer.valueOf(id));
			ExternalFlow ex = externalFlowRepository.findOne(id);
			if(null != change){
				change.setDr(1);
				changeRepository.save(change);
			}else{
				ex.setHasWarn("false");
				externalFlowRepository.save(ex);
			}
			return true;
		} catch (Exception e) {
			LOGGER.error("删除预警数量失败");
		}
		return false;

	}

	@Override
	public List<ExternalFlow> getExternalFlow(String park, String hasWarn) {
		List<ExternalFlow> list = externalFlowRepository.findByParkAndHasWarn(park,hasWarn);
		return list;
		
	}

}
