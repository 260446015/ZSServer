package com.huishu.ait.service.warning;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.entity.ChangeInfo;
import com.huishu.ait.entity.dto.AreaSearchDTO;
import com.huishu.ait.entity.dto.InformationSearchDTO;
import com.huishu.ait.es.entity.ExternalFlow;
import com.huishu.ait.es.entity.WarningInformation;

/**
 * 园区预警service
 * 
 * @author yindq
 * @date 2017年8月3日
 */
public interface WarningService {
	/**
	 * 获取企业疑似外流列表
	 * 
	 * @param searchModel
	 *            查询条件
	 * @return
	 */
	// JSONArray getBusinessOutflowList(AreaSearchDTO searchModel);
	Page<ExternalFlow> getBusinessOutflowList(AreaSearchDTO searchModel);

	/**
	 * 获取信息变更预警列表
	 * 
	 * @param searchModel
	 *            查询条件
	 * @return
	 */
	Page<ChangeInfo> getInformationChangeList(InformationSearchDTO searchModel);

	/**
	 * 根据ID获取信息变更预警详情
	 * 
	 * @param id
	 *            预警ID
	 * @return
	 */
	ChangeInfo getInformationChangeById(String id);

	/**
	 * 获取企业信息变更数量
	 */
	List<ChangeInfo> getChangeInfo(String park);

	/**
	 * 获取疑似外流未预警的数量
	 */
	List<ExternalFlow> getExternalFlow(String park, String hasWarn);

	/**
	 * 删除预警数量
	 * 
	 * @param id
	 */
	boolean deleteWarning(String id);
}
