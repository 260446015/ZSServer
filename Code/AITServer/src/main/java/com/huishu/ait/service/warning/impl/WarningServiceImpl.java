package com.huishu.ait.service.warning.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.DBConstant;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.es.entity.GardenInformation;
import com.huishu.ait.es.entity.WarningInformation;
import com.huishu.ait.es.repository.warning.WarningRepository;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.warning.WarningService;

@Service
public class WarningServiceImpl extends AbstractService implements WarningService {
	
	@Autowired
	private WarningRepository warningRepository;
	
	@Override
	public JSONArray getBusinessOutflowList(AreaSearchDTO searchModel) {
		//组装查询条件
		Map<String,String> map = new HashMap<String,String>();
		map.put("park", searchModel.getPark());
		map.put("emotion", DBConstant.Emotion.NEGATIVE);
		//组装排序字段,按时间和点击量降序排列
		 String[] order = {"publishDateTime","hitCount"};
		 List<String> orderList = Arrays.asList(order);
		//组装返回数据字段
		 String[] data = {"business","title","content"};
		 List<String> dataList = Arrays.asList(data);
		JSONArray array = getEsData(searchModel, map, orderList, dataList);
		return array;
	}

	@Override
	public GardenInformation getBusinessOutflowById(String id) {
		return warningRepository.findOne(id);
	}

	@Override
	public JSONArray getInformationChangeList(AreaSearchDTO searchModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WarningInformation getInformationChangeById(String id) {
		// TODO Auto-generated method stub
		return null;
	}


}
