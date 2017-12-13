package com.huishu.ZSServer.common.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.huishu.ZSServer.common.AjaxResult;

/**
 * shiro不支持前后端完全分离
 * 
 * @author ersan
 *
 */
public class ShiroUtil {

	/**
	 * 统一返回前端json数据
	 * 
	 * @param response
	 * @param data
	 */
	public static void writeResponse(HttpServletResponse response, String message) {
		try {
			response.setContentType("application/json");
			OutputStream outputStream = response.getOutputStream();
			AjaxResult result = new AjaxResult().setMessage(message).setSuccess(false).setStatus(1);
			outputStream.write(JSON.toJSONString(result).getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}