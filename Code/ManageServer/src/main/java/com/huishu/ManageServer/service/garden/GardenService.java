package com.huishu.ManageServer.service.garden;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.huishu.ManageServer.entity.dbFirst.GardenData;
import com.huishu.ManageServer.entity.dbFirst.GardenMap;
import com.huishu.ManageServer.entity.dbFirst.ScanGarden;
import com.huishu.ManageServer.entity.dto.GardenDTO;
import com.huishu.ManageServer.entity.dto.GardenIndustry;
import com.huishu.ManageServer.es.entity.AITInfo;


/**
 * 全景辖区概览service
 * 
 * @author yindq
 * @date 2017-7-28
 */
public interface GardenService {

	/**
	 * 获取园区动态
	 * 
	 * @param dto
	 * @return
	 */
	PageImpl<AITInfo> findGardensCondition(GardenDTO dto);
	
	/**
	 * 删除园区动态
	 * 
	 * @param dto
	 * @return
	 */
	boolean deleteCondition(String[] id);


	/**
	 * 获取园区列表
	 * 
	 * @param dto
	 * @return
	 */
	Page<GardenData> findGardensList(GardenDTO dto);


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

	/**
	 * 获取不同园区的产业类型
	 * @param dto
	 * @return
	 */
	List<Object[]> getGardenIndustryCount(GardenDTO dto);


	/**
	 * 查询园区地域
	 * @return
	 */
	List<String> getGardenArea();

	List<GardenIndustry> getGardenIndustry();

	boolean saveGarden(GardenData data);
	
	/**
	 * 获取园区产值
	 */
	Page<GardenMap> findGardenGdp(GardenDTO dto);

	/**
	 * 查询园区gdp地域区分
	 * @return
	 */
	List<Object[]> findGdpArea();

	/**
	 * 查询扫描过的园区
	 * @param dto
	 * @return
	 */
	Page<ScanGarden> findScanGarden(GardenDTO dto);

	/**
	 * 更改是否扫描的状态
	 * @param sg
	 * @return
	 */
	boolean changeScanStatus(ScanGarden sg);

	/**
	 * 按id查询园区
	 * @param id
	 * @return
	 */
	GardenData getGardenInfo(String id);

	/**
	 * 按id查询文章详情
	 * @param id
	 * @return
	 */
	AITInfo getDetailArt(String id);

	/**
	 * 保存es数据
	 * @param info
	 * @return
	 */
	boolean saveData(AITInfo info);

}
