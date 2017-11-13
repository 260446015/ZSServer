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
	
	/**
	 * 查询主要人员
	 * @param dto 
	 * @return
	 */
	@RequestMapping(value = "/getStaffInfo.json", method = RequestMethod.GET)
	public AjaxResult getStaffInfo(@RequestBody OpeneyesDTO dto) {
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getStaffInfo(dto));
	}
	/**
	 * 查询基本信息
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getBaseInfo.json",method=RequestMethod.GET)
	public AjaxResult getBaseInfo(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getBaseInfo(dto));
	}
	/**
	 * 查询分支机构
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getBranch.json",method=RequestMethod.GET)
	public AjaxResult getBranch(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getBranch(dto));
	}
	/**
	 * 历史融资
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getHistoryRongZi.json",method=RequestMethod.GET)
	public AjaxResult getHistoryRongZi(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getHistoryRongZi(dto));
	}
	/**
	 * 主要人员
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getTeamMember.json",method=RequestMethod.GET)
	public AjaxResult getTeamMember(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getTeamMember(dto));
	}
	/**
	 * 企业业务
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getProductInfo.json",method=RequestMethod.GET)
	public AjaxResult getProductInfo(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getProductInfo(dto));
	}
	/**
	 * 投资案例
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getTouZi.json",method=RequestMethod.GET)
	public AjaxResult getTouZi(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getTouZi(dto));
	}
	/**
	 * 竞品信息
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getJingPin.json",method=RequestMethod.GET)
	public AjaxResult getJingPin(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getJingPin(dto));
	}
	/**
	 * 商标信息
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getShangBiao.json",method=RequestMethod.GET)
	public AjaxResult getShangBiao(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getShangBiao(dto));
	}
	/**
	 * 专利
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getPatents.json",method=RequestMethod.GET)
	public AjaxResult getPatents(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getPatents(dto));
	}
	/**
	 * 著作权
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getCopyReg.json",method=RequestMethod.GET)
	public AjaxResult getCopyReg(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getCopyReg(dto));
	}
	/**
	 * 网站备案
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getIcp.json",method=RequestMethod.GET)
	public AjaxResult getIcp(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getIcp(dto));
	}
	/**
	 * 税务评级
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/getTaxCredit.json",method=RequestMethod.GET)
	public AjaxResult getTaxCredit(@RequestBody OpeneyesDTO dto){
		if(dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getTaxCredit(dto));
	}
	@RequestMapping(value="/getCompanyInfo.json",method=RequestMethod.GET)
	public AjaxResult getCompanyInfo(@RequestBody OpeneyesDTO dto){
		if(dto.getCname() == null || StringUtil.isEmpty(dto.getHumanName()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getCompanyInfo(dto));
	}
	@RequestMapping(value="/getKeyWords.json",method=RequestMethod.GET)
	public AjaxResult getKeyWords(@RequestBody OpeneyesDTO dto){
		if(dto.getCname() == null || StringUtil.isEmpty(dto.getHumanName()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getKeyWords(dto));
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
