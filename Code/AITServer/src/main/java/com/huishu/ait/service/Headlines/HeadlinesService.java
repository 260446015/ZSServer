package com.huishu.ait.service.Headlines;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.echart.Option;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;

/**
 * @author hhy
 * @date 2017年7月27日
 * @Parem
 * @return 
 * 
 */
public interface HeadlinesService {
	/** 产业头条--关键词云 */
	JSONArray  getWordCloud(HeadlinesDTO headlinesDTO); 
	
	/** 产业头条--媒体云图 */
	Option  getCarClondChartList(HeadlinesDTO headlinesDTO);
}
