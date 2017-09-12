package com.huishu.ait.controller.user.backstage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.UserPark;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.GardenDataDTO;
import com.huishu.ait.entity.dto.GardenSearchDTO;
import com.huishu.ait.service.user.backstage.UserParkService;

/**
 * 后台系统-园区管理
 * 
 * @author yindq
 * @create 2017年9月8日
 */
@RestController
@RequestMapping(value = "/apis/userPark/garden")
public class UserParkController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserParkController.class);
	@Autowired
	private UserParkService userParkService;
	/**
	 * 查看园区分页列表
	 * @param searchModel
	 * @return
	 */
	@RequestMapping(value = "getGardenList.json", method = RequestMethod.POST)
	public AjaxResult getGardenList(@RequestBody GardenSearchDTO searchModel) {
		if (null==searchModel || null==searchModel.getMsg()) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			List<GardenDataDTO> list = userParkService.getGardenList(searchModel);
			return success(changeObject(searchModel, list));
		} catch (Exception e) {
			LOGGER.error("getAccountList查询失败！",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 添加园区
	 * @param userPark
	 * @return
	 */
	@RequestMapping(value = "addGarden.json", method = RequestMethod.POST)
	public AjaxResult addGarden(UserPark userPark){
		if (null==userPark || StringUtil.isEmpty(userPark.getName())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return userParkService.addGarden(userPark);
		} catch (Exception e) {
			LOGGER.error("addGarden添加失败！",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}
