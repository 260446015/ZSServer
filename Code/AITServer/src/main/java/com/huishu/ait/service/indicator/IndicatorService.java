package com.huishu.ait.service.indicator;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.TreeNode.TreeNode;
import com.huishu.ait.es.entity.dto.IndicatorDTO;

/**
 * @author hhy
 * @date 2017年9月6日
 * @Parem
 * @return 
 * 
 */
public interface IndicatorService {

	/**获取园区内分类信息列表*/
	List<TreeNode> getIndicatorList(IndicatorDTO dto);

	/**根据分类信息，查询企业信息*/
	JSONArray getBusinessByIndicator(IndicatorDTO dto);
	
}
