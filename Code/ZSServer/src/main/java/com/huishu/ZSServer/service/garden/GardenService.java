package com.huishu.ZSServer.service.garden;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.GardenData;
import com.huishu.ZSServer.entity.GardenUser;
import com.huishu.ZSServer.entity.dto.AreaSearchDTO;
import com.huishu.ZSServer.entity.dto.GardenDTO;
import com.huishu.ZSServer.es.entity.AITInfo;

/**
 * 
 * @author yindawei
 * @date 2017年10月30日上午10:45:48
 * @description
 * @version
 */
public interface GardenService {
	/**
	 * 获取园区动态
	 * 
	 * @param dto
	 * @return
	 */
	Page<AITInfo> findGardensCondition(GardenDTO dto);

	/**
	 * 获取园区产值
	 */
	List<GardenData> findGardenGdp();

	/**
	 * 获取园区列表
	 * 
	 * @param dto
	 * @return
	 */
	Page<GardenData> findGardensList(GardenDTO dto);

	/**
	 * 获取园区基本信息
	 * @return
	 */
	GardenData findGarden(Long gardenId);

}
