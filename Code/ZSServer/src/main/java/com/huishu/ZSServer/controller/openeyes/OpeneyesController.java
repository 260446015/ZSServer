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
import com.huishu.ZSServer.exception.OpeneyesException;
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
	@RequestMapping(value = "/getStaffInfo.json", method = RequestMethod.POST)
	public AjaxResult getStaffInfo(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getStaffInfo(dto);
		} catch (OpeneyesException e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 查询股东信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getHolder.json", method = RequestMethod.POST)
	public AjaxResult getHolder(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try{
			returnObj = openeyesService.getHolder(dto);
		}catch(OpeneyesException e){
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 查询基本信息
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getBaseInfo.json", method = RequestMethod.GET, params = "name")
	public AjaxResult getBaseInfo(String name) throws OpeneyesException {
		if (StringUtil.isEmpty(name))
			return error(MsgConstant.ILLEGAL_PARAM);
		Long userId = 1L;
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname(name);
		dto.setUserId(userId);
		JSONObject returnObj = new JSONObject();
		try{
			returnObj = openeyesService.getBaseInfo(dto);
		}catch(OpeneyesException e){
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 查询分支机构
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getBranch.json", method = RequestMethod.POST)
	public AjaxResult getBranch(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try{
			returnObj = openeyesService.getBranch(dto);
		}catch(OpeneyesException e){
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 历史融资
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getHistoryRongZi.json", method = RequestMethod.POST)
	public AjaxResult getHistoryRongZi(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getHistoryRongZi(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 对外投资
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getInverst.json", method = RequestMethod.POST)
	public AjaxResult getInverst(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getInverst(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 主要人员
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getTeamMember.json", method = RequestMethod.POST)
	public AjaxResult getTeamMember(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getTeamMember(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 企业业务
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getProductInfo.json", method = RequestMethod.POST)
	public AjaxResult getProductInfo(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getProductInfo(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 信息变更
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getChangeInfo.json", method = RequestMethod.POST)
	public AjaxResult getChangeInfo(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getChangeInfo(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 投资案例
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getTouZi.json", method = RequestMethod.POST)
	public AjaxResult getTouZi(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getTouZi(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 竞品信息
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getJingPin.json", method = RequestMethod.POST)
	public AjaxResult getJingPin(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getJingPin(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 招投标
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getBids.json", method = RequestMethod.POST)
	public AjaxResult getBids(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getBids(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 债券信息
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getBond.json", method = RequestMethod.POST)
	public AjaxResult getBond(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getBond(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 购地信息
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getPurchaseland.json", method = RequestMethod.POST)
	public AjaxResult getPurchaseland(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getPurchaseland(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 招聘信息
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getEmployment.json", method = RequestMethod.POST)
	public AjaxResult getEmployment(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getEmployment(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 抽查检查
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getCheckInfo.json", method = RequestMethod.POST)
	public AjaxResult getCheckInfo(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getCheckInfo(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 产品信息
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getAppbkInfo.json", method = RequestMethod.POST)
	public AjaxResult getAppbkInfo(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getAppbkInfo(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 法律诉讼
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getLawsuit.json", method = RequestMethod.POST)
	public AjaxResult getLawsuit(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getLawsuit(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 法院公告
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getGonggao.json", method = RequestMethod.POST)
	public AjaxResult getGonggao(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getGonggao(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 失信人
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getDishonest.json", method = RequestMethod.POST)
	public AjaxResult getDishonest(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getDishonest(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 被执行人
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getZhixingInfo.json", method = RequestMethod.POST)
	public AjaxResult getZhixingInfo(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getZhixingInfo(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 股票行情
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getVolatility.json", method = RequestMethod.POST)
	public AjaxResult getVolatility(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getVolatility(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 企业简介（股票）
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getCompanyInfo.json", method = RequestMethod.POST)
	public AjaxResult getCompanyInfo(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getCompanyInfo(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 高管信息（股票）
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getSeniorExecutive.json", method = RequestMethod.POST)
	public AjaxResult getSeniorExecutive(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getSeniorExecutive(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 参股控股（股票）
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getHoldingCompany.json", method = RequestMethod.POST)
	public AjaxResult getHoldingCompany(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getHoldingCompany(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 上市公告（股票）
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getAnnouncement.json", method = RequestMethod.POST)
	public AjaxResult getAnnouncement(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getAnnouncement(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * ⼗⼤股东（⼗⼤流通股东）（股票）
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getShareholder.json", method = RequestMethod.POST)
	public AjaxResult getShareholder(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getShareholder(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 发⾏相关（股票）
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getIssueRelated.json", method = RequestMethod.POST)
	public AjaxResult getIssueRelated(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getIssueRelated(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 【web版】股本结构（股票）
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getShareStructure.json", method = RequestMethod.POST)
	public AjaxResult getShareStructure(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getShareStructure(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 【web版】股本变动（股票）
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getEquityChange.json", method = RequestMethod.POST)
	public AjaxResult getEquityChange(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getEquityChange(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 【web版】分红情况（股票）
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getBonusInfo.json", method = RequestMethod.POST)
	public AjaxResult getBonusInfo(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getBonusInfo(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 【web版】配股情况（股票）
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getAllotmen.json", method = RequestMethod.POST)
	public AjaxResult getAllotmen(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getAllotmen(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 商标信息
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getShangBiao.json", method = RequestMethod.POST)
	public AjaxResult getShangBiao(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getShangBiao(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 专利
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getPatents.json", method = RequestMethod.POST)
	public AjaxResult getPatents(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getPatents(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 资质证书
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getCertificate.json", method = RequestMethod.POST)
	public AjaxResult getCertificate(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getCertificate(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 著作权
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getCopyReg.json", method = RequestMethod.POST)
	public AjaxResult getCopyReg(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getCopyReg(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 网站备案
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getIcp.json", method = RequestMethod.POST)
	public AjaxResult getIcp(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getIcp(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}
	/**
	 * 欠税公告
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getOwnTax.json", method = RequestMethod.POST)
	public AjaxResult getOwnTax(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getOwnTax(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 税务评级
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getTaxCredit.json", method = RequestMethod.POST)
	public AjaxResult getTaxCredit(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		try {
			returnObj = openeyesService.getTaxCredit(dto);
		} catch (Exception e) {
			return error("查询数据为空");
		}
		return success(returnObj);
	}

	/**
	 * 搜索企业列表
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getSousuoCompanyList.json", method = RequestMethod.GET)
	public AjaxResult getSousuoCompanyList(@RequestBody OpeneyesDTO dto) throws OpeneyesException {
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
		if (StringUtil.isEmpty(name) || StringUtil.isEmpty(target)) {
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
		try {
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
			else {
				returnObj.put("message", "输入的type值无效");
				return returnObj;
			}
		} catch (OpeneyesException e) {
			returnObj.put("err_msg", "查询数据为空");
			returnObj.put("err_code", "999");
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
