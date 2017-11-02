package com.huishu.ZSServer.controller.openeyes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.service.openeyes.OpeneyesService;

/**
 * 
 * @author yindawei
 * @date 2017年11月1日下午3:40:31
 * @description 天眼查控制层
 * @version
 */
@RestController
@RequestMapping(value = "/apis/openeyes")
public class OpeneyesController extends BaseController {

	@Autowired
	private OpeneyesService openeyesService;
	
	@RequestMapping(value = "/getStaffInfo.json", method = RequestMethod.GET)
	public AjaxResult getStaffInfo(@RequestBody OpeneyesDTO dto) {
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getStaffInfo(dto));
	}
	@RequestMapping(value = "/getEDTInfo.json", method = RequestMethod.GET)
	public AjaxResult getEDTfInfo(@RequestBody OpeneyesDTO dto) {
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getEDTInfo(dto));
	}
}
