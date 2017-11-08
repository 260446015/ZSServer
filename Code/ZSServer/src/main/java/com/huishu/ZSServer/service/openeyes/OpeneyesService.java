package com.huishu.ZSServer.service.openeyes;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;

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
	 * @param dto
	 * @return
	 */
	JSONObject getStaffInfo(OpeneyesDTO dto);

	/**
	 * 获取天眼查基本信息
	 * @param dto
	 * @return
	 */
	JSONObject getBaseInfo(OpeneyesDTO dto);

	/**
	 * 获取天眼查分之几股
	 * @param dto
	 * @return
	 */
	JSONObject getBranch(OpeneyesDTO dto);

	/**
	 * 获取融资历史
	 * @param dto
	 * @return
	 */
	JSONObject getHistoryRongZi(OpeneyesDTO dto);

	/**
	 * 获取核心团队
	 * @param dto
	 * @return
	 */
	JSONObject getTeamMember(OpeneyesDTO dto);

	/**
	 * 获取企业业务
	 * @param dto
	 * @return
	 */
	JSONObject getProductInfo(OpeneyesDTO dto);

	/**
	 * 获取投资案例
	 * @param dto
	 * @return
	 */
	JSONObject getTouZi(OpeneyesDTO dto);

	/**
	 * 获取竞品信息
	 * @param dto
	 * @return
	 */
	JSONObject getJingPin(OpeneyesDTO dto);

	/**
	 * 获取商标信息
	 * @param dto
	 * @return
	 */
	JSONObject getShangBiao(OpeneyesDTO dto);

	/**
	 * 获取
	 * @param dto
	 * @return
	 */
	JSONObject getPatents(OpeneyesDTO dto);

	/**
	 * 获取著作权
	 * @param dto
	 * @return
	 */
	JSONObject getCopyReg(OpeneyesDTO dto);

	/**
	 * 获取网站备案
	 * @param dto
	 * @return
	 */
	JSONObject getIcp(OpeneyesDTO dto);

	/**
	 * 获取天眼查公用方法
	 * @param dto
	 * @return
	 */
	JSONObject getTargetInfo(OpeneyesDTO dto);

	/**
	 * 获取企业一系列信息的接口
	 * @param dto
	 * @return
	 */
	JSONObject getCompanyInfo(OpeneyesDTO dto);

}
