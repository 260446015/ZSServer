package com.huishu.ZSServer.controller.industry;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.IndustryRank;
import com.huishu.ZSServer.es.entity.SummitInfo;
import com.huishu.ZSServer.service.company.CompanyService;
import com.huishu.ZSServer.service.company.EnterPriseService;
import com.huishu.ZSServer.service.indus.IndusSummitService;
import com.huishu.ZSServer.service.indus.IndustryRankService;

/**
 * @author hhy
 * @date 2017年11月21日
 * @Parem
 * @return 
 * 产业地图页面相关控制方式
 */
@Controller
@RequestMapping("/indusMap")
public class IndustryMapController extends BaseController{

	private Logger LOGGER = LoggerFactory.getLogger(IndustryMapController.class);
	@Autowired
	private IndusSummitService service ;
	@Autowired
	private IndustryRankService rservice;
	@Autowired
	private EnterPriseService cservice;
	/**
	 * 产业地图页面跳转
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/map",method=RequestMethod.GET)
	public String getIndustryMap(Map<String,Object> map){
		JSONObject obj = new JSONObject();
		obj.put("dimension", "高峰论坛");
		obj.put("industryLabel", "人工智能");
		List<SummitInfo> list = service.findIndustrySummitList(obj);
		LOGGER.info("获取峰会数据成功");
		map.put("summit", list);
		return "/industry/industryMap";
	}
	
	/**
	 * 根据产业信息获取所有的内容
	 * @param industry
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getInfoByIndustry.json",method = RequestMethod.GET)
	public AjaxResult getInfoByIndustry(String industry){
		if(StringUtil.isEmpty(industry)){
			LOGGER.debug("根据产业查询所有信息失败："+industry);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		
		return null;
	}
	/**
	 * 获取企业热度城市排行
	 * @param industry
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getRankInfoByIndustry.json",method = RequestMethod.GET)
	public AjaxResult getRankInfoByIndustry(@Param("industry") String industry){
		if(StringUtil.isEmpty(industry)){
			LOGGER.debug("根据产业查询产业热度排行数据失败："+industry);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		List<IndustryRank> list = rservice.findIndustryRank(industry);
		return success(list);
	}
	/**
	 * 根据产业查看峰会数据
	 * @param industryLabel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getIndustrySummit.json",method = RequestMethod.POST)
	public AjaxResult getIndustrySummit( String industryLabel){
		if(StringUtil.isEmpty(industryLabel)){
			LOGGER.debug("根据产业查询峰会详情失败："+industryLabel);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject obj = new JSONObject();
		obj.put("dimension", "高峰论坛");
		obj.put("industryLabel", industryLabel);
		List<SummitInfo> list = service.findIndustrySummitList(obj);
		return success(list);
	}

	/**
	 * 根据地区显示公司名称 
	 */
	@ResponseBody
	@RequestMapping(value="/getBusinessListByArea.json",method=RequestMethod.POST)
	public AjaxResult getBusinessListByArea(@Param("industry") String industry,@Param("area") String area ){
		if(StringUtil.isEmpty(industry)||StringUtil.isEmpty(area)){
			LOGGER.debug("根据地区排行地区显示公司名称失败，参数异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		List<String> list = cservice.findCompanyName(area ,industry);
		return success(list);
	}
}
