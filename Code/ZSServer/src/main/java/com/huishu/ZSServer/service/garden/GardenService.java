package com.huishu.ZSServer.service.garden;

import java.util.List;

import org.springframework.data.domain.Page;

import com.huishu.ZSServer.entity.GardenData;
import com.huishu.ZSServer.entity.dto.AreaSearchDTO;
import com.huishu.ZSServer.entity.dto.GardenDTO;
import com.huishu.ZSServer.es.entity.AITInfo;


/**
 * 全景辖区概览service
 * 
 * @author yindq
 * @date 2017-7-28
 */
public interface GardenService {
	/**
	 * 获取园区的情报推送
	 * 
	 * @param dto
	 * @return
	 */
	Page<AITInfo> getInformationPush(AreaSearchDTO dto);

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
