package com.huishu.aitanalysis.controller.abs;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import com.huishu.aitanalysis.common.Component;
import com.huishu.aitanalysis.util.EmptyUtils;

import net.sf.json.JSONObject;

public abstract class AbstractController {
	protected static final Logger log = Logger.getRootLogger();
	
	/**
	 * 返回结果
	 * @param success 是否运行成功
	 * @param msg 说明
	 * @param result 结果
	 * @param callback js回调方法
	 * @param response 当前响应
	 */
	protected void sendResponse(Map<String, Object> returnMap, HttpServletResponse response){
		response.setContentType("application/json;charset=UTF-8");
		String returnStr = JSONObject.fromObject(returnMap).toString();
		try {
			response.getWriter().print(returnStr);
		} catch (IOException e) {
			log.error("send response error -> " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回结果
	 * @param success 是否运行成功
	 * @param msg 说明
	 * @param result 结果
	 * @param callback js回调方法
	 * @param response 当前响应
	 */
	protected void sendResponse(Boolean success, String msg, String code,Object result, String callback, HttpServletResponse response){
		response.setContentType("application/json;charset=UTF-8");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("msg", msg);
		returnMap.put("code", code);
		String returnStr = JSONObject.fromObject(returnMap).toString();
		try {
			response.getWriter().print(returnStr);
		} catch (IOException e) {
			log.error("send response error -> " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回结果
	 * @param success 是否运行成功
	 * @param msg 说明
	 * @param result 结果
	 * @param callback js回调方法
	 * @param response 当前响应
	 */
	protected void sendResponse(Boolean success, String msg, Object result, String callback, HttpServletResponse response){
		response.setContentType("application/json;charset=UTF-8");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("success", success);
		returnMap.put("msg", msg);
		returnMap.put("result", result);
		
		String returnStr = JSONObject.fromObject(returnMap).toString();
		try {
			if(EmptyUtils.isEmpty(callback)){
				response.getWriter().print(returnStr);
			}else{
				response.getWriter().print(callback + "(" + returnStr + ")");
			}
		} catch (IOException e) {
			log.error("send response error -> " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回结果
	 * @param success 是否运行成功
	 * @param msg 说明
	 * @param result 结果
	 * @param response 当前响应
	 */
	protected void sendResponse(Boolean success, String msg, Object result, HttpServletResponse response){
		sendResponse(success, msg, result, null, response);
	}
	
	/**
	 * 返回结果
	 * @param component 运行的控件
	 * @param result 结果
	 * @param response 当前响应
	 */
	protected void sendResponse(Component component, Object result, HttpServletResponse response){
		sendResponse(component, result, null, response);
	}
	
	/**
	 * 返回结果
	 * @param component 运行的控件
	 * @param result 结果
	 * @param callback js回调方法
	 * @param response 当前响应
	 */
	protected void sendResponse(Component component, Object result, String callback, HttpServletResponse response){
		sendResponse(component.success(), component.getMsg(), result, callback, response);
	}
	
	/**
	 * 解析uri编码
	 * @param str 需要编码的字符串
	 * @return 如果编码错误，则返回null
	 */
	protected String decode(String str){
		String target = null;
		try {
			target = URLDecoder.decode(str, "utf-8");
		} catch (Exception e) {
			log.info("param decode error");
		}
		return target;
	}
}
