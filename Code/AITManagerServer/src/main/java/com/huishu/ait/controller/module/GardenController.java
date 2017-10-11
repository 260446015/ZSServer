package com.huishu.ait.controller.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.GardenDTO;
import com.huishu.ait.es.entity.dto.BusinessSuperviseDTO;
import com.huishu.ait.service.garden.GardenService;

/**
 * 园区监管管理
 * 
 * @author yindq
 * @create 2017年9月28日
 */
@Controller
@RequestMapping("/apis/area")
public class GardenController extends BaseController {
	private static Logger LOGGER = LoggerFactory.getLogger(GardenController.class);

	@Autowired
	private GardenService gardenService;

	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "{page}", method = RequestMethod.GET)
	public String showAccount(@PathVariable String page,String park,Model model) {
		if(!StringUtil.isEmpty(park)){
			model.addAttribute("park",park);
		}
		return "moduleManagement/"+page;
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
		if (StringUtil.isEmpty(dto.getArea())||StringUtil.isEmpty(dto.getType())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return success(gardenService.findGardensList(dto));
		} catch (Exception e) {
			LOGGER.error("查询园区列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 获取园区内企业动态/疑似外流
	 * @param searchModel
	 * @return
	 */
	@RequestMapping(value = "/findDynamicList.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findDynamicList(@RequestBody BusinessSuperviseDTO searchModel) {
		if (StringUtil.isEmpty(searchModel.getPark())||StringUtil.isEmpty(searchModel.getDimension())
				||StringUtil.isEmpty(searchModel.getEmotion())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			JSONArray array = gardenService.findDynamicList(searchModel);
			return success(changeObject(searchModel, array));
		} catch (Exception e) {
			LOGGER.error("获取ES列表失败：", e);
			return error("获取ES列表失败");
		}
	}
	
	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/dropEssay.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult dropEssay(String id) {
		if (id==null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			gardenService.dropEssay(id);
			return success(null).setMessage("删除成功");
		} catch (Exception e) {
			LOGGER.error("删除文章失败：", e);
			return error("删除文章失败");
		}
	}

}
