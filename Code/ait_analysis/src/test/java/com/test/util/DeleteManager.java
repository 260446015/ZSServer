package com.test.util;

import org.elasticsearch.action.delete.DeleteResponse;

import com.huishu.aitanalysis.common.DBConstant.EsConfig;

public class DeleteManager {
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public static int deleteSOBanggByKey(String id) {
		DeleteResponse result = ESTools.client.prepareDelete().setRefresh(true).setIndex(EsConfig.INDEX)
		.setType(EsConfig.TYPE)
	    .setId(id)//设置ID, 
	    .setRefresh(true)//刷新
	    .execute().actionGet();
	   //是否查找并删除	
	   boolean isfound = result.isFound();
	   return isfound?1:0;
	}
}
