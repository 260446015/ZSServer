package com.huishu.ZSServer.controller.garden;

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

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.DateUtils;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.GardenCompare;
import com.huishu.ZSServer.entity.dto.AreaSearchDTO;
import com.huishu.ZSServer.entity.garden.GardenDTO;
import com.huishu.ZSServer.entity.garden.GardenData;
import com.huishu.ZSServer.entity.garden.GardenIndustry;
import com.huishu.ZSServer.entity.garden.GardenMap;
import com.huishu.ZSServer.entity.garden.GardenUser;
import com.huishu.ZSServer.es.entity.AITInfo;
import com.huishu.ZSServer.service.garden.GardenService;
import com.huishu.ZSServer.service.garden_user.GardenUserService;

/**
 * 处理园区的controller
 * 
 * @author yindawei
 * @date 2017年10月27日上午11:39:44
 * @description
 * @version
 */
@Controller
@RequestMapping("/apis/area")
public class GardenController extends BaseController {
	private static Logger LOGGER = LoggerFactory.getLogger(GardenController.class);
	@Autowired
	private GardenService gardenService;
	@Autowired
	private GardenUserService gardenUserService;

	/**
	 * 获取园区动态
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/findGardensCondition.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findGardensCondition(@RequestBody GardenDTO dto) {
		// Long userId = getUserId();
		Long userId = 1L;
		dto.setUserId(userId);
		// dto.setPageNumber(pageNumber);
		// dto.setPageSize(pageSize);
		// dto.setProvince(area);
		Page<AITInfo> page = null;
		try {
			page = gardenService.findGardensCondition(dto);
		} catch (Exception e) {
			LOGGER.error("查询园区动态失败!", e);
			return error(e.getMessage());
		}
		return success(page.getContent());
	}

	/**
	 * 获取园区产值
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/findGardenGdp.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findGardenGdp(@RequestBody GardenDTO dto) {
		if (StringUtil.isEmpty(dto.getIndustry()))
			return error(MsgConstant.ILLEGAL_PARAM);
		if (dto.getYear() == null)
			dto.setYear(new Integer[] { DateUtils.getNowYear() });
		List<GardenMap> list = gardenService.findGardenGdp(dto);
		return success(list);
	}

	/**
	 * 关注/取消关注园区
	 * 
	 * @param gardenId
	 *            园区id
	 * @param flag
	 *            true关注，false取消关注
	 * @return
	 */
	@RequestMapping(value = "/attentionGarden.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult attentionGarden(Long gardenId, Boolean flag) {
		GardenUser gardenUser = null;
		if (null == gardenId || null == flag) {
			error(MsgConstant.ILLEGAL_PARAM);
		}
		// Long userId = getUserId();
		Long userId = 1L;
		try {
			if (flag)
				gardenUser = gardenUserService.attentionGarden(gardenId, userId, true);
			else
				gardenUser = gardenUserService.attentionGarden(gardenId, userId, false);
		} catch (Exception e) {
			LOGGER.error("关注园区失败", e);
			return error("关注园区失败");
		}
		return success(gardenUser);

	}

