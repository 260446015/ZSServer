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
	JSONObject getEDTInfo(OpeneyesDTO dto);

}
