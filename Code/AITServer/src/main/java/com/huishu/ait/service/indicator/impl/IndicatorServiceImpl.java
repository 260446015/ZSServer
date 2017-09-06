package com.huishu.ait.service.indicator.impl;


import org.elasticsearch.index.query.BoolQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.es.entity.dto.IndicatorDTO;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.indicator.IndicatorService;

/**
 * @author hhy
 * @date 2017年9月6日
 * @Parem
 * @return 
 * 
 */
@Service
public class IndicatorServiceImpl extends AbstractService implements IndicatorService {

	
	private static Logger logger = LoggerFactory.getLogger(IndicatorServiceImpl.class);


	/**
	 * 获取产业分类信息
	 */
	public JSONArray getIndicatorList(IndicatorDTO dto) {
		
		BoolQueryBuilder bq = getIndicatorContentBuilder(dto);
		JSONArray  json =	getIndicatorInfo(bq);
		return json;
	}
	
	/**
	 * 根据分类指标查询公司信息
	 */
	@Override
	public JSONArray getBusinessByIndicator(IndicatorDTO dto) {

		BoolQueryBuilder bq = getIndicatorContentBuilder(dto);
		JSONArray json = 	getBusinessByIndicator(bq);
		return json;
	}
	

}
