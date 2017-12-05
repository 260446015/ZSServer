package com.huishu.ZSServer.service.openeyes;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.exception.OpeneyesException;

/**
 * 
 * @author yindawei
 * @date 2017年11月1日下午3:42:50
 * @description 天眼查service
 * @version
 */
public interface OpeneyesService {

	/**
	 * 获取天眼查主要人员
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getStaffInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 获取天眼查基本信息
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getBaseInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 获取天眼查分支机构
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getBranch(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 获取融资历史
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getHistoryRongZi(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 获取核心团队
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getTeamMember(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 获取企业业务
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getProductInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 获取投资案例
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getTouZi(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 获取竞品信息
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getJingPin(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 获取商标信息
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getShangBiao(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 获取
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getPatents(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 获取著作权
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getCopyReg(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 获取网站备案
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getIcp(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询经营异常
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getAbnormal(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询行政处罚
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getPunishmentInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询严重违法
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getIllegalinfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询欠税公告
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getOwnTax(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询新闻
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getNews(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询失信人
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getDishonest(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询企业风险
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getRiskInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询人风险
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getHumanRiskInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询风险信息
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getRiskDetail(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 税务评级
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getTaxCredit(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 搜索企业列表
	 * 
	 * @param dto
	 * @return
	 */
	JSONObject getSousuoCompanyList(OpeneyesDTO dto) throws OpeneyesException;
	
	/**
	 * 查询股东信息
	 * @param dto
	 * @return
	 * @throws OpeneyesException
	 */
	JSONObject getHolder(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询信息变更
	 * @param dto
	 * @return
	 */
	JSONObject getChangeInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询对外投资
	 * @param dto
	 * @return
	 */
	JSONObject getInverst(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询招投标
	 * @param dto
	 * @return
	 */
	JSONObject getBids(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询债券信息
	 * @param dto
	 * @return
	 */
	JSONObject getBond(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询购地信息
	 * @param dto
	 * @return
	 */
	JSONObject getPurchaseland(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询招聘信息
	 * @param dto
	 * @return
	 * @throws OpeneyesException
	 */
	JSONObject getEmployment(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询抽查检查
	 * @param dto
	 * @return
	 */
	JSONObject getCheckInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询产品信息
	 * @param dto
	 * @return
	 * @throws OpeneyesException
	 */
	JSONObject getAppbkInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询法律诉讼
	 * @param dto
	 * @return
	 * @throws OpeneyesException
	 */
	JSONObject getLawsuit(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询法院公告
	 * @param dto
	 * @return
	 */
	JSONObject getGonggao(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询被执行人
	 * @param dto
	 * @return
	 */
	JSONObject getZhixingInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询股票行情
	 * @param dto
	 * @return
	 * @throws OpeneyesException
	 */
	JSONObject getVolatility(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询企业简介（股票）
	 * @param dto
	 * @return
	 */
	JSONObject getCompanyInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询高管信息(股票)
	 * @param dto
	 * @return
	 * @throws OpeneyesException
	 */
	JSONObject getSeniorExecutive(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询参股控股（股票）
	 * @param dto
	 * @return
	 */
	JSONObject getHoldingCompany(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 查询上市公告（股票）
	 * @param dto
	 * @return
	 */
	JSONObject getAnnouncement(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * ⼗⼤股东（⼗⼤流通股东）（股票）
	 * @param dto
	 * @return
	 */
	JSONObject getShareholder(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 发⾏相关（股票）
	 * @param dto
	 * @return
	 * @throws OpeneyesException
	 */
	JSONObject getIssueRelated(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 【web版】股本结构（股票）
	 * @param dto
	 * @return
	 */
	JSONObject getShareStructure(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 【web版】股本变动（股票）
	 * @param dto
	 * @return
	 * @throws OpeneyesException
	 */
	JSONObject getEquityChange(OpeneyesDTO dto) throws OpeneyesException;

	/***
	 * 【web版】分红情况（股票）
	 * @param dto
	 * @return
	 */
	JSONObject getBonusInfo(OpeneyesDTO dto) throws OpeneyesException;

	/**
	 * 【web版】配股情况（股票）
	 * @param dto
	 * @return
	 */
	JSONObject getAllotmen(OpeneyesDTO dto) throws OpeneyesException;

}
