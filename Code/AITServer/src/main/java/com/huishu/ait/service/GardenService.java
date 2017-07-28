package com.huishu.ait.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface GardenService {
	List<JSONObject> getGardenPolicyList(String park,String pageNumber,String pageSize);
}
