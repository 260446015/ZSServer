package com.huishu.aitanalysis.service.indus;

import com.alibaba.fastjson.JSONObject;
import com.huishu.aitanalysis.entity.IndustryInfo;

public interface IndustryInfoService {
	
	IndustryInfo   getIndusbyIndustryLabel(String industryLabel);
	
	//融资快讯，融资事件去重
	boolean removalInfo(JSONObject jsonObject);
}
