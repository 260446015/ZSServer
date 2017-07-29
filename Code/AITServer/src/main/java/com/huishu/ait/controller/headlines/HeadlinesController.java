package com.huishu.ait.controller.headlines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.service.Headlines.HeadlinesService;

/**
 * @author hhy
 * @date 2017年7月27日
 * @Parem
 * @return 
 * 产业头条
 */
@Controller
@RequestMapping("/Headlines")
public class HeadlinesController  extends BaseController{
	private static Logger log = LoggerFactory.getLogger(HeadlinesController.class);
	@Autowired
	private HeadlinesService service ;
	/**关键词云*/
	@ResponseBody
	@RequestMapping(value="/getWordClond.json",method=RequestMethod.POST)
	public AjaxResult getWordClond(@RequestBody HeadlinesDTO headlinesDTO){
		try {
			  boolean b = checkDTO(headlinesDTO);
			  if(b){
				return success(service.getWordCloud(headlinesDTO));
			  }
			  return error(MsgConstant.ILLEGAL_PARAM);
		} catch (Exception e) {
			log.error("查询失败：",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		
		}
	}
	/**媒体云图*/
	@ResponseBody
	@RequestMapping(value="getCarClondChartList.json",method=RequestMethod.POST)
	public AjaxResult  getCarClondChartList(@RequestBody HeadlinesDTO headlinesDTO){
	  try {
		  boolean b = 	checkDTO(headlinesDTO);
		  if(b){
			return success(service.getCarClondChartList(headlinesDTO));
		  }
		  return error(MsgConstant.ILLEGAL_PARAM);
	} catch (Exception e) {
		log.error("查询失败：",e);
		return error(MsgConstant.ILLEGAL_PARAM);
	
	}
	}
	
	
}
