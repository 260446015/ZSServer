
package com.huishu.ait.controller.headlines;

import java.util.Date;
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
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils.DateUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.IndustrialPolicyDTO;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.service.Headlines.HeadlinesService;
import com.huishu.ait.es.entity.dto.HeadlinesArticleListDTO;

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
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getWordClond.json",method=RequestMethod.POST)
	public AjaxResult getWordClond(@RequestBody HeadlinesDTO headlinesDTO) {
		try {
			HeadlinesDTO dto = CheckDTO(headlinesDTO);
			if (dto.getPeriodDate() != null){
                dto = dateInit(dto);
               dto.setPeriodDate(null);
            }
			boolean b = checkDTO(dto);
			if (b) {
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
		if(industrtLabel.equals("不限")){
			dto.setIndustryLabel("");
		}else{
			dto.setIndustryLabel(msg[1]);
		}
		dto.setPeriodDate(msg[2]);
		if(msg.length>=4&&msg.length<5){
			dto.setVector(msg[3]);
		}
		return dto;
	}

	/**
	 * 产业头条--媒体云图
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getClondChartList.json" ,method=RequestMethod.POST)
	public AjaxResult getCarClondChartList(@RequestBody HeadlinesDTO headlinesDTO) {
		try {
			HeadlinesDTO dto = CheckDTO(headlinesDTO);
			if (dto.getPeriodDate() != null){
                dto = dateInit(dto);
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
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getArticleByVectorList.json",method=RequestMethod.POST)
	public AjaxResult getArticleByVectorList(@RequestBody HeadlinesDTO headlinesDTO){
			try {
				HeadlinesDTO dto = new HeadlinesDTO();
				String[] msg = headlinesDTO.getMsg();
				dto.setIndustry(msg[0]);
				String industrtLabel = msg[1];
				if(industrtLabel.equals("不限")){
					dto.setIndustryLabel("");
				}else{
					dto.setIndustryLabel(msg[1]);
				}
				dto.setPeriodDate(msg[2]);
				dto.setVector(msg[3]);
				if (dto.getPeriodDate() != null){
	                dto = dateInit(dto);
	                dto.setPeriodDate(null);
	            }
				 boolean b = checkDTO(dto);
				 if(b){
					 dto.setDimension("产业头条");
					Page<HeadlinesArticleListDTO> page = service.findArticleByVector(dto);
					 return success(page);
				 }
				 return error(MsgConstant.ILLEGAL_PARAM);
			} catch (Exception e) {
				logger.error("查询头条信息失败：",e);
				return error("参数不合法");
			}
		}
	/**
	 * 产业头条--关键词查文章
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getArticleByKeyWordList.json",method=RequestMethod.POST)
	public AjaxResult getArticleByKeyWordList(@RequestBody HeadlinesDTO headlinesDTO ){
		try {
			HeadlinesDTO dto = new HeadlinesDTO();
			String[] msg = headlinesDTO.getMsg();
			dto.setIndustry(msg[0]);
			String industrtLabel = msg[1];
//			String industrtLabel = "不限";
//			dto.setIndustry("互联网");
			
			if(industrtLabel.equals("不限")){
				dto.setIndustryLabel("");
			}else{
				dto.setIndustryLabel(msg[1]);
			}
			dto.setPeriodDate(msg[2]);
			dto.setKeyWord(msg[3]);
//			dto.setKeyWord("创新");
//			dto.setPeriodDate("一年");
			if (dto.getPeriodDate() != null){
                dto = dateInit(dto);
                dto.setPeriodDate(null);
            }
			boolean b = checkDTO(dto);
			if(b){
				
				Page<HeadlinesArticleListDTO> page = service.findArticleByKeyWord(dto);
				return success(page);
			}
			return error(MsgConstant.ILLEGAL_PARAM);
		} catch (Exception e) {
			logger.error("根据关键词查询文章内容失败：",e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}
	/**
	 * 产业头条--根据文章id查看文章详情（针对载体文章和关键词文章）
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getArticleById.json",method=RequestMethod.GET )
	public AjaxResult getVectorArticleById(@RequestBody String id){
		AITInfo byId = service.findArticleById(id);
		return success(byId);
	}
	/**
     * 时间初始处理 yyyy-MM-dd
     * @param dto
     * @return
     */
    private HeadlinesDTO dateInit(HeadlinesDTO dto){
        Date date = new Date();
        String endTime = DateUtil.getFormatDate(date, DateUtil.FORMAT_DATE); //今天的当前时间（获取服务端时间）
        String startTime = DateUtil.getFormatDate(DateUtil.getStartTime(), DateUtil.FORMAT_DATE); //今天的起始时间
        String yesterAgo = DateUtil.getFormatDate(DateUtil.getYesterAgoStartTime(date), DateUtil.FORMAT_DATE); //昨天的起始时间
        String weekAgo = DateUtil.getFormatDate(DateUtil.getYearStartTime(date), DateUtil.FORMAT_DATE); //近7天的起始时间
        String monthAgo = DateUtil.getFormatDate(DateUtil.getMonthAgoStartTime(date), DateUtil.FORMAT_DATE); //一个月内
        String halfYearAgo = DateUtil.getFormatDate(DateUtil.getHalfYearStartTime(date), DateUtil.FORMAT_DATE); //半年内
        String yearAgo = DateUtil.getFormatDate(DateUtil.getYearStartTime(date), DateUtil.FORMAT_DATE); //一年内
        
        //对时间段进行判断
        if(dto.getPeriodDate().equals("今日")){
            dto.setStartDate(startTime);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("昨天")){
            dto.setStartDate(yesterAgo);
            dto.setEndDate(startTime);
        }
        if(dto.getPeriodDate().equals("近7天")){
            dto.setStartDate(weekAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("1个月")){
            dto.setStartDate(monthAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("半年")){
            dto.setStartDate(halfYearAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("一年")){
            dto.setStartDate(yearAgo);
            dto.setEndDate(endTime);
        }
        if(dto.getPeriodDate().equals("不限")){
            dto.setStartDate("1980-01-01");
            dto.setEndDate(endTime);
        }
        return dto;
    }
}
