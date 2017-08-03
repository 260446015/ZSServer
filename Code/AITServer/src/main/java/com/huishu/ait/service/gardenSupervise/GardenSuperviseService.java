package com.huishu.ait.service.gardenSupervise;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author yxq
 * @date 2017年8月3日
 * @功能描述：园区监管
 */
public interface GardenSuperviseService {
	
	//获取园区的信息
	 public JSONObject getGardenInfo(String park); 
	  
	//获取园区内所有企业的信息
	 public JSONArray getCompanyFromGarden(String park);
	 
	 //保存分组
	 public String addCompanyGroup(String groupName);
}
