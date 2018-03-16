package com.huishu.ZSServer.controller.openeyes;

import java.io.IOException;

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
import com.huishu.ZSServer.service.user.impl.UserLogoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	@Autowired
	private UserLogoServiceImpl userLogoServiceImpl;
	
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
		dto.setUserId(getUserId());
		return success(openeyesService.getStaffInfo(dto));
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
		dto.setUserId(getUserId());
		return success(openeyesService.getHolder(dto));
	}

	/**
	 * 查询基本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBaseInfo.json", method = RequestMethod.GET, params = "name")
	public AjaxResult getBaseInfo(String name) {
		if (StringUtil.isEmpty(name))
			return error(MsgConstant.ILLEGAL_PARAM);
		userLogoServiceImpl.addOperationLogo(getUserId(), name);
		Long userId = getUserId();
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setCname(name);
		dto.setUserId(userId);
		return success(openeyesService.getBaseInfo(dto));
	}

	/**
	 * 查询分支机构
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getBranch.json", method = RequestMethod.POST)
	public AjaxResult getBranch(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getBranch(dto));
	}

	/**
	 * 历史融资
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getHistoryRongZi.json", method = RequestMethod.POST)
	public AjaxResult getHistoryRongZi(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getHistoryRongZi(dto));
	}
	/**
	 * 对外投资
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getInverst.json", method = RequestMethod.POST)
	public AjaxResult getInverst(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getInverst(dto));
	}

	/**
	 * 主要人员
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getTeamMember.json", method = RequestMethod.POST)
	public AjaxResult getTeamMember(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getTeamMember(dto));
	}

	/**
	 * 企业业务
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getProductInfo.json", method = RequestMethod.POST)
	public AjaxResult getProductInfo(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getProductInfo(dto));
	}
	/**
	 * 信息变更
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getChangeInfo.json", method = RequestMethod.POST)
	public AjaxResult getChangeInfo(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getChangeInfo(dto));
	}

	/**
	 * 投资案例
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getTouZi.json", method = RequestMethod.POST)
	public AjaxResult getTouZi(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getTouZi(dto));
	}

	/**
	 * 竞品信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getJingPin.json", method = RequestMethod.POST)
	public AjaxResult getJingPin(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getJingPin(dto));
	}
	/**
	 * 招投标
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getBids.json", method = RequestMethod.POST)
	public AjaxResult getBids(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getBids(dto));
	}
	/**
	 * 债券信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getBond.json", method = RequestMethod.POST)
	public AjaxResult getBond(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getBond(dto));
	}
	/**
	 * 购地信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getPurchaseland.json", method = RequestMethod.POST)
	public AjaxResult getPurchaseland(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getPurchaseland(dto));
	}
	/**
	 * 招聘信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getEmployment.json", method = RequestMethod.POST)
	public AjaxResult getEmployment(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getEmployment(dto));
	}
	/**
	 * 抽查检查
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getCheckInfo.json", method = RequestMethod.POST)
	public AjaxResult getCheckInfo(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getCheckInfo(dto));
	}
	/**
	 * 产品信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getAppbkInfo.json", method = RequestMethod.POST)
	public AjaxResult getAppbkInfo(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getAppbkInfo(dto));
	}
	/**
	 * 法律诉讼
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getLawsuit.json", method = RequestMethod.POST)
	public AjaxResult getLawsuit(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getLawsuit(dto));
	}
	/**
	 * 法院公告
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getGonggao.json", method = RequestMethod.POST)
	public AjaxResult getGonggao(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getGonggao(dto));
	}
	/**
	 * 失信人
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getDishonest.json", method = RequestMethod.POST)
	public AjaxResult getDishonest(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getDishonest(dto));
	}
	/**
	 * 被执行人
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getZhixingInfo.json", method = RequestMethod.POST)
	public AjaxResult getZhixingInfo(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getZhixingInfo(dto));
	}
	/**
	 * 股票行情
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getVolatility.json", method = RequestMethod.POST)
	public AjaxResult getVolatility(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getVolatility(dto));
	}
	/**
	 * 企业简介（股票）
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getCompanyInfo.json", method = RequestMethod.POST)
	public AjaxResult getCompanyInfo(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getCompanyInfo(dto));
	}
	/**
	 * 高管信息（股票）
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getSeniorExecutive.json", method = RequestMethod.POST)
	public AjaxResult getSeniorExecutive(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getSeniorExecutive(dto));
	}
	/**
	 * 参股控股（股票）
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getHoldingCompany.json", method = RequestMethod.POST)
	public AjaxResult getHoldingCompany(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getHoldingCompany(dto));
	}
	/**
	 * 上市公告（股票）
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getAnnouncement.json", method = RequestMethod.POST)
	public AjaxResult getAnnouncement(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getAnnouncement(dto));
	}
	/**
	 * ⼗⼤股东（⼗⼤流通股东）（股票）
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getShareholder.json", method = RequestMethod.POST)
	public AjaxResult getShareholder(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getShareholder(dto));
	}
	/**
	 * 发⾏相关（股票）
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getIssueRelated.json", method = RequestMethod.POST)
	public AjaxResult getIssueRelated(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		dto.setUserId(getUserId());
		return success(openeyesService.getIssueRelated(dto));
	}
	/**
	 * 【web版】股本结构（股票）
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getShareStructure.json", method = RequestMethod.POST)
	public AjaxResult getShareStructure(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		dto.setUserId(getUserId());
		return success(openeyesService.getShareStructure(dto));
	}
	/**
	 * 【web版】股本变动（股票）
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getEquityChange.json", method = RequestMethod.POST)
	public AjaxResult getEquityChange(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getEquityChange(dto));
	}
	/**
	 * 【web版】分红情况（股票）
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getBonusInfo.json", method = RequestMethod.POST)
	public AjaxResult getBonusInfo(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getBonusInfo(dto));
	}
	/**
	 * 【web版】配股情况（股票）
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getAllotmen.json", method = RequestMethod.POST)
	public AjaxResult getAllotmen(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		dto.setUserId(getUserId());
		return success(openeyesService.getAllotmen(dto));
	}

	/**
	 * 商标信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getShangBiao.json", method = RequestMethod.POST)
	public AjaxResult getShangBiao(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getShangBiao(dto));
	}

	/**
	 * 专利
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getPatents.json", method = RequestMethod.POST)
	public AjaxResult getPatents(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getPatents(dto));
	}
	/**
	 * 资质证书
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getCertificate.json", method = RequestMethod.POST)
	public AjaxResult getCertificate(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success( openeyesService.getCertificate(dto));
	}

	/**
	 * 著作权
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getCopyReg.json", method = RequestMethod.POST)
	public AjaxResult getCopyReg(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getCopyReg(dto));
	}

	/**
	 * 网站备案
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getIcp.json", method = RequestMethod.POST)
	public AjaxResult getIcp(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		JSONObject returnObj = new JSONObject();
		dto.setUserId(getUserId());
		return success(openeyesService.getIcp(dto));
	}
	/**
	 * 欠税公告
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getOwnTax.json", method = RequestMethod.POST)
	public AjaxResult getOwnTax(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getOwnTax(dto));
	}

	/**
	 * 税务评级
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/getTaxCredit.json", method = RequestMethod.POST)
	public AjaxResult getTaxCredit(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
		return success(openeyesService.getTaxCredit(dto));
	}

	/**
	 * 搜索企业列表
	 * 
	 * @param dto
	 * @return
	 * @throws OpeneyesException 
	 */
	@RequestMapping(value = "/getSousuoCompanyList.json", method = RequestMethod.GET)
	public AjaxResult getSousuoCompanyList(@RequestBody OpeneyesDTO dto) {
		if (StringUtil.isEmpty(dto.getCname()))
			return error(MsgConstant.ILLEGAL_PARAM);
		dto.setUserId(getUserId());
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
	 * @throws IOException 
	 */
	@RequestMapping(value = "/searchInfo.do", method = RequestMethod.GET)
	public JSONObject getTargetInfo(String name, String target, Long id, String humanName) throws IOException {
		JSONObject returnObj = new JSONObject();
		if(!"riskDetail".equalsIgnoreCase(target)){
			if (StringUtil.isEmpty(name) || StringUtil.isEmpty(target)) {
				returnObj.put("message", MsgConstant.ILLEGAL_PARAM);
				return returnObj;
			}
		}
		OpeneyesDTO dto = new OpeneyesDTO();
		dto.setPageNumber(1);
		dto.setPageSize(200);
		dto.setCname(name);
		dto.setFrom("2");// dto中有from字段，1代表用户，2代表广西
		dto.setId(id);
		dto.setHumanName(humanName);
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
			else if (target.equalsIgnoreCase("humanRiskInfo")){
				if(StringUtil.isEmpty(humanName)){
					returnObj.put("message", MsgConstant.ILLEGAL_PARAM);
					return returnObj;
				}
				return openeyesService.getHumanRiskInfo(dto);
			}else if (target.equalsIgnoreCase("riskDetail"))
				return openeyesService.getRiskDetail(dto);
			else if (target.equalsIgnoreCase("baseInfo"))
				return openeyesService.getBaseInfo(dto);
			else if (target.equalsIgnoreCase("staff"))
				return openeyesService.getStaffInfo(dto);
			else {
				returnObj.put("message", "输入的type值无效");
				return returnObj;
			}
		} catch (Exception e) {
			returnObj.put("err_msg", "查询数据为空");
			returnObj.put("err_code", "999");
			return returnObj;
		}
	}

	@RequestMapping(value = "downLoad.json")
	public void downLoad(HttpServletRequest request, HttpServletResponse response){
        OpeneyesDTO dto = new OpeneyesDTO();
        String methods = request.getParameter("methods");
        String cname = request.getParameter("cname");
        String exportType = request.getParameter("exportType");
        dto.setUserId(getUserId());
        dto.setMethods(methods.split(","));
        dto.setCname(cname);
        dto.setExportType(exportType);
		openeyesService.downLoad(dto,request,response);
		return;
	}

}
