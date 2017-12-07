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
import com.huishu.ZSServer.common.Data;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.IndustryRank;
import com.huishu.ZSServer.entity.Institutional;
import com.huishu.ZSServer.es.entity.SummitInfo;
import com.huishu.ZSServer.service.company.CompanyService;
import com.huishu.ZSServer.service.company.EnterPriseService;
import com.huishu.ZSServer.service.indus.IndusSummitService;
import com.huishu.ZSServer.service.indus.IndustryRankService;
import com.huishu.ZSServer.service.indus.InstitutionalService;

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
	@Autowired
	private InstitutionalService iservice;
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
	 * 获取地图map数据
	 * @param industry
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getMapInfoByIndustry.json",method = RequestMethod.GET)
	public AjaxResult getMapInfoByIndustry(@Param("industry") String industry){
		if(StringUtil.isEmpty(industry)){
			LOGGER.debug("根据产业查询城市地图数据失败："+industry);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		if(industry.equals("生物技术")){
			industry = "生物产业";
		}
		JSONObject list = rservice.findMapInfo(industry);
		
		return success(list);
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
		if(industry.equals("生物技术")){
			industry="生物科技";
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
	public AjaxResult getIndustrySummit(@Param("industry") String industry){
		if(StringUtil.isEmpty(industry)){
			LOGGER.debug("根据产业查询峰会列表失败："+industry);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject obj = new JSONObject();
		obj.put("dimension", "高峰论坛");
		if(industry.equals("生物技术")){
			obj.put("industry", "生物产业");
		}else{
			obj.put("industryLabel", industry);
		}
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
		if(industry.equals("生物技术")){
			industry="生物科技";
		}
		List<String> list = cservice.findCompanyName(area ,industry);
		return success(list);
	}
	
	/**
	 * 获取国家重点实验室
	 * @param industry
	 * @param area
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getLaboratoryInfo.json",method=RequestMethod.POST)
	public AjaxResult getLaboratoryInfo(@Param("industry") String industry,@Param("area") String area){
		if(StringUtil.isEmpty(industry)||StringUtil.isEmpty(area)){
			LOGGER.debug("获取国家重点实验室详情失败，参数异常");
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		List<Institutional> info = iservice.getInstutionalInfo(area,industry);
		if(info==null){
			return error("暂无数据");
		}else{
			return success(info);
		}
	}
	/**
	 * 根据国家实验室的id 关注 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertLaboratoryInfo.json",method=RequestMethod.GET)
	public AjaxResult  insertLaboratoryInfo(@Param("id") Long id){
		if(id==null){
			LOGGER.debug("关注实验室参数异常"+id);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		//当前用户的用户名，测试专用
		String name = "张三";
		String info = iservice.saveLaboratoryInfoById(id,name);
		return success(info);
	}
}
