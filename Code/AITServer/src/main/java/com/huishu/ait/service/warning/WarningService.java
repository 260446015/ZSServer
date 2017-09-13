package com.huishu.ait.service.warning;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.es.entity.ExternalFlow;
import com.huishu.ait.es.entity.GardenInformation;
import com.huishu.ait.es.entity.WarningInformation;

/**
 * 园区预警service
 * @author yindq
 * @date 2017年8月3日
 */
public interface WarningService {
	/**
	 * 获取企业疑似外流列表
	 * @param searchModel    查询条件
	 * @return
	 */
//	JSONArray getBusinessOutflowList(AreaSearchDTO searchModel);
	Page<ExternalFlow> getBusinessOutflowList(AreaSearchDTO searchModel);
	/**
	 * 获取信息变更预警列表
	 * @param searchModel    查询条件
	 * @return
	 */
	JSONArray getInformationChangeList(AreaSearchDTO searchModel);
	/**
	 * 根据ID获取信息变更预警详情
	 * @param id     预警ID
	 * @return
	 */
	WarningInformation getInformationChangeById(String id);
}
