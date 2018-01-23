package com.huishu.aitanalysis.service.index;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.huishu.aitanalysis.es.entity.Index;
import com.huishu.aitanalysis.es.entity.Index2;
import com.huishu.aitanalysis.es.entity.Index3;


public interface IIndexService {
	
	/**
	 * 新增一个索引
	 * @param index
	 * @return
	 */
	public void index(Index index, Logger log);
	
	/**
	 * 新增一个索引
	 * @param index
	 * @return
	 */
	public void indexInfo(Index2 index, Logger log);
	
	/**
	 * 新增一个索引
	 * @param index
	 * @return
	 */
	public void bulkIndex(List<Index> indexs, Logger log);
	
	/**
	 * 修改指定文档列的值
	 * 对于平台, 做研判时使用
	 * @param documentId
	 * @param obj
	 * @return
	 */
//	public boolean modifyByID(String documentId, JSONObject obj, Logger log);
	
	
	/**
	 * 根据文档ID查询文档
	 * @param documentId
	 * @return
	 */
	public Index queryByID(String documentId, Logger log);
	
	public boolean filterURL(String articleLink);

	public boolean filterTitle(String title, String articleLink);

	public boolean filterByTitleAndLink(String title, String articleLink);

	public void savaIndex3(Index3 index3, Logger log);

	public boolean filterByDimensionAndJSON(String dimension2, com.alibaba.fastjson.JSONObject jsonObject);
}
