package com.huishu.ManageServer.controller.user;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.conf.PageConstant;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.vo.UserLogoVO;
import com.huishu.ManageServer.service.user.UserMonitorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 账户监控模块
 *
 * @author yindq
 * @date 2018/1/11
 */
@Controller
@RequestMapping("/apis/monitor")
public class UserMonitorController extends BaseController{

	@Autowired
	private UserMonitorService userMonitorService;

	private static final Logger LOGGER = Logger.getLogger(UserMonitorController.class);

	/**
	 * 分页查看用户日志列表
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "listLoginLogo.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult listLoginLogo(@RequestBody AbstractDTO dto) {
		try {
			Page<UserLogoVO> page = userMonitorService.listLogo(PageConstant.LOGOTYPE_LOGIN,dto);
			return successPage(page,dto.getPageNum()+1);
		}catch (Exception e){
			LOGGER.error("分页查看用户登录日志列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

	/**
	 * 分页查看用户操作日志列表
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "listOperationLogo.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult listOperationLogo(@RequestBody AbstractDTO dto) {
		try {
			Page<UserLogoVO> page = userMonitorService.listLogo(PageConstant.LOGOTYPE_OPERATION,dto);
			return successPage(page,dto.getPageNum()+1);
		}catch (Exception e){
			LOGGER.error("分页查看用户操作日志列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}

}
