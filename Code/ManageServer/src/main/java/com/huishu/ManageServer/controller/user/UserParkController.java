package com.huishu.ManageServer.controller.user;


import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbFirst.UserBase;
import com.huishu.ManageServer.entity.dbFirst.UserPark;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.AccurateDTO;
import com.huishu.ManageServer.entity.vo.GardenDataVO;
import com.huishu.ManageServer.service.user.UserParkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户园区管理
 * 
 * @author yindq
 * @create 2018/1/15
 */
@RestController
@RequestMapping(value = "/apis/park")
public class UserParkController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserParkController.class);
	@Autowired
	private UserParkService userParkService;

	/**
	 * 查看园区分页列表
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "getGardenList.json", method = RequestMethod.POST)
	public AjaxResult getGardenList(@RequestBody AbstractDTO dto) {
		try {
			Page<GardenDataVO> list = userParkService.getGardenList(dto);
			return successPage(list,dto.getPageNum()+1);
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
			Boolean flag = userParkService.addGarden(userPark);
			if (flag) {
				return success(MsgConstant.OPERATION_SUCCESS);
			} else {
				return error(MsgConstant.OPERATION_ERROR);
			}
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
		try {
			return success(userParkService.findParkInformation(id));
		} catch (Exception e) {
			LOGGER.error("findParkInformation失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 查看园区账号列表
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "findParkAccount.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult findParkAccount(@RequestBody AccurateDTO dto) {
		if (null == dto || null == dto.getId()) {
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			Page<UserBase> list = userParkService.findParkAccount(dto);
			return successPage(list,dto.getPageNum()+1);
		} catch (Exception e) {
			LOGGER.error("findParkAccount失败！", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}

	}

}
