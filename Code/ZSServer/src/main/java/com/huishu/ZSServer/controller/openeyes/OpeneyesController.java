package com.huishu.ZSServer.controller.openeyes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.StringUtil;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.entity.openeyes.BaseInfo;
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
	@RequestMapping(value="/getBaseInfo.json",method=RequestMethod.GET)
	public AjaxResult getBaseInfo(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getBaseInfo(dto));
	}
	@RequestMapping(value="/getBranch.json",method=RequestMethod.GET)
	public AjaxResult getBranch(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getBranch(dto));
	}
	@RequestMapping(value="/getHistoryRongZi.json",method=RequestMethod.GET)
	public AjaxResult getHistoryRongZi(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getHistoryRongZi(dto));
	}
	@RequestMapping(value="/getTeamMember.json",method=RequestMethod.GET)
	public AjaxResult getTeamMember(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getTeamMember(dto));
	}
	@RequestMapping(value="/getProductInfo.json",method=RequestMethod.GET)
	public AjaxResult getProductInfo(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getProductInfo(dto));
	}
	@RequestMapping(value="/getTouZi.json",method=RequestMethod.GET)
	public AjaxResult getTouZi(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getTouZi(dto));
	}
	@RequestMapping(value="/getJingPin.json",method=RequestMethod.GET)
	public AjaxResult getJingPin(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getJingPin(dto));
	}
	@RequestMapping(value="/getShangBiao.json",method=RequestMethod.GET)
	public AjaxResult getShangBiao(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getShangBiao(dto));
	}
	@RequestMapping(value="/getPatents.json",method=RequestMethod.GET)
	public AjaxResult getPatents(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getPatents(dto));
	}
	@RequestMapping(value="/getCopyReg.json",method=RequestMethod.GET)
	public AjaxResult getCopyReg(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getCopyReg(dto));
	}
	@RequestMapping(value="/getIcp.json",method=RequestMethod.GET)
	public AjaxResult getIcp(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getIcp(dto));
	}
	@RequestMapping(value="/getCompanyInfo.json",method=RequestMethod.GET)
	public AjaxResult getCompanyInfo(@RequestBody OpeneyesDTO dto){
		if(dto.getCname() == null || StringUtil.isEmpty(dto.getHumanName()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getCompanyInfo(dto));
	}
	/*@RequestMapping(value="getTargetInfo.json",method=RequestMethod.GET)
	public AjaxResult getTargetInfo(@RequestBody OpeneyesDTO dto,@PathVariable String page){
		if(StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		switch (page) {
		case "":
			
			break;

		default:
			break;
		}
		return success(openeyesService.getTargetInfo(dto));
	}*/
	
	
}
