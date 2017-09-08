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
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.GardenDataDTO;
import com.huishu.ait.entity.dto.GardenSearchDTO;
import com.huishu.ait.service.user.AdminService;

/**
 * 后台系统-园区管理
 * 
 * @author yindq
 * @create 2017年9月8日
 */
@RestController
@RequestMapping(value = "/apis/admin/garden")
public class GardenController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GardenController.class);
	@Autowired
	private AdminService adminService;
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
			List<GardenDataDTO> list = adminService.getGardenList(searchModel);
			return success(changeObject(searchModel, list));
		} catch (Exception e) {
			LOGGER.error("getAccountList查询失败！",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
}
