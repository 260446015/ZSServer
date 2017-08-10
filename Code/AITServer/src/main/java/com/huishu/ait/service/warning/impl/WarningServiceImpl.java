package com.huishu.ait.service.warning.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.DBConstant;
import com.huishu.ait.common.util.Constans;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.es.entity.GardenInformation;
import com.huishu.ait.es.entity.WarningInformation;
import com.huishu.ait.es.repository.warning.WarningRepository;
import com.huishu.ait.service.SkyEyeAbstractService;
import com.huishu.ait.service.warning.WarningService;

@Service
public class WarningServiceImpl extends SkyEyeAbstractService implements WarningService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WarningServiceImpl.class);

	@Autowired
	private WarningRepository warningRepository;

	@Override
	public JSONArray getBusinessOutflowList(AreaSearchDTO searchModel) {
		// 组装查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("park", searchModel.getPark());
		map.put("emotion", DBConstant.Emotion.NEGATIVE);
		// 组装排序字段,按时间和点击量降序排列
		String[] order = { "publishDateTime", "hitCount" };
		List<String> orderList = Arrays.asList(order);
		// 组装返回数据字段
		String[] data = { "business", "title", "content" };
		List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, orderList, dataList);
		return array;
	}

	@Override
	public GardenInformation getBusinessOutflowById(String id) {
		return warningRepository.findOne(id);
	}

	@Override
	public JSONArray getInformationChangeList(AreaSearchDTO searchModel, HttpServletRequest request,
			HttpServletResponse response) {
		StringBuffer buffer = new StringBuffer();
		// 根据park获取该园区所有企业
		ArrayList<String> list = new ArrayList<String>();
		if (list == null || list.size() == 0) {
			return null;
		}
		buffer.append("[");
		for (String companyName : list) {
			List<String> specList = Arrays.asList(Constans.SEARCH);
			Map<String, String> params = new HashMap<String, String>();
			params.put("word", companyName);
			JSONArray array = sendHttpsRequest(specList, params, request, response);
			JSONObject json = (JSONObject) array.get(0);
			if (json == null) {
				continue;
			}
			List<Object> items = (List<Object>) json.get("data");
			if (items != null && items.size() != 0) {
				String string = JSONObject.toJSON(items.get(0)).toString();
				JSONObject parse = JSONObject.parseObject(string);
				String id = parse.get("id").toString();
				List<String> specList2 = Arrays.asList(Constans.CHANGEINFO);
				Map<String, String> params2 = new HashMap<String, String>();
				params2.put("id", id);
				JSONArray array2 = sendHttpsRequest(specList2, params2, request, response);
				// 将所有的JSONArray拼接成一个JSONString
				try {
					JSONObject obj1 = (JSONObject) array2.get(0);
					JSONObject datas = (JSONObject) obj1.get("data");
					List<Object> result = (List<Object>) datas.get("result");
					if (obj1 == null || datas == null || result == null || result.size() == 0) {
						continue;
					}
					for (int i = 0; i < result.size(); i++) {
						JSONObject item = (JSONObject) result.get(i);
						if (item != null) {
							if (i == result.size() - 1)
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
			String valA = (String) a.get("changeTime");
			String valB = (String) b.get("changeTime");
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
		return null;
	}

}
