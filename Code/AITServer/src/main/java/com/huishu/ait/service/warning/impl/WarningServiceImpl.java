package com.huishu.ait.service.warning.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.DBConstant;
import com.huishu.ait.entity.Company;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.es.entity.GardenInformation;
import com.huishu.ait.es.entity.WarningInformation;
import com.huishu.ait.es.repository.warning.WarningInformationRepository;
import com.huishu.ait.repository.company.CompanyRepository;
import com.huishu.ait.service.SkyEyeAbstractService;
import com.huishu.ait.service.warning.WarningService;

@Service
public class WarningServiceImpl extends SkyEyeAbstractService implements WarningService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(WarningServiceImpl.class);

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private WarningInformationRepository warningInformationRepository;

	@Override
	public JSONArray getBusinessOutflowList(AreaSearchDTO searchModel) {
		// 组装查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("dimension", "疑似外流");
		map.put("emotion", DBConstant.Emotion.NEGATIVE);
		// 组装not条件
		Map<String, String> mapnot = new HashMap<String, String>();
		mapnot.put("park", searchModel.getPark());
		// 组装排序字段,按时间和点击量降序排列
		String[] order = { "publishDate", "hitCount" };
		List<String> orderList = Arrays.asList(order);
		// 组装返回数据字段
		String[] data = { "publishDate","business", "title", "content","warnTime","park"};
		List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, mapnot,orderList, dataList,true);
		return array;
	}

	@Override
	public JSONArray getInformationChangeList(AreaSearchDTO searchModel) {
		StringBuffer buffer = new StringBuffer();
		List<Company> list = companyRepository.findByPark(searchModel.getPark());
		if (list == null || list.size() == 0) {
			return null;
		}
		buffer.append("[");
		for (Company company : list) {
			//组装查询条件
			Map<String,String> map = new HashMap<String,String>();
			map.put("business", company.getCompanyName());
			map.put("dimension", "企业信息变更");
			//组装排序字段,按时间和点击量降序排列
			 String[] order = {"publishDate","hitCount"};
			 List<String> orderList = Arrays.asList(order);
			//组装返回数据字段
			 String[] data = {"publishTime","business", "updateAttribute","businessType"};
			 List<String> dataList = Arrays.asList(data);
			JSONArray array = getEsData(searchModel, map, null,orderList, dataList,true);
			// 将所有的JSONArray拼接成一个JSONString
			try {
				for (int i = 0; i < array.size(); i++) {
					JSONObject item = (JSONObject) array.get(i);
					if (item != null) {
						if (i == array.size() - 1)
							buffer.append(item.toString());
						else
							buffer.append(item.toString()).append(",");
					}
					
				}
			} catch (Exception e) {
				LOGGER.error("拼接字符串出错", e);
				continue;
			}
		}
		buffer.append("]");
		JSONArray jsonArr = JSONArray.parseArray(buffer.toString());
		if (jsonArr == null || jsonArr.size() == 0) {
			return null;
		}
		JSONArray sortedJsonArray = new JSONArray();
		List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		for (int i = 0; i < jsonArr.size(); i++) {
			jsonValues.add(jsonArr.getJSONObject(i));
		}
		// 对数据进行排序，按照变更时间先后
		jsonValues.sort((JSONObject a, JSONObject b) -> {
			String valA = (String) a.get("publishTime");
			String valB = (String) b.get("publishTime");
			return valB.compareTo(valA);
		});
		for (int i = 0; i < jsonValues.size(); i++) {
			sortedJsonArray.add(jsonValues.get(i));
		}
		// 实现分页功能
		JSONArray resultJsonArray = new JSONArray();
		searchModel.setTotalSize(sortedJsonArray.size());
		Integer from = searchModel.getPageFrom();
		Integer pageSize = searchModel.getPageSize();
		for (int i=from;i<(sortedJsonArray.size()<from+pageSize? sortedJsonArray.size():from+pageSize); i++) {
			resultJsonArray.add(sortedJsonArray.get(i));
		}
		return resultJsonArray;
	}

	@Override
	public WarningInformation getInformationChangeById(String id) {
		return warningInformationRepository.findOne(id);
	}

}