	/**
	 * 获取园区列表
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/findGardensList.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findGardensList(@RequestBody GardenDTO dto) {
		if (null == dto.getMsg() || dto.getMsg().length == 0) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Page<GardenData> page = null;
		try {
			Long userId = 1L;
			// Long userId = getUserId();
			dto.setUserId(userId);
			page = gardenService.findGardensList(dto);
		} catch (Exception e) {
			LOGGER.error("查询园区列表失败!", e);
			return error("查询园区列表失败!");
		}
		return success(page);
	}

	/**
	 * 获取关注园区列表
	 * 
	 * @param dto
	 *            [id,area,industryType]
	 * @return
	 */
	@RequestMapping(value = "/getAttentionGardenList.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getAttentionGardenList(@RequestBody GardenDTO dto) {
		// Long userId = getUserId();
		Long userId = 1L;
		if (null == dto || userId == null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Page<GardenUser> page = null;
		try {
			page = gardenUserService.getAttentionGardenList(dto);
			return success(page);
		} catch (Exception e) {
			LOGGER.error("查询关注园区失败!", e);
			return error(e.getMessage());
		}
	}

	/**
	 * 查询园区基本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findGardenInfo.json", method = RequestMethod.GET,params={"gardenName"})
	@ResponseBody
	public AjaxResult findGardenInfo(String gardenName) {
		if (StringUtil.isEmpty(gardenName)) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(gardenService.findGarden(gardenName));
	}

	/**
	 * 关注园区-情报推送
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "getInformationPush.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getInformationPush(@RequestBody AreaSearchDTO dto) {
		if (null == dto || StringUtil.isEmpty(dto.getPark()) || StringUtil.isEmpty(dto.getDimension())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Page<AITInfo> page = gardenService.getInformationPush(dto);
			return successPage(page,dto.getPageNumber()+1);
		} catch (Exception e) {
			LOGGER.error("获取关注园区-情报推送失败！", e);
			return error(MsgConstant.ILLEGAL_PARAM);
		}
	}

	/**
	 * 政策动向
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "getGardenPolicy.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getGardenPolicy(@RequestBody GardenDTO dto) {
		if (StringUtil.isEmpty(dto.getProvince()))
			return error(MsgConstant.ILLEGAL_PARAM);
		Page<AITInfo> page = gardenService.findGardenPolicy(dto);
		return success(page.getContent());
	}

	/**
	 * echarts返回数据
	 * 
	 * @param province
	 *            省份
	 * @return
	 */
	@RequestMapping(value = "getGardenIndustryEcharts.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGardenIndustryEcharts(String province, Integer year) {
		if (StringUtil.isEmpty(province))
			return error(MsgConstant.ILLEGAL_PARAM);
		List<Object[]> list = gardenService.getGardenIndustryEcharts(province, year);
		list.sort((a, b) -> {
			System.out.println(b[1]);
			System.out.println(a[1]);
			Double a1 = Double.parseDouble(b[1].toString());
			Double a2 = Double.parseDouble(a[1].toString());
			return a2.compareTo(a1);
		});
		return success(list);
	}

	/**
	 * 加入园区对比的功能
	 * 
	 * @return
	 */
	@RequestMapping(value = "addGardenCompare.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult addGardenCompare(Long gardenId) {
		if (null == gardenId)
			return error(MsgConstant.ILLEGAL_PARAM);
		Long userId = 1L;
		return success(gardenUserService.addGardenCompare(gardenId, userId));
	}

	/**
	 * 展示园区对比的功能
	 * 
	 * @return
	 */
	@RequestMapping(value = "getGardenCompare.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGardenCompare() {
		Long userId = 1L;
		return success(gardenUserService.getGardenCompare(userId, null));
	}

	/**
	 * 删除园区对比数据的功能
	 */
	@RequestMapping(value = "deleteGardenCompare.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult deleteGardenCompare(Long gardenId) {
		Long userId = 1L;
		List<GardenCompare> list = gardenUserService.getGardenCompare(userId, gardenId);
		boolean flag = gardenUserService.deleteCompare(list);
		return success(flag);
	}

	/**
	 * 访问链接
	 */
	@RequestMapping(value = { "{page}" })
	public String gardenMap(@PathVariable String page) {
		return "gardenMap/" + page;
	}

	/**
	 * 返回gardenMap中展示园区产业数量的controller
	 */
	@RequestMapping(value = "/getGardenIndustryCount.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getGardenIndustryCount(@RequestBody GardenDTO dto) {
		if (StringUtil.isEmpty(dto.getIndustryType())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(gardenService.getGardenIndustryCount(dto));
	}

	/**
	 * 返回gardenMap中展示某个城市各种产业分布的controller
	 */
	@RequestMapping(value = "/getIndustryByProvince.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getIndustryByProvince(@RequestBody GardenDTO dto) {
		if (StringUtil.isEmpty(dto.getProvince())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(gardenService.getIndustryByProvince(dto));
	}

	/**
	 * 查询园区产业分类
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getGardenIndustry.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGardenIndustry() {
		List<GardenIndustry> list = gardenService.getGardenIndustry();
		return success(list);
	}

	/**
	 * 查询园区地域分类
	 */
	@RequestMapping(value = "getGardenArea.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGardenArea() {
		return success(gardenService.getGardenArea());
	}
	/**
	 * 查询园区关注地域分类
	 */
	@RequestMapping(value = "getGardenAttainArea.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGardenAttainArea() {
		return success(gardenUserService.getGardenAttainArea());
	}
}
