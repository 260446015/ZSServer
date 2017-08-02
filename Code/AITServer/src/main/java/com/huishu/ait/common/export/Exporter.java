package com.huishu.ait.common.export;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Exporter {

	/**
	 * 导出文件
	 * @param response
	 * @param fileName
	 * @throws Exception
	 */
	void export(HttpServletRequest request, HttpServletResponse response,
			String fileName) throws Exception;
	
}
