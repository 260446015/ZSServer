package com.huishu.ZSServer.service.garden;

import java.util.List;

import org.springframework.data.domain.Page;

import com.huishu.ZSServer.entity.dto.AreaSearchDTO;
import com.huishu.ZSServer.entity.garden.GardenDTO;
import com.huishu.ZSServer.entity.garden.GardenData;
import com.huishu.ZSServer.entity.garden.GardenMap;
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
	List<GardenMap> findGardenGdp(GardenDTO dto);

	/**
	 * 获取园区列表
	 * 
	 * @param dto
	 * @return
	 */
	Page<GardenData> findGardensList(GardenDTO dto);

	/**
	 * 获取园区基本信息
	 * 
	 * @return
	 */
	GardenData findGarden(Long gardenId);

	/**
	 * 获取政策动向
	 * 
	 * @return
	 */
	Page<AITInfo> findGardenPolicy(GardenDTO dto);

	/**
	 * 获取echarts数据
	 * 
	 * @param province
	 * @return
	 */
	List<Object[]> getGardenIndustryEcharts(String province, Integer year);
}
