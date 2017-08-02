package com.huishu.ait.controller.headlines;

import java.util.List;

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
import com.huishu.ait.common.Echarts.transform.EchartsDataTransform;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.es.entity.dto.HeadlinesArticleListDTO;
import com.huishu.ait.service.Headlines.HeadlinesService;

/**
 * @author hhy
 * @date 2017年7月27日
 * @Parem
 * @return 产业头条
 */
@Controller
@RequestMapping("/Headlines")
public class HeadlinesController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(HeadlinesController.class);
	@Autowired
	private EchartsDataTransform ecdTransformer;
	@Autowired
	private HeadlinesService service;

	/**
	 * 产业头条--关键词云
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getWordClond.json", method = RequestMethod.POST)
	public AjaxResult getWordClond(@RequestBody HeadlinesDTO headlinesDTO) {
		try {
			boolean b = checkDTO(headlinesDTO);
			if (b) {
				JSONArray cloud = ecdTransformer.transformWordCloud(service.getWordCloud(headlinesDTO));
				return success(cloud);
			}
			return error(MsgConstant.ILLEGAL_PARAM);
		} catch (Exception e) {
			logger.error("查询失败：", e);
			return error(MsgConstant.ILLEGAL_PARAM);

		}
	}

	/**
	 * 产业头条--媒体云图
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getClondChartList.json", method = RequestMethod.POST)
	public AjaxResult getCarClondChartList(@RequestBody HeadlinesDTO headlinesDTO) {
		try {
			boolean b = checkDTO(headlinesDTO);
			if (b) {
				return success(service.getCarClondChartList(headlinesDTO));
			}
			return error(MsgConstant.ILLEGAL_PARAM);
		} catch (Exception e) {
			logger.error("查询失败：", e);
			return error(MsgConstant.ILLEGAL_PARAM);

		}
	}
	
	/**
	 * 产业头条--今日头条
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getArticleByVectorList.json",method=RequestMethod.POST)
	public AjaxResult getArticleByVectorList(@RequestBody HeadlinesDTO headlinesDTO){
			try {
				 boolean b = checkDTO(headlinesDTO);
				 if(b){
					Page<HeadlinesArticleListDTO> page = service.findArticleByVector(headlinesDTO);
					 return success(page);
				 }
				 return error(MsgConstant.ILLEGAL_PARAM);
			} catch (Exception e) {
				logger.error("查询头条信息失败：",e);
				return null;
			}
		}
	/**
	 * 产业头条--根据文章id查看文章详情（针对载体文章和关键词文章）
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getArticleById.json",method=RequestMethod.GET)
	public AjaxResult getVectorArticleById(@RequestBody String id){
		AITInfo dto = service.findArticleById(id);
		return success(dto);
	}
}
