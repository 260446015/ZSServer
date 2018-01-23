package com.huishu.aitanalysis.util;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;

import com.huishu.aitanalysis.common.DBConstant;

/**
 * ES相关操作
 * @author Administrator
 *
 */
public class ESDataUtil {

	/**
	 * 获取默认的ES 搜索请求构造器
	 * 
	 * @param client
	 * @return
	 */
	public static SearchRequestBuilder getSearchRequestBuilder(Client client) {
		return client.prepareSearch(DBConstant.EsConfig.INDEX2).setTypes(DBConstant.EsConfig.TYPE1);
	}

}
