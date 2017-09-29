package com.huishu.ait.controller.park;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.UserPark;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.common.SearchModel;
import com.huishu.ait.entity.dto.AccountDataDTO;
import com.huishu.ait.entity.dto.AddAccountDTO;
import com.huishu.ait.entity.dto.GardenDataDTO;
import com.huishu.ait.entity.dto.GardenSearchDTO;
import com.huishu.ait.service.user.UserBaseService;
import com.huishu.ait.service.user.backstage.UserParkService;

/**
 * 后台系统-园区管理
 * 
 * @author yindq
 * @create 2017年9月8日
 */
@RestController
@RequestMapping(value = "/apis/back/garden")
public class UserParkController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserParkController.class);
	@Autowired
	private UserParkService userParkService;
	@Autowired
	private UserBaseService userBaseService;

	/**
	 * 查看园区分页列表
	 * 
	 * @param searchModel
	 * @return
	 */
	@RequestMapping(value = "getGardenList.json", method = RequestMethod.POST)
	public AjaxResult getGardenList(@RequestBody GardenSearchDTO searchModel) {
		if (null == searchModel || null == searchModel.getMsg() || 3 != searchModel.getMsg().length) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			List<GardenDataDTO> list = userParkService.getGardenList(searchModel);
			return success(changeObject(searchModel, list));
		} catch (Exception e) {
			LOGGER.error("getAccountList查询失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 添加园区
	 * 
	 * @param userPark
	 * @return
	 */
	@RequestMapping(value = "addGarden.json", method = RequestMethod.POST)
	public AjaxResult addGarden(@RequestBody UserPark userPark) {
		if (null == userPark || StringUtil.isEmpty(userPark.getName())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return userParkService.addGarden(userPark);
		} catch (Exception e) {
			LOGGER.error("addGarden添加失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 查看园区详细信息
	 * 
	 * @param id
	 *            园区ID
	 * @return
	 */
	@RequestMapping(value = "findParkInformation.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult findParkInformation(Long id) {
		if (null == id) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			UserPark park = userParkService.findParkInformation(id);
			return success(park);
		} catch (Exception e) {
			LOGGER.error("findParkInformation失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

	/**
	 * 查看园区账号列表
	 * 
	 * @param searchModel
	 * @return
	 */
	@RequestMapping(value = "findParkAccount.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findParkAccount(@RequestBody SearchModel searchModel) {
		if (null == searchModel || null == searchModel.getUserId()) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			List<AccountDataDTO> list = userParkService.findParkAccount(searchModel);
			return success(changeObject(searchModel, list));
		} catch (Exception e) {
			LOGGER.error("findParkAccount失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

	/**
	 * 添加园区账号列表
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "addParkAccount.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addParkAccount(@RequestBody AddAccountDTO dto) {
		if (null == dto || StringUtil.isEmpty(dto.getTelphone()) || StringUtil.isEmpty(dto.getTime())
				|| StringUtil.isEmpty(dto.getArea()) || StringUtil.isEmpty(dto.getUserType())) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			return userBaseService.addParkAccount(dto);
		} catch (Exception e) {
			LOGGER.error("addParkAccount失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

	/**
	 * 删除园区账号列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "dropParkAccount.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult dropParkAccount(Long id) {
		if (null == id) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			userBaseService.dropParkAccount(id);
			return success(null).setMessage("删除成功");
		} catch (Exception e) {
			LOGGER.error("dropParkAccount失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}
}
