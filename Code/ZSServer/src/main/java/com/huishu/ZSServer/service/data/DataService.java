package com.huishu.ZSServer.service.data;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.Institutional;

/**
 * @author hhy
 * @date 2017年11月15日
 * @Parem
 * @return 
 * 
 */
public interface DataService {

	/**
	 * @param value
	 * @return
	 * 转换机构库数据
	 */
	Institutional transformData1(String value);

	/**
	 * @param info
	 * @return
	 */
	boolean addData1(Institutional info);
	/**
	 * 
	 */
	JSONObject rederData(String value);
	boolean  saveData(JSONObject obj);
	//生物产业数据读取
	JSONObject redData(String value);

	/**
	 * 生物产业数据保存
	 * @param obj
	 * @return
	 */
	boolean updateData(JSONObject obj);
	
	
}
