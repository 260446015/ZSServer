package com.huishu.ZSServer.controller.openeyes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
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
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/apis/openeyes")
public class OpeneyesController extends BaseController {

	@Autowired
	private OpeneyesService openeyesService;

	/**
	 * 查询主要人员
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getStaffInfo.json", method = RequestMethod.GET)
	public AjaxResult getStaffInfo(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getStaffInfo(dto));
	}

	/**
	 * 查询基本信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getBaseInfo.json", method = RequestMethod.GET)
	public AjaxResult getBaseInfo(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getBaseInfo(dto));
	}

	/**
	 * 查询分支机构
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getBranch.json", method = RequestMethod.GET)
	public AjaxResult getBranch(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getBranch(dto));
	}

	/**
	 * 历史融资
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getHistoryRongZi.json", method = RequestMethod.GET)
	public AjaxResult getHistoryRongZi(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getHistoryRongZi(dto));
	}

	/**
	 * 主要人员
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getTeamMember.json", method = RequestMethod.GET)
	public AjaxResult getTeamMember(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getTeamMember(dto));
	}

	/**
	 * 企业业务
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getProductInfo.json", method = RequestMethod.GET)
	public AjaxResult getProductInfo(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getProductInfo(dto));
	}

	/**
	 * 投资案例
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getTouZi.json", method = RequestMethod.GET)
	public AjaxResult getTouZi(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getTouZi(dto));
	}

	/**
	 * 竞品信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getJingPin.json", method = RequestMethod.GET)
	public AjaxResult getJingPin(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getJingPin(dto));
	}

	/**
	 * 商标信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getShangBiao.json", method = RequestMethod.GET)
	public AjaxResult getShangBiao(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getShangBiao(dto));
	}

	/**
	 * 专利
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getPatents.json", method = RequestMethod.GET)
	public AjaxResult getPatents(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getPatents(dto));
	}

	/**
	 * 著作权
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getCopyReg.json", method = RequestMethod.GET)
	public AjaxResult getCopyReg(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getCopyReg(dto));
	}

	/**
	 * 网站备案
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getIcp.json", method = RequestMethod.GET)
	public AjaxResult getIcp(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getIcp(dto));
	}

	/**
	 * 税务评级
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getTaxCredit.json", method = RequestMethod.GET)
	public AjaxResult getTaxCredit(@RequestBody OpeneyesDTO dto) {
		if (dto.getParams() == null || StringUtil.isEmpty(dto.getSpec()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getTaxCredit(dto));
	}

	/**
	 * 搜索企业列表
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getSousuoCompanyList.json", method = RequestMethod.GET)
	public AjaxResult getSousuoCompanyList(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		return success(openeyesService.getSousuoCompanyList(dto));
	}

	/**
	 * 请求地址/apis/openeyes/searchInfo.json 请求方式为Get
	 * 
	 * @param name
	 *            查询企业的名称 true
	 * @param target
	 *            要查询的接口名称(不区分大小写) true <br/>
	 *            baseInfo-----查询基本信息 <br/>
	 *            abnormal-----查询经营异常 <br/>
	 *            punishmentInfo-----查询行政处罚 <br/>
	 *            illegalinfo-----查询严重违法 <br/>
	 *            ownTax-----查询欠税公告 <br/>
	 *            dishonest-----查询失信人 <br/>
	 *            news-----查询新闻(未放行权限) <br/>
	 *            riskInfo-----查询企业风险(未放行权限) <br/>
	 *            humanRiskInfo-----查询人风险(未放行权限) <br/>
	 *            riskDetail-----查询风险信息(未放行权限) <br/>
	 * @param pageNum
	 *            查询页数(从第一页起) true
	 * @param pageSize
	 *            每页条数 false
	 * @param id
	 *            获取风险信息接口需要天眼查公司id,可通过查询企业基本信息接口获得id false
	 * @return 查询信息统一接口
	 */
	@RequestMapping(value = "/searchInfo.json", method = RequestMethod.GET)
	public JSONObject getTargetInfo(String name, String target, String pageNum, String pageSize, Long id) {
		JSONObject returnObj = new JSONObject();
		if (StringUtil.isEmpty(name) || StringUtil.isEmpty(target)){
			returnObj.put("message", MsgConstant.ILLEGAL_PARAM);
			return returnObj;
		}
		OpeneyesDTO dto = new OpeneyesDTO();
		if (!StringUtil.isEmpty(pageNum)) {
			try {
				dto.setPageNumber(Integer.parseInt(pageNum));
			} catch (NumberFormatException e) {
				returnObj.put("message", "请输入正确的页码数并从1开始");
				return returnObj;
			}
		}
		if (!StringUtil.isEmpty(pageSize)) {
			try {
				dto.setPageSize(Integer.parseInt(pageSize));
			} catch (NumberFormatException e) {
				returnObj.put("message", "请输入正确的页码数量");
				return returnObj;
			}
		}
		dto.setCname(name);
		dto.setFrom("2");// dto中有from字段，1代表用户，2代表广西
		dto.setId(id);
		if (target.equalsIgnoreCase("abnormal"))
			return openeyesService.getAbnormal(dto);
		else if (target.equalsIgnoreCase("punishmentInfo"))
			return openeyesService.getPunishmentInfo(dto);
		else if (target.equalsIgnoreCase("illegalinfo"))
			return openeyesService.getIllegalinfo(dto);
		else if (target.equalsIgnoreCase("ownTax"))
			return openeyesService.getOwnTax(dto);
		else if (target.equalsIgnoreCase("news"))
			return openeyesService.getNews(dto);
		else if (target.equalsIgnoreCase("dishonest"))
			return openeyesService.getDishonest(dto);
		else if (target.equalsIgnoreCase("riskInfo"))
			return openeyesService.getRiskInfo(dto);
		else if (target.equalsIgnoreCase("humanRiskInfo"))
			return openeyesService.getHumanRiskInfo(dto);
		else if (target.equalsIgnoreCase("riskDetail"))
			return openeyesService.getRiskDetail(dto);
		else if (target.equalsIgnoreCase("baseInfo"))
			return openeyesService.getBaseInfo(dto);
		else{
			returnObj.put("message", "输入的type值无效");
			return returnObj;
		}
	}
	/*
	 * @RequestMapping(value="getTargetInfo.json",method=RequestMethod.GET)
	 * public AjaxResult getTargetInfo(@RequestBody OpeneyesDTO
	 * dto,@PathVariable String page){ if(StringUtil.isEmpty(dto.getCname()))
	 * return error(MsgConstant.ILLEGAL_PARAM); switch (page) { case "":
	 * 
	 * break;
	 * 
	 * default: break; } return success(openeyesService.getTargetInfo(dto)); }
	 */

}
