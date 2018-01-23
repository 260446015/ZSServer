/*package com.huishu.aitanalysis.repository;

import java.util.List;

import org.apache.log4j.Logger;

import com.huishu.aitanalysis.es.entity.Index;

import net.sf.json.JSONObject;

*//**
 * @author hhy
 * @date 2017年8月30日
 * @Parem
 * @return 
 * 
 *//*
public interface IIndexDao {
	
	void insert(String info);

	*//**
	 * @param documentId
	 * @param log
	 * @return
	 *//*
	Index queryByID(String documentId, Logger log);

	*//**
	 * @param index
	 * @param log
	 *//*
	void index(Index index, Logger log);

	*//**
	 * @param indexs
	 * @param log
	 *//*
	void bulkIndex(List<Index> indexs, Logger log);

	*//**
	 * @param documentId
	 * @param source
	 * @param log
	 * @return
	 *//*
	boolean modifyByID(String documentId, JSONObject source, Logger log);
}
*/