package com.huishu.ZSServer.service.label;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2018年2月8日
 * @Parem
 * @return 
 * 
 */
public interface GetLabelService {

	/**
	 * @param obj
	 * @return
	 */
	List<String> getLabelInfo(JSONObject obj);

}
