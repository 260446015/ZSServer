package com.huishu.ait.controller.headlines;

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
import com.huishu.ait.entity.Company;
import com.huishu.ait.entity.UserPark;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.dto.BusinessSuperviseDTO;
import com.huishu.ait.service.garden.GardenService;

/**
 * 园区情报的相关内容
 * 
 * @author yindq
 * @create 2017年10月13日
 */
@Controller
@RequestMapping("/apis/parkInfo")
public class parkInformationController extends BaseController{
	
	private static Logger LOGGER = LoggerFactory.getLogger(parkInformationController.class);
	
	@Autowired
	private GardenService gardenService;
	
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "{page}", method = RequestMethod.GET)
	public String showAccount(@PathVariable String page) {
		return "industry/"+page;
	}
	
	/**
	 * 获取园区信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "parkDetails.json", method = RequestMethod.GET)
	public String parkDetails(Long id,Model model){
		try {
			UserPark garden = gardenService.findGarden(id);
			model.addAttribute("garden",garden);
		} catch (Exception e) {
			LOGGER.error("parkDetails失败！", e);
		}
		return "industry/parkDetails";
	}
	
	/**
	 * 修改园区信息
	 * @param garden
	 * @return
	 */
	@RequestMapping(value = "changeGarden.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult changeGarden(@RequestBody UserPark garden){
		try {
			gardenService.changeGarden(garden);
			return success(null).setMessage("操作成功");
		} catch (Exception e) {
			LOGGER.error("changeGarden失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 获取园区内入驻企业/园区政策/园区情报
	 * @param searchModel
	 * @return
	 */
	@RequestMapping(value = "/findInformationList.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findInformationList(@RequestBody BusinessSuperviseDTO searchModel) {
		if (StringUtil.isEmpty(searchModel.getPark())||StringUtil.isEmpty(searchModel.getDimension())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			JSONArray array = gardenService.findInformationList(searchModel);
			return success(changeObject(searchModel, array));
		} catch (Exception e) {
			LOGGER.error("获取ES列表失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 删除园区内入驻企业
	 * @param companyName
	 * @param park
	 * @return
	 */
	@RequestMapping(value = "/dropCompany.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult dropCompany(String companyName,String park) {
		if (companyName==null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			gardenService.dropCompany(park,companyName);
			return success(null).setMessage("操作成功");
		} catch (Exception e) {
			LOGGER.error("删除园区企业失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 添加园区内入驻企业
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/addCompany.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addCompany(@RequestBody Company company) {
		if (company==null) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			gardenService.addCompany(company);
			return success(null).setMessage("操作成功");
		} catch (Exception e) {
			LOGGER.error("添加园区企业失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}
