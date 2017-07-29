package com.huishu.ait.service.Headlines.impl;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.echart.Option;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.service.AbstractService;
import com.huishu.ait.service.Headlines.HeadlinesService;

/**
 * @author hhy
 * @date 2017年7月27日
 * @Parem
 * @return 
 * 
 */
@Service
public class HeadlinesServiceImpl extends AbstractService implements HeadlinesService {
	private static Logger log = LoggerFactory.getLogger(HeadlinesServiceImpl.class);
	
	/**
	 *  产业头条--关键词云
	 */
	@Override
	public JSONArray getWordCloud(HeadlinesDTO headlinesDTO) {
		
		return null;
	}

	/**
	 *  产业头条---云图
	 */
	@Override
	public Option getCarClondChartList(HeadlinesDTO headlinesDTO) {
		try {
			BoolQueryBuilder bq = getIndustryContentBuilder(headlinesDTO);
			return getVectorDistribution(bq);
		} catch (Exception e) {
			log.error("查询失败：",e);
			return null;
		}
	}
	
}
