package com.huishu.ait.controller.headlines;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.common.util.ConcersUtils.DateUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.es.entity.dto.ArticleListDTO;
import com.huishu.ait.es.entity.dto.HeadlinesArticleListDTO;
import com.huishu.ait.es.entity.dto.HeadlinesDTO;
import com.huishu.ait.service.ExpertOpinion.ExpertOpinionService;
import com.huishu.ait.service.company.CompanyService;
import com.huishu.ait.service.headline.HeadlinesService;
import com.huishu.ait.service.industrialPolicy.IndustrialPolicyService;
import com.merchantKey.itemModel.KeywordModel;

/**
 * @author hhy
 * @date 2017年9月26日
 * @Parem
 * @return 产业的相关接口
 */
@Controller
@RequestMapping("/head")
public class HeadlinesController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(HeadlinesController.class);

	@Autowired
	private HeadlinesService headService;

	@Autowired
	private ExpertOpinionService expertService;

	/**
	 * 查询百家论和专家论的全部内容
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/getExpertOpinion.json")
	@ResponseBody
	public AjaxResult getExpertOpinion(@RequestBody String params) {
		if (StringUtil.isEmpty(params)) {
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject param = JSONObject.parseObject(params);
		Page<AITInfo> list = expertService.findExpertOpinionArticleList(param);
		return success(list);
	}

	/**
	 * 查询产业头条的内容(根据类型，关键词查询文章内容)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getHeadlineInfo.json", method = RequestMethod.POST)
	public AjaxResult getHeadlineInfo(String params) {
		if (StringUtil.isEmpty(params)) {
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject param = StringUtil.paramToJson(params);
		Page<ArticleListDTO> list = headService.findArticleList(param);
		if (list != null) {
			return success(list);
		} else {
			return error("获取产业头条关键词失败！");
		}
	}

	/**
	 * 根据筛选条件，获得关键词
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/getHeadlineKeyWord.json")
	@ResponseBody
	public AjaxResult getHeadlineKeyWord(@RequestBody String params) {
		if (StringUtil.isEmpty(params)) {
			logger.debug(MsgConstant.ILLEGAL_PARAM);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		JSONObject param = JSONObject.parseObject(params);
		List<KeywordModel> list = headService.findArticleKeyword(param);
		if (list != null) {
			return success(list);
		} else {
			return error("获取产业头条关键词失败！");
		}
	}

	@RequestMapping(value = { "{page}" }, method = RequestMethod.GET)
	public String pageHeadlines(@PathVariable String page) {
		return "industry/" + page;
	}

	/**
	 * 前台传递产业类型，后台返回产业标签
	 * 
	 * @param industry
	 * @return
	 */
	@RequestMapping(value = "/getLabel.json", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getLabel(String industry) {
		JSONObject data = new JSONObject();
		String[] label;
		industry = industry.trim();
		if ("互联网".equals(industry)) {
			label = new String[] { "网络游戏", "大数据", "电子商务", "网络视听", "移动阅读", "智能硬件" };
			industry = "互联网+";
		} else if ("高科技".equals(industry)) {
			label = new String[] { "新一代信息技术", "生物医药", "节能环保技术装备", "新能源", "新材料", "航空装备", "人工智能" };
		} else if ("文化创意".equals(industry)) {
			label = new String[] { "动漫制作", "影视传媒", "图书出版", "广告营销" };
		} else if ("精英配套".equals(industry)) {
			label = new String[] { "精英服务", "住宅地产", "商业综合体", "康体美容", "健康产业", "教育培训" };
		} else if ("其他".equals(industry)) {
			label = new String[] { "特色旅游综合体", "体育产业" };
		} else {
			label = new String[] { "生鲜贸易", "食品加工", "冷链物流" };
		}
		data.put("industry", industry);
		data.put("label", label);
		return data;
	}

	@RequestMapping(value = "/getDetail.json", method = RequestMethod.GET)
	public ModelAndView getDetail(String id, ModelAndView model) {
		AITInfo a = expertService.findDetail(id);
		model.addObject("detail", a);
		model.setViewName("industry/detail");
		return model;
	}

	/**
	 * 产业头条--关键词查文章
	 * 
	 * @param headlinesDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getArticleByKeyWordList.json", method = RequestMethod.POST)
	public AjaxResult getArticleByKeyWordList(@RequestBody HeadlinesDTO headlinesDTO) {
		try {
			HeadlinesDTO dto = new HeadlinesDTO();
			String[] msg = headlinesDTO.getMsg();
			dto.setIndustry(msg[0]);
			String industrtLabel = msg[1];

			if (industrtLabel.equals("不限")) {
				dto.setIndustryLabel("");
			} else {
				dto.setIndustryLabel(msg[1]);
			}
			dto.setPeriodDate(msg[2]);
			dto.setKeyWord(msg[3]);

			if (dto.getPeriodDate() != null) {
				dto = dateInit(dto);
				dto.setPeriodDate(null);
			}
			boolean b = checkDTO(dto);
			if (b) {

				Page<HeadlinesArticleListDTO> page = headService.findArticleByKeyWord(dto);
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
	private HeadlinesDTO dateInit(HeadlinesDTO dto) {
		Date date = new Date();
		String endTime = DateUtil.getFormatDate(date, DateUtil.FORMAT_DATE); // 今天的当前时间（获取服务端时间）
		String startTime = DateUtil.getFormatDate(DateUtil.getStartTime(), DateUtil.FORMAT_DATE); // 今天的起始时间
		String yesterAgo = DateUtil.getFormatDate(DateUtil.getYesterAgoStartTime(date), DateUtil.FORMAT_DATE); // 昨天的起始时间
		String weekAgo = DateUtil.getFormatDate(DateUtil.getWeekAgoStartTime(date), DateUtil.FORMAT_DATE); // 近7天的起始时间
		String monthAgo = DateUtil.getFormatDate(DateUtil.getMonthAgoStartTime(date), DateUtil.FORMAT_DATE); // 一个月内
		String halfYearAgo = DateUtil.getFormatDate(DateUtil.getHalfYearStartTime(date), DateUtil.FORMAT_DATE); // 半年内
		String yearAgo = DateUtil.getFormatDate(DateUtil.getYearStartTime(date), DateUtil.FORMAT_DATE); // 一年内

		// 对时间段进行判断
		String periodDate = dto.getPeriodDate();

		if (periodDate.equals("今日")) {
			dto.setStartDate(startTime);
			dto.setEndDate(endTime);
		}
		if (periodDate.equals("昨天")) {
			dto.setStartDate(yesterAgo);
			dto.setEndDate(startTime);
		}
		if (periodDate.equals("近七天")) {
			dto.setStartDate(weekAgo);
			dto.setEndDate(endTime);
		}
		if (periodDate.equals("一个月")) {
			dto.setStartDate(monthAgo);
			dto.setEndDate(endTime);
		}
		if (periodDate.equals("半年")) {
			dto.setStartDate(halfYearAgo);
			dto.setEndDate(endTime);
		}
		if (periodDate.equals("一年")) {
			dto.setStartDate(yearAgo);
			dto.setEndDate(endTime);
		}
		if (periodDate.equals("不限")) {
			dto.setStartDate("1980-01-01");
			dto.setEndDate(endTime);
		}
		return dto;
	}
}
