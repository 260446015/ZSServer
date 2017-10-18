
package com.huishu.ait.controller.Industrymodule.headlines;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils.DateUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.HeadlinesArticleListDTO;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.es.entity.dto.HeadlinesKeyWordDTO;
import com.huishu.ait.es.entity.dto.HeadlinesVectorDTO;
import com.huishu.ait.service.headline.HeadlinesService;

/**
 * @author hhy
 * @date 2017年7月27日
 * @Parem
 * @return 产业头条
 */
@Controller
@RequestMapping("/apis/Headlines")
public class HeadlinesController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(HeadlinesController.class);
	@Autowired
	private HeadlinesService service;

	/**
	 * 产业头条--关键词云
	 * 
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getWordClond.json", method = RequestMethod.POST)
	public AjaxResult getWordClond(@RequestBody HeadlinesDTO headlinesDTO) {
		try {
			HeadlinesDTO dto = CheckDTO(headlinesDTO);
			if (dto.getPeriodDate() != null) {
				Map<String,Object> map = new HashMap<String,Object>();
				map = dateInit(dto.getPeriodDate(),map);
				dto.setStartDate((String) map.get("startTime"));
				dto.setEndDate((String)map.get("endTime"));
				dto.setPeriodDate(null);
			}
			boolean b = checkDTO(dto);
			if (b) {
				dto.setDimension("产业头条");
				JSONArray cloud = service.getWordCloud(dto);
				return success(cloud);
			}
			return error(MsgConstant.ILLEGAL_PARAM);
		} catch (Exception e) {
			logger.error("查询失败：", e);
			return error(MsgConstant.ILLEGAL_PARAM);

		}
	}

	/**
	 * @param headlinesDTO
	 * @return
	 */
	private HeadlinesDTO CheckDTO(HeadlinesDTO DTO) {
		HeadlinesDTO dto = new HeadlinesDTO();
		String[] msg = DTO.getMsg();
		dto.setIndustry(msg[0]);
		String industrtLabel = msg[1];
		if (industrtLabel.equals("不限")) {
			dto.setIndustryLabel("");
		} else {
			dto.setIndustryLabel(msg[1]);
		}
		dto.setPeriodDate(msg[2]);
		if (msg.length >= 4 && msg.length < 5) {
			dto.setVector(msg[3]);
		}
		return dto;
	}

	/**
	 * 产业头条--媒体云图
	 * 
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getClondChartList.json", method = RequestMethod.POST)
	public AjaxResult getCarClondChartList(@RequestBody HeadlinesDTO headlinesDTO) {
		try {
			HeadlinesDTO dto = CheckDTO(headlinesDTO);
			if (dto.getPeriodDate() != null) {
				Map<String,Object> map = new HashMap<String,Object>();
				map = dateInit(dto.getPeriodDate(),map);
				dto.setStartDate((String) map.get("startTime"));
				dto.setEndDate((String)map.get("endTime"));
				dto.setPeriodDate(null);
			}
			boolean b = checkDTO(dto);
			if (b) {
				dto.setDimension("产业头条");
				return success(service.getCarClondChartList(dto));
			}
			return error(MsgConstant.ILLEGAL_PARAM);
		} catch (Exception e) {
			logger.error("查询失败：", e);
			return error(MsgConstant.ILLEGAL_PARAM);

		}
	}

	/**
	 * 产业头条--今日头条
	 * 
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getArticleByVectorList.json", method = RequestMethod.POST)
	public AjaxResult getArticleByVectorList(@RequestBody HeadlinesVectorDTO headlinesDTO) {
		try {
			HeadlinesVectorDTO dto = new HeadlinesVectorDTO();
			String[] msg = headlinesDTO.getMsg();
			dto.setIndustry(msg[0]);
			String industrtLabel = msg[1];
			if (industrtLabel.equals("不限")) {
				dto.setIndustryLabel(null);
			} else {
				dto.setIndustryLabel(msg[1]);
			}
			dto.setPeriodDate(msg[2]);
			dto.setVector(msg[3]);
			if (dto.getPeriodDate() != null) {
				Map<String,Object> map = new HashMap<String,Object>();
				map = dateInit(dto.getPeriodDate(),map);
				dto.setStartDate((String) map.get("startTime"));
				dto.setEndDate((String)map.get("endTime"));
				dto.setPeriodDate(null);
			}
			boolean b = checkDTO(dto);
			if (b) {
				dto.setDimension("产业头条");
				Page<HeadlinesArticleListDTO> page = service.findArticleByVector(dto);
				return success(page);
			}
			return error(MsgConstant.ILLEGAL_PARAM);
		} catch (Exception e) {
			logger.error("查询头条信息失败：", e);
			return error("参数不合法");
		}
	}

	/**
	 * 产业头条--关键词查文章
	 * 
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getArticleByKeyWordList.json", method = RequestMethod.POST)
	public AjaxResult getArticleByKeyWordList(@RequestBody HeadlinesKeyWordDTO headlinesDTO) {
		try {
			HeadlinesKeyWordDTO dto = new HeadlinesKeyWordDTO();
			String[] msg = headlinesDTO.getMsg();
			dto.setIndustry(msg[0]);
			String industrtLabel = msg[1];

			if (industrtLabel.equals("不限")) {
				
				dto.setIndustryLabel(null);
			} else {
				dto.setIndustryLabel(msg[1]);
			}
			dto.setPeriodDate(msg[2]);
			dto.setKeyWord(msg[3]);

			if (dto.getPeriodDate() != null) {
				Map<String,Object> map = new HashMap<String,Object>();
				map = dateInit(dto.getPeriodDate(),map);
				dto.setStartDate((String) map.get("startTime"));
				dto.setEndDate((String)map.get("endTime"));
				dto.setPeriodDate(null);
			}
			boolean b = checkDTO(dto);
			if (b) {
				dto.setDimension("产业头条");
				Page<HeadlinesArticleListDTO> page = service.findArticleByKeyWord(dto);
				return success(page);
			}
			return error(MsgConstant.ILLEGAL_PARAM);
		} catch (Exception e) {
			logger.error("根据关键词查询文章内容失败：", e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
		
	
	
	
	/**
	 * 时间初始处理 yyyy-MM-dd
	 * 
	 * @param dto
	 * @return
	 */
	private Map<String,Object> dateInit(String periodDate,Map<String,Object> map ) {
		Date date = new Date();
		String endTime = DateUtil.getFormatDate(date, DateUtil.FORMAT_DATE); // 今天的当前时间（获取服务端时间）
		String startTime = DateUtil.getFormatDate(DateUtil.getStartTime(), DateUtil.FORMAT_DATE); // 今天的起始时间
		String yesterAgo = DateUtil.getFormatDate(DateUtil.getYesterAgoStartTime(date), DateUtil.FORMAT_DATE); // 昨天的起始时间
		String weekAgo = DateUtil.getFormatDate(DateUtil.getWeekAgoStartTime(date), DateUtil.FORMAT_DATE); // 近7天的起始时间
		String monthAgo = DateUtil.getFormatDate(DateUtil.getMonthAgoStartTime(date), DateUtil.FORMAT_DATE); // 一个月内
		String halfYearAgo = DateUtil.getFormatDate(DateUtil.getHalfYearStartTime(date), DateUtil.FORMAT_DATE); // 半年内
		String yearAgo = DateUtil.getFormatDate(DateUtil.getYearStartTime(date), DateUtil.FORMAT_DATE); // 一年内

		// 对时间段进行判断

		if (periodDate.equals("今日")) {
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			
		}
		if (periodDate.equals("昨天")) {
			map.put("startTime", yesterAgo);
			map.put("endTime", yesterAgo);
			
		}
		if (periodDate.equals("近七天")) {
			map.put("startTime", weekAgo);
			map.put("endTime", endTime);
			
		}
		if (periodDate.equals("一个月")) {
			map.put("startTime", monthAgo);
			map.put("endTime", endTime);
			
		}
		if (periodDate.equals("半年")) {
			map.put("startTime",halfYearAgo);
			map.put("endTime", endTime);
			
		}
		if (periodDate.equals("一年")) {
			map.put("startTime", yearAgo);
			map.put("endTime", endTime);
			
		}
		if (periodDate.equals("不限")) {
			map.put("startTime", "1980-01-01");
			map.put("endTime", endTime);
		}
		
		return map;
	}
}
