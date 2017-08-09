package com.huishu.ait.service.skyeye;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

/**
 * @author yindawei 
 * @date 2017年8月8日下午2:31:15
 * @description 
 * @version 
 */
public interface SkyEyeService {

	/**
	 * 企业基本画像
	 * @param params  前台传递的参数
	 * @return
	 */
	JSONArray findBaseCompany(Map<String, String> params,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 企业业务画像
	 * @param params  前台传递的参数
	 * @return
	 */
	JSONArray findBaseCompanyBusiness(Map<String, String> params,HttpServletRequest request,HttpServletResponse response);
}
